package co.id.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({ "co.id.web" })
@EnableWebMvc
public class UmWebConfig {
    public UmWebConfig() {
        super();
    }
}