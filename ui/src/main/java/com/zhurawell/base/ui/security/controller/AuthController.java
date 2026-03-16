package com.zhurawell.base.ui.security.controller;

import com.zhurawell.base.api.security.jwt.JwtTokenProvider;
import com.zhurawell.base.service.exception.ErrorCodes;
import com.zhurawell.base.ui.model.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Value("${jwt.header}")
    private String authorizationHeader;

    @PostMapping("/login")
    public String login(@ModelAttribute LoginModel loginModel, HttpServletResponse response) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginModel.getLogin(), loginModel.getPassword()));
        Pair<String, String> tokens = jwtTokenProvider.createAccessAndRefreshTokens(loginModel.getLogin());
        Cookie cookie = new Cookie(authorizationHeader, tokens.getFirst());
        cookie.setMaxAge(Integer.MAX_VALUE); //seconds
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return "redirect:/home";
    }

    @ExceptionHandler(Exception.class)
    public String handleError(Exception ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("loginError", true);
        return "login";
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String handleBuilderError(BadCredentialsException ex, Model model) {
        model.addAttribute("errorMessage", ErrorCodes.C_102.getMessage());
        model.addAttribute("loginError", true);
        return "login";
    }


}
