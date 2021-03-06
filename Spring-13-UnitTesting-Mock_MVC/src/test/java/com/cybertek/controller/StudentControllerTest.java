package com.cybertek.controller;

import com.cybertek.entity.Student;
import com.cybertek.repository.StudentRepository;
import com.cybertek.service.StudentService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
@WebMvcTest({StudentController.class})
class StudentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean //bring instance of student service in Mock MVC
    StudentService studentService;


    @Test
    void jsonAssert() throws JSONException {
        String actual = "{id: 0,firstName: Jan,lastName: Smith,age: 20}";
        String expected = "{id: 0,firstName: Jan,lastName: Smith,age: 20}";
        JSONAssert.assertEquals(expected, actual, true);
    }



    @Test
    void getStudent_data() throws Exception {
        //mock the service
        when(studentService.getStudent_data()).thenReturn(Arrays.asList(new Student("ozzy", "can", 20),
                new Student("tom", "hanks", 50)));
        mockMvc.perform(MockMvcRequestBuilders.get("/data").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{id:null,firstName:ozzy,lastName:can,age:20},{id:null,firstName:tom,lastName:hanks,age:50}]"))
                .andReturn();

    }

}