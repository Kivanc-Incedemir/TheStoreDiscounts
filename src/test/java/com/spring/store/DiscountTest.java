package com.spring.store;


import com.spring.store.helper.DiscountHelper;
import com.spring.store.models.*;
import com.spring.store.models.types.ItemType;
import com.spring.store.models.types.UserType;
import com.spring.store.services.DiscountService;
import com.spring.store.services.DiscountServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DiscountTest {


    @Test
    public void testCalculateTotal_PhonesOnly() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(ItemType.PHONES, BigDecimal.valueOf(100.0)));
        items.add(new Item(ItemType.PHONES, BigDecimal.valueOf(100.0)));
        items.add(new Item(ItemType.PHONES, BigDecimal.valueOf(100.0)));


        DiscountHelper helper = new DiscountHelper();
        BigDecimal total = helper.calculateTotalPerType(items, ItemType.PHONES);
        assertEquals(300,total.doubleValue(),0);
    }

    @Test
    public void testCalculateTotal_NonPhonesOnly() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item(ItemType.CLOTHES, BigDecimal.valueOf(100.0)));
        items.add(new Item(ItemType.OTHER, BigDecimal.valueOf(100.0)));
        items.add(new Item(ItemType.GROCERY,BigDecimal.valueOf(100.0)));

        DiscountHelper helper = new DiscountHelper();
        BigDecimal total = helper.calculateTotal(items);
        assertEquals(300.0,total.doubleValue(),0);
    }

    @Test(expected = NullPointerException.class)
    public void testCalculateTotal_error() {
        DiscountHelper helper = new DiscountHelper();
        helper.getUserDiscount(null);
    }

    @Test
    public void testCalculateDiscount_10pct() {
        DiscountHelper helper = new DiscountHelper();
        BigDecimal total = helper.calculateDiscount(BigDecimal.valueOf(1000),BigDecimal.valueOf(0.1));
        assertEquals(900, total.doubleValue(),0);
    }

    @Test
    public void testCalculateDiscount_50pct() {
        DiscountHelper helper = new DiscountHelper();
        BigDecimal total = helper.calculateDiscount(BigDecimal.valueOf(1000),BigDecimal.valueOf(0.5));
        assertEquals(500, total.doubleValue(),0);
    }

    @Test
    public void testCalculateDiscount_0pct() {
        DiscountHelper helper = new DiscountHelper();
        BigDecimal total = helper.calculateDiscount(BigDecimal.valueOf(1000),BigDecimal.valueOf(0));
        assertEquals(1000, total.doubleValue(),0);
    }

    @Test
    public void testCalculateDiscount_100pct() {
        DiscountHelper helper = new DiscountHelper();
        BigDecimal total = helper.calculateDiscount(BigDecimal.valueOf(1000),BigDecimal.valueOf(1.0));
        assertEquals(0.0, total.doubleValue(),0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateDiscount_error() {
        DiscountHelper helper = new DiscountHelper();
        helper.calculateDiscount(BigDecimal.valueOf(1000), BigDecimal.valueOf(2.0));
    }

    @Test
    public void testGetUserSpecificDiscount_affiliate() {
        User user = new User(UserType.AFFILIATE, LocalDate.now());
        DiscountHelper helper = new DiscountHelper();
        BigDecimal discount = helper.getUserDiscount(user);
        assertEquals(0.1,discount.doubleValue(),0);
    }

    @Test
    public void testGetUserSpecificDiscount_gold() {
        User user = new User(UserType.GOLD_CARD, LocalDate.now());
        DiscountHelper helper = new DiscountHelper();
        BigDecimal discount = helper.getUserDiscount(user);
        assertEquals(0.3,discount.doubleValue(),0);
    }

    @Test
    public void testGetUserSpecificDiscount_silver() {
        User user = new User(UserType.SILVER_CARD, LocalDate.now());
        DiscountHelper helper = new DiscountHelper();
        BigDecimal discount = helper.getUserDiscount(user);
        assertEquals(0.2,discount.doubleValue(),0);
    }

    @Test
    public void testGetUserSpecificDiscount_customer_old() {
        LocalDate joinDate = LocalDate.of(2016,2,23);
        User user = new User(UserType.CUSTOMER, joinDate);
        DiscountHelper helper = new DiscountHelper();
        BigDecimal discount = helper.getUserDiscount(user);
        assertEquals(0.025,discount.doubleValue(),0);
    }

    @Test
    public void testGetUserSpecificDiscount_customer_new() {
        User user = new User(UserType.CUSTOMER, LocalDate.now());
        DiscountHelper helper = new DiscountHelper();
        BigDecimal discount = helper.getUserDiscount(user);
        assertEquals(0.0,discount.doubleValue(),0);
    }

    @Test(expected = NullPointerException.class)
    public void testGetUserSpecificDiscount_customer_null_user(){
        DiscountHelper helper = new DiscountHelper();
        helper.getUserDiscount(null);
    }

    @Test
    public void testIsCustomerSince(){
        DiscountHelper helper = new DiscountHelper();
        LocalDate joinDate = LocalDate.now();
        boolean isTwoYearsJoined = helper.isCustomerSince(joinDate,2);
        assertFalse(isTwoYearsJoined);
    }

    @Test
    public void testIsCustomerSince_1year(){
        DiscountHelper helper = new DiscountHelper();
        LocalDate joinDate = LocalDate.now().minusYears(1);
        boolean isTwoYearsJoined = helper.isCustomerSince(joinDate,2);
        assertFalse(isTwoYearsJoined);
    }

    @Test
    public void testIsCustomerSince_2year(){
        DiscountHelper helper = new DiscountHelper();
        LocalDate joinDate = LocalDate.now().minusYears(2);
        boolean isTwoYearsJoined = helper.isCustomerSince(joinDate,2);
        assertTrue(isTwoYearsJoined);
    }

    @Test
    public void testIsCustomerSince_3year(){
        DiscountHelper helper = new DiscountHelper();
        LocalDate joinDate = LocalDate.now().minusYears(3);
        boolean isTwoYearsJoined = helper.isCustomerSince(joinDate,2);
        assertTrue(isTwoYearsJoined);
    }

    @Test
    public void testCalculateBillsDiscount() {
        DiscountHelper helper = new DiscountHelper();
        BigDecimal amount = helper.calculateBillsDiscount(BigDecimal.valueOf(1000), BigDecimal.valueOf(100), BigDecimal.valueOf(5));
        assertEquals(50,amount.doubleValue(),0);
    }

    @Test
    public void testCalculateBillsDiscount_2() {
        DiscountHelper helper = new DiscountHelper();
        BigDecimal amount = helper.calculateBillsDiscount(BigDecimal.valueOf(1000), BigDecimal.valueOf(50), BigDecimal.valueOf(5));
        assertEquals(100,amount.doubleValue(),0);
    }

    @Test
    public void testCalculateBillsDiscount_3() {
        DiscountHelper helper = new DiscountHelper();
        BigDecimal amount = helper.calculateBillsDiscount(BigDecimal.valueOf(5632), BigDecimal.valueOf(100), BigDecimal.valueOf(5));
        assertEquals(280,amount.doubleValue(),0);
    }

    @Test
    public void testDiscountServiceCalculate() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(ItemType.PHONES,BigDecimal.valueOf(50)));
        items.add(new Item(ItemType.OTHER,BigDecimal.valueOf(200)));
        items.add(new Item(ItemType.PHONES,BigDecimal.valueOf(10)));

        Bill bill = new Bill();
        bill.setItems(items);

        DiscountService discountService = new DiscountServiceImpl();

        discountService.discountCalculation(new User(UserType.CUSTOMER,LocalDate.now()),bill);
        DiscountHelper helper = new DiscountHelper();
        BigDecimal amount = helper.calculateBillsDiscount(BigDecimal.valueOf(5632),BigDecimal.valueOf(100),BigDecimal.valueOf(5));
        assertEquals(280,amount.doubleValue(),0);
    }

}
