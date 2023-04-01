package com.aws.test.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AbstractAjpProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TomcatContainerConfig {
    @Value("${tomcat.ajp.protocol}")
    private String ajpProtocol;
    @Value("${tomcat.ajp.port}")
    private int ajpPort;
    @Value("${tomcat.ajp.address}")
    private String address;
    @Value("${tomcat.ajp.allowedRequestAttributesPattern}")
    private String allowedRequestAttributesPattern;

    @Bean
    public ServletWebServerFactory servletContainer() {
        log.info("ajpProtocol -> {}", ajpProtocol);
        log.info("ajpPort -> {}", ajpPort);
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();                     
        tomcat.addAdditionalTomcatConnectors(createAjpConnector());
        return tomcat;
    }

    private Connector createAjpConnector() {
        Connector ajpConnector = new Connector(ajpProtocol);
        ajpConnector.setPort(ajpPort);
        ajpConnector.setSecure(false);
        ajpConnector.setAllowTrace(false);
        ajpConnector.setScheme("http");
        ajpConnector.setProperty("address",address);
        ajpConnector.setProperty("allowedRequestAttributesPattern", allowedRequestAttributesPattern);
        ((AbstractAjpProtocol<?>)ajpConnector.getProtocolHandler()).setSecretRequired(false);
        return ajpConnector;
    }
}
