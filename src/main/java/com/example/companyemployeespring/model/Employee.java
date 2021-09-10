package com.example.companyemployeespring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private int salary;
    private String position;
    @ManyToOne
    private Company company;
    @Enumerated(value = EnumType.STRING)
    private EmployeeType employeeType;
    private String activeCode;
    private Boolean isActive = false;
}
