package com.spring.store.controller.requests;


import com.spring.store.models.Bill;
import com.spring.store.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountRequest {

    private User user;

    private Bill bill;
}
