package com.spring.store.models;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Bill {

    private List<Item>items;
}
