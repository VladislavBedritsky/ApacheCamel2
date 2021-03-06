package org.example.camel.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.example.camel.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransformFromCsvToJson implements Processor {

    @Autowired
    private MyService myService;

    @Override
    public void process(Exchange exchange) throws Exception {
        String payload = exchange.getIn().getBody(String.class);

        List<Employee> employees = myService.getListOfEmployeesFromCsv(payload);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(employees);

        exchange.getIn().setBody(json);
    }


}
