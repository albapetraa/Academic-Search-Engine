/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.example.AkademikArama.controller;


import java.io.IOException;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Emree
 */
@Controller
public class IndexController {



    @RequestMapping("/")
    public String home(Map<String, Object> model) throws IOException {

        return "index";
    }

    @RequestMapping("/adminlogin")
    public String adminlogin(Map<String, Object> model) throws IOException {
        
        return "adminlogin";
    }

    @RequestMapping("/welcome")
    public String userlogin(Map<String, Object> model) throws IOException {

        return "welcome";
    }

}
