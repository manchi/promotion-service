package app.demo.promotion.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import app.demo.promotion.domain.Product;
import app.demo.promotion.domain.Promotion;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class PromotionConfig {

    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    private static final String PROMO_DEFINITION_MATCHER = "classpath:/promotions/*.yaml";

    @Bean(name = "promotions")
    public Map<String, Promotion> loadPromotions() {

        Map<String, Promotion> collection = new HashMap<>();
        Resource[] resources = new Resource[0];

        try {
            // get list of yaml files from /promotions directory
            resources = new PathMatchingResourcePatternResolver().getResources(PROMO_DEFINITION_MATCHER);
        } catch (IOException e) {
            log.error("Exception while reading the yaml definitions. message: {} , exception: {}", e.getMessage(), e);
        }

        for (Resource resource : resources) {
            Product product = null;

            try {
                product = mapper.readValue(resource.getInputStream(), Product.class);
            } catch (IOException e) {
                log.error("Exception while loading product. message: {} , exception: {}", e.getMessage(), e);
            }

            if (product != null) {
                collection.put(product.getName(), product.getPromotion());

                log.info("product {}: definition {}", product.getName(),
                         ReflectionToStringBuilder.toString(product, ToStringStyle.MULTI_LINE_STYLE));
            }
        }
        log.info("total promotions: {}", collection.size());
        log.info("collection: {}", collection);
        return collection;
    }

    /*
    @Bean(name = "promotions")
    public Map<String, Promotion> loadPromotions() {

        Map<String, Promotion> collection = new HashMap<>();

        Product productA = new Product("A", new Promotion(50, 3, 130));
        Product productB = new Product("B", new Promotion(30, 2, 45));
        Product productC = new Product("C", new Promotion(20, 1, 20));
        Product productD = new Product("D", new Promotion(15, 1, 15));
        Product productCD = new Product("CD", new Promotion(30, 1, 30));

        collection.put(productA.getName(), productA.getPromotion());
        collection.put(productB.getName(), productB.getPromotion());
        collection.put(productC.getName(), productC.getPromotion());
        collection.put(productD.getName(), productD.getPromotion());
        collection.put(productCD.getName(), productCD.getPromotion());

        log.info("total promotions: {}", collection.size());
        log.info("collection: {}", collection);
        return collection;
    }
    */

}