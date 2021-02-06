package es.santiagobarquero.denunciasocial;

import java.util.Arrays;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import es.santiagobarquero.denunciasocial.auxiliary.LogAction;

@SpringBootApplication
@ComponentScan({"es.santiagobarquero.denunciasocial"})
@EntityScan("es.santiagobarquero.denunciasocial.api.model.entity")
@EnableJpaRepositories("es.santiagobarquero.denunciasocial.api.model.repository")
@PropertySource("classpath:application.properties")
@JsonPOJOBuilder

public class ApplicationCoreRunner {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ApplicationCoreRunner.class, args);

		Logger logger = LogAction.getLogger(ApplicationCoreRunner.class);
		
		logger.info("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			logger.info(beanName);
		}
	}
	
}
