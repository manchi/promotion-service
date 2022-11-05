package app.demo.promotion.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import app.demo.promotion.PromotionServiceApplication;
import app.demo.promotion.dto.SKU;

@SpringBootTest(classes = PromotionServiceApplication.class)
class PromotionServiceApplicationTests {

    @Autowired
    PromotionService promotionService;

    @Test
    void when_only_1_item_of_1_quantity_then_total_is_50() {

        List<SKU> skuList = List.of(new SKU("A", 1));

        Integer total = promotionService.applyPromotion(skuList);

        assertEquals(50, total);
    }


    @Test
    void when_4_items_of_1_quantity_then_total_is_110() {

        List<SKU> skuList = List.of(new SKU("A", 1),
                                    new SKU("B", 1),
                                    new SKU("C", 1),
                                    new SKU("D", 1));

        Integer total = promotionService.applyPromotion(skuList);
        assertEquals(110, total);
    }

    @Test
    public void when_4_items_of_different_quantity_then_total_is_280() {

        List<SKU> skuList = List.of(new SKU("A", 3),
                                    new SKU("B", 5),
                                    new SKU("C", 1),
                                    new SKU("D", 1));

        Integer total = promotionService.applyPromotion(skuList);
        assertEquals(280, total);
    }


}