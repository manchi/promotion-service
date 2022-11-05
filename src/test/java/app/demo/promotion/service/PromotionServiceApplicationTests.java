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
    void when_only_1_item_of_10_quantity_then_total_is_440() {

        List<SKU> skuList = List.of(new SKU("A", 10));

        Integer total = promotionService.applyPromotion(skuList);
        assertEquals(440, total);
    }

    @Test
    void when_only_2_items_of_1_quantity_then_total_is_70() {

        List<SKU> skuList = List.of(new SKU("A", 1),
                                    new SKU("C", 1));

        Integer total = promotionService.applyPromotion(skuList);
        assertEquals(70, total);
    }

    @Test
    void when_only_2_items_of_10_quantity_then_total_is_640() {

        List<SKU> skuList = List.of(new SKU("A", 10),
                                    new SKU("C", 10));

        Integer total = promotionService.applyPromotion(skuList);
        assertEquals(640, total);
    }

    @Test
    void when_2_items_of_different_quantity_then_total_is_390() {

        List<SKU> skuList = List.of(new SKU("A", 5),
                                    new SKU("C", 8));

        Integer total = promotionService.applyPromotion(skuList);
        assertEquals(390, total);
    }

    @Test
    void when_3_items_of_1_quantity_then_total_is_100() {

        List<SKU> skuList = List.of(new SKU("A", 1),
                                    new SKU("B", 1),
                                    new SKU("C", 1));

        Integer total = promotionService.applyPromotion(skuList);
        assertEquals(100, total);
    }

    @Test
    void when_3_items_of_5_quantity_then_total_is_450() {

        List<SKU> skuList = List.of(new SKU("A", 5),
                                    new SKU("B", 5),
                                    new SKU("C", 5));

        Integer total = promotionService.applyPromotion(skuList);
        assertEquals(450, total);
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
    void when_3_items_with_C_only_then_total_is_140() {

        List<SKU> skuList = List.of(new SKU("A", 1),
                                    new SKU("B", 1),
                                    new SKU("C", 3));

        Integer total = promotionService.applyPromotion(skuList);
        assertEquals(140, total);
    }

    @Test
    void when_3_items_with_D_only_then_total_is_125() {

        List<SKU> skuList = List.of(new SKU("A", 1),
                                    new SKU("B", 1),
                                    new SKU("D", 3));

        Integer total = promotionService.applyPromotion(skuList);
        assertEquals(125, total);
    }

    @Test
    void when_2_items_with_CD_only_then_total_is_30() {

        List<SKU> skuList = List.of(new SKU("C", 1),
                                    new SKU("D", 1));

        Integer total = promotionService.applyPromotion(skuList);
        assertEquals(30, total);
    }

    @Test
    void when_2_items_of_quantity_3_with_CD_only_then_total_is_90() {

        List<SKU> skuList = List.of(new SKU("C", 3),
                                    new SKU("D", 3));

        Integer total = promotionService.applyPromotion(skuList);
        assertEquals(90, total);
    }

    @Test
    void when_4_items_of_5_quantity_then_total_is_500() {

        List<SKU> skuList = List.of(new SKU("A", 5),
                                    new SKU("B", 5),
                                    new SKU("C", 5),
                                    new SKU("D", 5));

        Integer total = promotionService.applyPromotion(skuList);
        assertEquals(500, total);
    }

    @Test
    public void when_3_items_of_10_quantity_with_C_only_then_total_is_865() {
        List<SKU> skuList = List.of(new SKU("A", 10),
                                    new SKU("B", 10),
                                    new SKU("C", 10));
        Integer total = promotionService.applyPromotion(skuList);
        assertEquals(865, total);
    }

    @Test
    public void when_3_items_of_10_quantity_with_D_only_then_total_is_815() {
        List<SKU> skuList = List.of(new SKU("A", 10),
                                    new SKU("B", 10),
                                    new SKU("D", 10));
        Integer total = promotionService.applyPromotion(skuList);
        assertEquals(815, total);
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

    @Test
    public void when_4_items_of_same_quantity_then_total_is_295() {

        List<SKU> skuList = List.of(new SKU("A", 3),
                                    new SKU("B", 3),
                                    new SKU("C", 3),
                                    new SKU("D", 3));

        /*
        A:3, B:3, C:3, D:3
        130 + 45+30 + 30*3 = 295
         */
        Integer total = promotionService.applyPromotion(skuList);
        assertEquals(295, total);
    }

    @Test
    void when_3_items_of_0_quantity_then_total_is_0() {

        List<SKU> skuList = List.of(new SKU("A", 0),
                                    new SKU("B", 0),
                                    new SKU("C", 0));

        Integer total = promotionService.applyPromotion(skuList);
        assertEquals(0, total);
    }

    @Test
    void when_4_items_of_0_quantity_then_total_is_0() {

        List<SKU> skuList = List.of(new SKU("A", 0),
                                    new SKU("B", 0),
                                    new SKU("C", 0),
                                    new SKU("D", 0));

        Integer total = promotionService.applyPromotion(skuList);
        assertEquals(0, total);
    }

    @Test
    void when_4_items_of_2_quantity_with_lowercase_then_total_is_200() {

        List<SKU> skuList = List.of(new SKU("a", 1),
                                    new SKU("b", 2),
                                    new SKU("c", 3),
                                    new SKU("d", 4));
        /*
        A:1, B:2, C:3, D:4
        50 + 45 + 30*3 + 15 = 200
         */
        Integer total = promotionService.applyPromotion(skuList);
        assertEquals(200, total);
    }

    @Test
    void when_4_items_with_lowercase_invalid_then_total_is_155() {

        List<SKU> skuList = List.of(new SKU("a", 1),
                                    new SKU("b", 2),
                                    new SKU("c", 3),
                                    new SKU("x", 4));
        /*
        A:1, B:2, C:3, X:4
        50 + 45 + 60 = 155
         */
        Integer total = promotionService.applyPromotion(skuList);
        assertEquals(155, total);
    }

}