package app.demo.promotion.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.demo.promotion.dto.SKU;
import app.demo.promotion.service.PromotionService;
import lombok.extern.slf4j.Slf4j;

@RestController()
@Slf4j
public class PromotionController {

    PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> placeOrder(@RequestBody List<SKU> items) {
        log.info("request received for placeOrder: {}", items.size());
        Integer total = promotionService.applyPromotion(items);

        log.info("total after applying promotion: {}", total);

        return ResponseEntity.ok("{total: " + total + "}");
    }
}