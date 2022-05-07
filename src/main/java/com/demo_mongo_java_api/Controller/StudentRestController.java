package com.demo_mongo_java_api.Controller;

import com.demo_mongo_java_api.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.ServerException;
import java.util.List;

@RestController
public class StudentRestController {

    @GetMapping("allstudents")
    public ResponseEntity<List<Student>> getStudents() {

        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);

    }

    @Autowired
    MongoRepository repository;

    @PostMapping(path = "addstudent",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> create(@RequestBody Student student) throws Exception {
        repository.insert(student);
        if (student == null) {
            throw new Exception("Empty User");
        } else {
            return new ResponseEntity<>(student, HttpStatus.CREATED);
        }
    }


}
