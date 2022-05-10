package com.demo_mongo_java_api.controller;

import com.demo_mongo_java_api.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentRestController {
    @Autowired
    MongoRepository repository;

    @GetMapping("/api/v1/listallstudents")
    public ResponseEntity<List<Student>> getStudents() {

        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);

    }

    @PostMapping(path = "/api/v1/addstudent",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> create(@RequestBody Student student) {
        repository.insert(student);
            return new ResponseEntity<>(student, HttpStatus.CREATED);
        }
    }







