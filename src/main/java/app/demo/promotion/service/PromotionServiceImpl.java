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

        // to store the quantity of c and d products
        int countC = 0, countD = 0;

        // init all products total to 0
        int totalA = 0, totalB = 0, totalC = 0, totalD = 0;

        // return total value
        int sum = 0;

        for (SKU item : items) {
            switch (item.getName().toUpperCase()) {
                case "A" -> {
                    Promotion promotionA = promotions.get("A");
                    totalA = calculatePromoTotal(item.getQuantity(), promotionA);
                    log.info("total for {}: {}", item.getName(), totalA);
                }
                case "B" -> {
                    Promotion promotionB = promotions.get("B");
                    totalB = calculatePromoTotal(item.getQuantity(), promotionB);
                    log.info("total for {}: {}", item.getName(), totalB);
                }
                case "C" -> {
                    Promotion promotionC = promotions.get("C");
                    countC = item.getQuantity();
                    totalC = calculatePromoTotal(item.getQuantity(), promotionC);
                    log.info("total for {}: {}", item.getName(), totalC);
                }
                case "D" -> {
                    Promotion promotionD = promotions.get("D");
                    countD = item.getQuantity();
                    totalD = calculatePromoTotal(item.getQuantity(), promotionD);
                    log.info("total for {}: {}", item.getName(), totalD);
                }
            }
        }

        // check for product C and product D
        if (countC > 0 && countD > 0) {

            if (countC == countD) {
                // both have same values. so apply single logic using one of the counts

                int totalCD = calculatePromoTotal(countC, promotions.get("CD"));
                log.info("[C==D] promotion total for CD: {}", totalCD);

                sum = totalA + totalB + totalCD;
            } else if (countC > countD) {

                // C count is more than D
                int totalCD = calculatePromoTotal(countD, promotions.get("CD"));
                log.info("[C>D] promotion total for CD: {}", totalCD);
                int remainingC = calculatePromoTotal(countC - countD, promotions.get("C"));
                log.info("promotion total for remaining C count: {}", remainingC);

                sum = totalA + totalB + totalCD + remainingC;
            } else { // countD > countC

                // D count is more than C
                int totalCD = calculatePromoTotal(countC, promotions.get("CD"));
                log.info("[C<D] promotion total for CD: {}", totalCD);
                int remainingD = calculatePromoTotal(countD - countC, promotions.get("D"));
                log.info("promotion total for remaining D count: {}", remainingD);

                sum = totalA + totalB + totalCD + remainingD;
            }
        } else {
            // sum of all types
            sum = totalA + totalB + totalC + totalD;
        }

        log.info("returning: {}", sum);
        log.info("applyPromotion - end");
        return sum;
    }

    private Integer calculatePromoTotal(Integer itemSize, Promotion promotion) {
        Integer unitPrice = promotion.getUnitPrice();
        Integer promoQuantity = promotion.getPromoQuantity();
        Integer promoPrice = promotion.getPromoPrice();

        Integer total = (itemSize / promoQuantity) * promoPrice + (itemSize % promoQuantity) * unitPrice;
        log.info("calculatePromoTotal: {}", total);

        return total;
    }
}