package com.demo_mongo_java_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {

    private String City;
    private int zipcode;
    private String Country;


}
