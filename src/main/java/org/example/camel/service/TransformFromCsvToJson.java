package org.example.camel.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.example.camel.dto.Employee;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TransformFromCsvToJson implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String payload = exchange.getIn().getBody(String.class);


        Pattern pattern = Pattern.compile(",");
        try (BufferedReader in = new BufferedReader(new StringReader(payload));) {
            List< Employee > employees =
                        in
                            .lines()
                            .map(line -> {
                    String[] x = pattern.split(line);
            return new Employee(x[0], x[1], x[2]);
    }).collect(Collectors.toList());

            ObjectMapper mapper = new ObjectMapper();
            mapper.
            String json = mapper.writeValueAsString(employees);

            exchange.getIn().setBody(json);
        }

    }
}
