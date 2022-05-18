package com.demo_mongo_java_api.controller;

import com.demo_mongo_java_api.dto.Student;
import com.demo_mongo_java_api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentRestController {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    StudentService studentService;

    @GetMapping("/api/v1/listallstudents")
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<>(studentService.getAllStudent(), HttpStatus.OK);

    }


    @GetMapping("/api/v1/getstudent/{email}")
    public ResponseEntity<Student> getStudent(@PathVariable String email) {
        Student student = studentService.getUniqueStudent(email);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping(path = "/api/v1/addstudent",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> create(@RequestBody Student student) {

        studentService.insertStudent(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/v1/deletestudent/{email}")
    public ResponseEntity<String> deleteStudent(@PathVariable String email) {

       boolean studentDeleted = studentService.deleteStudent(email);

       if (studentDeleted){
        return new ResponseEntity<>("Sucessfully Deleted", HttpStatus.ACCEPTED);
       }else{
           return new ResponseEntity<>("Student doesn't exist", HttpStatus.NOT_FOUND);
       }
    }

    }









