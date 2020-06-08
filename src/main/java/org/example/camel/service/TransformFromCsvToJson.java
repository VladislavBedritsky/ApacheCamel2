package org.example.camel.service;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Service;

@Service
public class TransformFromCsvToJson implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {

    }
}
