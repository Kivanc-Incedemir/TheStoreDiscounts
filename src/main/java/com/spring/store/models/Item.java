package com.spring.store.models;


import com.spring.store.models.types.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class Item {
    private ItemType type;

    private BigDecimal price;
}
