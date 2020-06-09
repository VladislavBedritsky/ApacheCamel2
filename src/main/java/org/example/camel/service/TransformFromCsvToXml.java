package org.example.camel.service;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.example.camel.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransformFromCsvToXml implements Processor {

    @Autowired
    private MyService myService;

    @Override
    public void process(Exchange exchange) throws Exception {
        String payload = exchange.getIn().getBody(String.class);

        List<Employee> employees = myService.getListOfEmployeesFromCsv(payload);

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String xml = xmlMapper.writeValueAsString(employees);

        exchange.getIn().setBody(xml);
    }
}
