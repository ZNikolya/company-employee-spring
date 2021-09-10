package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.security.CurrentUser;
import com.example.companyemployeespring.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ActiveController {
    private final EmployeeService employeeService;
    @PostMapping("/active")
    public String activeUser(@RequestParam ("text") String text, @AuthenticationPrincipal CurrentUser currentUser){
       System.out.println( currentUser.getEmployee().getActiveCode());
        if (text.equals(currentUser.getEmployee().getActiveCode())){
            currentUser.getEmployee().setIsActive(true);
            employeeService.save(currentUser.getEmployee());
            return "index";
        }
            return "active";
    }

}
