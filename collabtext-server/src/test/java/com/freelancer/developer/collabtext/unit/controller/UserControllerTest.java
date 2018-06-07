package com.freelancer.developer.collabtext.unit.controller;

import com.freelancer.developer.collabtext.controller.UserController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Test
    public void getAllUsers() {

    }
}
