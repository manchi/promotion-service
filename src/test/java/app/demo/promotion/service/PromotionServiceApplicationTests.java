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

		List<SKU> items1 = List.of(new SKU("A", 1));

		Integer total = promotionService.applyPromotion(items1);

		assertEquals(50, total);
	}


}