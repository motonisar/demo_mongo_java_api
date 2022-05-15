package com.demo_mongo_java_api.controller;

import com.demo_mongo_java_api.dto.Address;
import com.demo_mongo_java_api.dto.Student;
import com.demo_mongo_java_api.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(StudentRestController.class)
@ExtendWith(SpringExtension.class)
class StudentRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private MongoRepository mongoRepository;

    @MockBean
    private MongoTemplate mongoTemplate;
    final Address address = new Address(
            "Plymouth",
            15111,
            "USA");
    final ArrayList<String> subjects = new ArrayList<String>(Arrays.asList("Maths", "English", "Science"));

    final  Student student = new Student(
            "jane",
            "dough",
            "jane@gmail.com",
            0,
            "female",
            address,
            subjects,
            100);


    @Test
    void getAllStudentSuccessfullyTest() throws Exception {

        List<Student> responseExpected = new ArrayList<Student>();
        responseExpected.add(student);

        Mockito.when(studentService.getAllStudent()).thenReturn(responseExpected);
        mockMvc.perform(get("/api/v1/listallstudents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].email", Matchers.is("jane@gmail.com")));
    }


    @Test
    void getOneStudentByEmailSuccessfullyTest() throws Exception {

        List<Student> responseExpected = new ArrayList<Student>();
        responseExpected.add(student);

        String email = "jane@gmail.com";

        Mockito.when(studentService.getUniqueStudent(email)).thenReturn(student);

        mockMvc.perform(get("/api/v1/getstudent/{email}", "jane@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("jane"));
    }

    @Test
    public void deleteStudentSucessFully() throws Exception{

        String email = "jane@gmail.com";
        boolean studentDeleted = true;

        Mockito.when(studentService.deleteStudent(email)).thenReturn(studentDeleted);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/deletestudent/{email}","jane@gmail.com"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void insertStudentSucessFully() throws Exception{

        Mockito.when(studentService.insertStudent(student)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/addstudent")
                .content(asJsonString(student))
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}