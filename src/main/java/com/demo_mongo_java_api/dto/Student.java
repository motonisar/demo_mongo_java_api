package com.demo_mongo_java_api.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
public class Student {
    @MongoId
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String gender;
    Address address;
    private List<String > subjects;
    private int fees;

    public Student(String firstName, String lastName, String email, int age, String gender, Address address, List<String> subjects, int fees) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.subjects = subjects;
        this.fees = fees;
    }
}
