package com.demo_mongo_java_api.dto;

import lombok.Data;

@Data
public class Address {

    private String City;
    private int zipcode;
    private String Country;
}
