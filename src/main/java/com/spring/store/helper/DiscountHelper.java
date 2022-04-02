package com.spring.store.helper;

import com.spring.store.models.Item;
import com.spring.store.models.types.ItemType;
import com.spring.store.models.User;
import com.spring.store.models.types.UserType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class DiscountHelper {

    private static final int YEARS_FOR_DISCOUNT = 2;

    private static final double GOLD_CARD_DISCOUNT_PERCENTAGE = 0.30;
    private static final double SILVER_CARD_DISCOUNT_PERCENTAGE = 0.20;
    private static final double AFFILIATE_DISCOUNT_PERCENTAGE = 0.10;
    private static final double CUSTOMER_DISCOUNT_PERCENTAGE = 0.025;

    public BigDecimal calculateTotal(List<Item> items)
    {
        return items.stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public BigDecimal calculateTotalPerType(List<Item> items, ItemType type){

        BigDecimal sum =  BigDecimal.valueOf(0);

        if(type != null) {
            sum = items.stream().filter(i ->  type.equals(i.getType())).map(Item::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
        }

        return sum;
    }


    public BigDecimal getUserDiscount(User user) {
    if(user == null){
        throw new NullPointerException("User cannot be null");
    }

        BigDecimal discount =  BigDecimal.valueOf(0);

        UserType type = user.getType();


        switch (type) {
            case GOLD_CARD:
                discount = BigDecimal.valueOf(GOLD_CARD_DISCOUNT_PERCENTAGE).setScale(2, RoundingMode.HALF_EVEN);
                break;
            case SILVER_CARD:
                discount = BigDecimal.valueOf(SILVER_CARD_DISCOUNT_PERCENTAGE).setScale(2,RoundingMode.HALF_EVEN);
                break;
            case AFFILIATE:
                discount = BigDecimal.valueOf(AFFILIATE_DISCOUNT_PERCENTAGE).setScale(2,RoundingMode.HALF_EVEN);
                break;
            case CUSTOMER:
                if(isCustomerSince(user.getRegisterDate(), YEARS_FOR_DISCOUNT)){
                    discount = BigDecimal.valueOf(CUSTOMER_DISCOUNT_PERCENTAGE).setScale(3,RoundingMode.HALF_EVEN);
                }
                break;
            default:
                break;
        }
        return discount;
    }

    public boolean isCustomerSince(LocalDate registeredDate, long years) {
        Period period = Period.between(registeredDate,LocalDate.now());
        return period.getYears() >= years;
    }

    public BigDecimal calculateBillsDiscount(BigDecimal totalAmount, BigDecimal amount, BigDecimal discountAmount) {
        int value = totalAmount.divide(amount).intValue();
        return discountAmount.multiply(BigDecimal.valueOf(value));
    }

    public BigDecimal calculateDiscount(BigDecimal amount, BigDecimal discount){
        if(discount.doubleValue() > 1.0){
            throw new IllegalArgumentException("Discount cannot be more than 100%");
        }

        BigDecimal x = amount.multiply(discount);
        return amount.subtract(x);
    }


}
