package com.demo_mongo_java_api.service;

import com.demo_mongo_java_api.dto.Student;
import com.demo_mongo_java_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

   @Autowired
    StudentRepository studentRepository;
   public List<Student> getAllStudent(){

       return studentRepository.findAll();
   }

   public Student getUniqueStudent(String email){

       Student student = studentRepository.findStudentByEmail(email);
       return student;
   }

   public Student insertStudent(Student student){

       String email;
       email = student.getEmail();
       Student studentExist = studentRepository.findStudentByEmail(email);

       if(studentExist == null){
           studentRepository.insert(student);
       }else{
           throw new IllegalStateException("Multiple students found with email " + email);
       }
       return student;
   }

   public boolean deleteStudent(String email){
       boolean studentExist;

       Student studentFound = studentRepository.findStudentByEmail(email);
       if(studentFound == null) {
           studentExist = false;
       }else{
           studentRepository.deleteByEmail(email);
           studentExist = true;
       }
       return studentExist;
   }
}
