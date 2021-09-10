package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.model.Company;
import com.example.companyemployeespring.model.Employee;
import com.example.companyemployeespring.security.CurrentUser;
import com.example.companyemployeespring.service.CompanyService;
import com.example.companyemployeespring.service.EmailService;
import com.example.companyemployeespring.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;
    private final CompanyService companyService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @GetMapping("/employees")
    public String getEmployees(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Employee> all = employeeService.findEmployeeByCompanyId(currentUser.getEmployee().getCompany().getId());
        modelMap.addAttribute("employees", all);
        if (!currentUser.getEmployee().getIsActive()){
            modelMap.addAttribute(currentUser.getEmployee());
            return "active";
        }
        log.info("Employee with {} name opened employee page, employee.size = {}", currentUser.getEmployee().getEmail(), all.size());
        return "employees";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(ModelMap modelMap) {
        List<Company> all = companyService.findAll();
        modelMap.addAttribute("companies", all);
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setActiveCode(String.valueOf(UUID.randomUUID()));
        employeeService.save(employee);
        Company company = companyService.getById(employee.getCompany().getId());
        company.setSize(company.getSize() + 1);
        companyService.save(company);
        emailService.sendMessage(employee.getEmail(),"Welcome","Welcome dear " + employee.getName()
                + " to our page, your company is "+ employee.getCompany().getName() + "your activated code is " + employee.getActiveCode());
        return "redirect:/employees";
    }

}
