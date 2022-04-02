package com.spring.store.services;

import com.spring.store.models.Bill;
import com.spring.store.models.User;

import java.math.BigDecimal;

public interface DiscountService {


    BigDecimal discountCalculation(User user, Bill bill);
}
