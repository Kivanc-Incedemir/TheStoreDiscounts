package com.spring.store.models;


import com.spring.store.models.types.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private UserType type;

    private LocalDate registerDate;
}
