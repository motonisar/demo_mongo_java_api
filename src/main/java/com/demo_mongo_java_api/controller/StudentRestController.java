package com.demo_mongo_java_api.controller;

import com.demo_mongo_java_api.dto.Student;
import com.demo_mongo_java_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.event.ListDataEvent;
import java.util.List;

@RestController
public class StudentRestController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/api/v1/listallstudents")
    public ResponseEntity<List<Student>> getStudents() {

        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);

    }


    @GetMapping("/api/v1/getstudent/{email}")
    public ResponseEntity<Student> getStudent(@PathVariable String email) {
        Student student = studentRepository.findStudentByEmail(email);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping(path = "/api/v1/addstudent",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> create(@RequestBody Student student) {

        Query query = new Query();
        String email = student.getEmail();
        query.addCriteria(Criteria.where("email").is(email));

        List<Student> students = mongoTemplate.find(query, Student.class);

        if (students.size() > 1) {
            throw new IllegalStateException("found multiple students with same email " + email);
        }
        if(!students.isEmpty()){
            System.out.println("Student already exist" + student) ;
        }else{
            studentRepository.insert(student);
        }
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/v1/deletestudent/{email}")
    public ResponseEntity<Student> deleteStudent(@PathVariable String email) {
        Student student = studentRepository.findStudentByEmail(email);
        if (student == null) {
            return new ResponseEntity<>(studentRepository.deleteByEmail(email), HttpStatus.NOT_FOUND);}
            else{
                return new ResponseEntity<>(studentRepository.deleteByEmail(email), HttpStatus.ACCEPTED);
            }
        }
    }









