package com.demo_mongo_java_api.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepositoryTest;

    @Test
    void itShouldCheckIfStudentExistByEmail() {
        //given
        String email="jamie-dough@gmail.com";
        //when

        //then
    }

    @Test
    void deleteByEmail() {
    }
}