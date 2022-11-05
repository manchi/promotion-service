package app.demo.promotion.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import app.demo.promotion.domain.Product;
import app.demo.promotion.domain.Promotion;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class PromotionConfig {

    @Bean(name = "promotions")
    public Map<String, Promotion> loadPromotions() throws IOException {

        Map<String, Promotion> collection = new HashMap<>();

        Product productA = new Product("A", new Promotion(50, 3, 130));
        Product productB = new Product("B", new Promotion(30, 2, 45));
        Product productC = new Product("C", new Promotion(20, 1, 20));
        Product productD = new Product("D", new Promotion(15, 1, 15));

        collection.put(productA.getName(), productA.getPromotion());
        collection.put(productB.getName(), productB.getPromotion());
        collection.put(productC.getName(), productC.getPromotion());
        collection.put(productD.getName(), productD.getPromotion());

        log.info("total promotions: {}", collection.size());
        log.info("collection: {}", collection);
        return collection;
    }
}