package org.example.camel.service.impl;

import org.example.camel.dto.Employee;
import org.example.camel.service.MyService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class MyServiceImpl implements MyService {
    @Override
    public void print(String content) {
        System.out.println(content);
    }

    @Override
    public List<Employee> getListOfEmployeesFromCsv(String employeesFromCsv) {

        List<Employee> employees = new ArrayList<>();

        Pattern pattern = Pattern.compile(",");
        try (BufferedReader in = new BufferedReader(new StringReader(employeesFromCsv));) {
            employees =
                        in
                            .lines()
                            .map(line -> {
                                String[] x = pattern.split(line);
                                return new Employee(x[0], x[1], x[2]);
                            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return employees;
    }
}
