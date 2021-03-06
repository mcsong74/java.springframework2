package com.cybertek.controller;

import com.cybertek.repository.StudentRepository;
import com.cybertek.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@WebMvcTest({StudentController.class})
class StudentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean //bring instance of student service in Mock MVC
    StudentService studentService;



    @Test
    void getStudent_data() {
        mockMvc
    }
}