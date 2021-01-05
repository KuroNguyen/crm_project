package com.myclass.controller;

import com.myclass.common.SessionConstant;
import com.myclass.common.StatusCode;
import com.myclass.common.UrlConstant;
import com.myclass.dto.UserLoginDto;
import com.myclass.service.ServiceResponse;
import com.myclass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 1/4/2021
 * Updated: 1/4/2021
 */
@Component
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    String login() {
        return UrlConstant.URL_LOGIN;
    }

    @GetMapping("/logout")
    String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionConstant.USER_LOGIN) != null) {
            session.removeAttribute(SessionConstant.USER_LOGIN);
        }
        return "redirect:" + UrlConstant.URL_LOGIN;
    }

    @PostMapping("/login")
    String post_login(@RequestParam("email") String email,
                      @RequestParam("password") String password,
                      HttpServletRequest request,
                      ModelMap model) {

        ServiceResponse response = userService.checkLogin(email, password);
        if (response.getStatusCode() == StatusCode.SUCCESS) {
            System.out.println(response.getResObject().toString());
            // Set session with UserLoginDto
            HttpSession session = request.getSession();
            session.setAttribute(SessionConstant.USER_LOGIN, response.getResObject());
            // Redirect to dashboard
            return "redirect:" + UrlConstant.URL_HOME;
        }

        // Send message to login UI
        model.addAttribute("message", response.getResObject());
        return UrlConstant.URL_LOGIN;
    }

}
