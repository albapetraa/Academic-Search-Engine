/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.example.AkademikArama.controller;

import com.example.AkademikArama.repository.UserRepo;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Emree
 */
@Controller
public class AdminLoginController {

    @Autowired
    UserRepo uRepo;

    @RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
    public String adminlogin(Map<String, Object> model, @RequestParam(required = false, name = "userMail") String userMail,
            @RequestParam(required = false, name = "userPassword") String userPass,HttpServletRequest request,ModelMap mod) throws IOException {
        int count = uRepo.checkLogin(userMail, userPass);
        if (count > 0) {
            HttpSession session = request.getSession();
            session.setAttribute("sessionUser", userMail);
            return "redirect:/adminhome";
        }
        mod.addAttribute("loginMessage","Giriş bilgileriniz yanlış. Lütfen kontrol ediniz.");
        return "adminlogin";
    }
      @RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
    public String adminLogin(Map<String, Object> model, HttpServletRequest request) throws IOException {
        if (request.getSession().getAttribute("sessionUser") != null) {
            return "redirect:/adminhome";
        }
        return "adminlogin";
    }
}
