package app.demo.promotion.service;

import java.util.List;

import app.demo.promotion.dto.SKU;

public interface PromotionService {

    Integer applyPromotion(List<SKU> items);
}