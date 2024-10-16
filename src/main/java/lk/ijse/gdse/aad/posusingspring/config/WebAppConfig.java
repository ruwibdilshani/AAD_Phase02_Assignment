package lk.ijse.gdse.aad.posusingspring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("lk.ijse.gdse.aad.posusingspring")
@EnableWebMvc
@EnableJpaRepositories("lk.ijse.gdse.aad.posusingspring")
@EnableTransactionManagement
public class WebAppConfig {
}
