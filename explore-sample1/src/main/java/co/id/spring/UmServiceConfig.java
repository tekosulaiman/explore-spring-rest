package co.id.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "co.id.service" })
public class UmServiceConfig {
    public UmServiceConfig() {
        super();
    }
}