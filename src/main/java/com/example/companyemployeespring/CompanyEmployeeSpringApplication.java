package com.example.companyemployeespring;

import com.example.companyemployeespring.model.Employee;
import com.example.companyemployeespring.model.EmployeeType;
import com.example.companyemployeespring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CompanyEmployeeSpringApplication implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(CompanyEmployeeSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (!employeeRepository.findByEmail("admin").isPresent()) {
            employeeRepository.save(Employee.builder()
                    .email("admin")
                    .phoneNumber("+37441824704")
                    .surname("admin")
                    .name("admin")
                    .password(passwordEncoder.encode("admin"))
                    .position("admin")
                    .employeeType(EmployeeType.ADMIN)
                    .companyId(5)
                    .build());
        }
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}