package app.demo.promotion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import app.demo.promotion.dto.SKU;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PromotionServiceImpl implements PromotionService {

    public Integer applyPromotion(List<SKU> items) {
        log.info("received request for applyPromotion.");
        log.info("total items: {}", items.size());

        log.info("return total value");
        return 50;
    }
}