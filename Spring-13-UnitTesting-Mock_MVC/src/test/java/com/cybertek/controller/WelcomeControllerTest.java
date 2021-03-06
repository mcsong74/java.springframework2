package com.cybertek.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(WelcomeController.class) //only test executes only in WelcomeController
//@SpringBootTest  //either one annotation
class WelcomeControllerTest {

    @Autowired
    private MockMvc mockMvc; //for Mock API



    @Test
    void welcome() throws Exception {
        //call /welcome end point
        //1. build request end point
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/welcome").accept(MediaType.APPLICATION_JSON);
        //2. perform mock end point and return the result
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        //and verify "welcome" returned
        assertEquals("welcome", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void welcome2(){
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/welcome").accept(MediaType.APPLICATION_JSON);



    }




}