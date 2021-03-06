package org.example.camel.service.impl;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:test.xml"})
public class MyServiceImplTest {

    @Autowired
    private CamelContext producerCamelContext;
    @Autowired
    private CamelContext camelFromCsvToJson;
    @Autowired
    private CamelContext camelFromCsvToXml;

    @Test
    public void produceStringToBeanMethod() throws Exception {
        producerCamelContext.start();

        ProducerTemplate producerTemplate = producerCamelContext.createProducerTemplate();
        producerTemplate.sendBody("direct:start", "objectToActiveMQ");
        producerCamelContext.stop();
    }

    @Test
    public void fromCsvToJson() throws Exception {
        camelFromCsvToJson.start();
        Thread.sleep(4000);
        camelFromCsvToJson.stop();
    }

    @Test
    public void fromCsvToXml() throws Exception {
        camelFromCsvToXml.start();
        Thread.sleep(4000);
        camelFromCsvToXml.stop();
    }


}