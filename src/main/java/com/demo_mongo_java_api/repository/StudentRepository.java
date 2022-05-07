package com.demo_mongo_java_api.repository;

import com.demo_mongo_java_api.dto.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}
