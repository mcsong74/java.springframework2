package com.cybertek.controller;

import com.cybertek.entity.Student;
import com.cybertek.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    Student getStudent_service(){
        Student student = new Student("Jan", "Smith", 20);
        return student;
    }

    @GetMapping("/data")
    List<Student> getStudent_data(){
        return studentService.getStudent_data();
    }





}
