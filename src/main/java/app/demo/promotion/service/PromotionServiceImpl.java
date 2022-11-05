package app.demo.promotion.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import app.demo.promotion.domain.Promotion;
import app.demo.promotion.dto.SKU;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PromotionServiceImpl implements PromotionService {

    Map<String, Promotion> promotions;

    public PromotionServiceImpl(@Qualifier("promotions") Map<String, Promotion> promotions) {
        this.promotions = promotions;
    }

    @Override
    public Integer applyPromotion(List<SKU> items) {

        log.info("applyPromotion - start");
        log.info("items: {}", items.size());

        // init all products total to 0
        Integer totalA = 0, totalB = 0, totalC = 0, totalD = 0;

        // final sum
        Integer sum = 0;

        for (SKU item : items) {
            switch (item.getName()) {
                case "A" -> {
                    Promotion promotionA = promotions.get("A");
                    totalA = calculatePromoTotal(item.getQuantity(), promotionA);
                }
                case "B" -> {
                    Promotion promotionB = promotions.get("B");
                    totalB = calculatePromoTotal(item.getQuantity(), promotionB);
                }
                case "C" -> {
                    Promotion promotionC = promotions.get("C");
                    totalC = calculatePromoTotal(item.getQuantity(), promotionC);
                }
                case "D" -> {
                    Promotion promotionD = promotions.get("D");
                    totalD = calculatePromoTotal(item.getQuantity(), promotionD);
                }
            }
        }

        sum = totalA + totalB + totalC + totalD;

        log.info("applyPromotion - end");
        return sum;
    }

    private Integer calculatePromoTotal(Integer itemSize, Promotion promotion) {
        Integer unitPrice = promotion.getUnitPrice();
        Integer promoQuantity = promotion.getPromoQuantity();
        Integer promoPrice = promotion.getPromoPrice();

        Integer total = (itemSize / promoQuantity) * promoPrice + (itemSize % promoQuantity) * unitPrice;
        log.info("total: {}", total);

        return total;
    }
}