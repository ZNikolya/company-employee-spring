package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.model.Employee;
import com.example.companyemployeespring.model.Message;
import com.example.companyemployeespring.security.CurrentUser;
import com.example.companyemployeespring.service.EmployeeService;
import com.example.companyemployeespring.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final EmployeeService employeeService;
    private final MessageService messageService;

    @GetMapping("/sendMessage")
    public String getAllEmployees(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Employee> all = employeeService.findAll();
        modelMap.addAttribute("employees", all);
        modelMap.addAttribute("id", currentUser.getEmployee().getId());
        return "/messages";
    }

    @GetMapping("/allMessages")
    public String getAllMessages(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Message> all = messageService.findAllMessagesByToId(currentUser.getEmployee().getId());
        for (Message message : all) {
            Optional<Employee> employeeById = employeeService.findEmployeeById(message.getFromId());
            message.setFromEmployee(employeeById.get());
        }
        modelMap.addAttribute("messages", all);
        return "/showMessages";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@ModelAttribute Message message) {
        messageService.saveMessage(message);
        return "redirect:/employees";
    }

}
