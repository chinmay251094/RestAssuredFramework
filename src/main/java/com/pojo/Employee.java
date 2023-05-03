package com.pojo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "set")
public class Employee {
    private int id;
    private String name;
    private String email;
    private String city;
}
