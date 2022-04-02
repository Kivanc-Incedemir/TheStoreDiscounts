package com.spring.store.services;


import com.spring.store.helper.DiscountHelper;
import com.spring.store.models.Bill;
import com.spring.store.models.types.ItemType;
import com.spring.store.models.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DiscountServiceImpl implements DiscountService{



    @Override
    public BigDecimal discountCalculation(User user, Bill bill) {

        DiscountHelper helper = new DiscountHelper();

        BigDecimal totalAmount = helper.calculateTotal(bill.getItems());
        BigDecimal phonesAmount = helper.calculateTotalPerType(bill.getItems(), ItemType.PHONES);
        BigDecimal nonPhonesAmount = totalAmount.subtract(phonesAmount);
        BigDecimal userDiscount = helper.getUserDiscount(user);
        BigDecimal billsDiscount = helper.calculateBillsDiscount(totalAmount,  BigDecimal.valueOf(200), BigDecimal.valueOf(5));

        if(nonPhonesAmount.compareTo(BigDecimal.ZERO) > 0) {
            nonPhonesAmount = helper.calculateDiscount(nonPhonesAmount,userDiscount);
        }

        return phonesAmount.add(nonPhonesAmount).subtract(billsDiscount);
    }

}


