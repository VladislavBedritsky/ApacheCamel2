package org.example.camel.service;

import org.example.camel.dto.Employee;

import java.util.List;

public interface MyService {

    void print(String content);

    List<Employee> getListOfEmployeesFromCsv(String employeesFromCsv);
}
