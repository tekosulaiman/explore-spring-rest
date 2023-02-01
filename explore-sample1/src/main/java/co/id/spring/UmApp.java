package co.id.spring;

import co.id.persistence.setup.MyApplicationContextInitializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ // @formatter:off
    UmContextConfig.class,
    UmPersistenceJpaConfig.class,
    UmServiceConfig.class,
    UmWebConfig.class
})
public class UmApp {
    public static void main(final String... args) {
        new SpringApplicationBuilder(UmApp.class)
                .initializers(new MyApplicationContextInitializer())
                .listeners()
                .run(args);
    }
}