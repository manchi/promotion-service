package app.demo.promotion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import app.demo.promotion.dto.SKU;

@Service
public interface PromotionService {

    Integer applyPromotion(List<SKU> items);
}