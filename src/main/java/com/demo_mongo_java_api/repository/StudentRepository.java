package com.demo_mongo_java_api.repository;

import com.demo_mongo_java_api.dto.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {

    Student findStudentByEmail(String email);
}
