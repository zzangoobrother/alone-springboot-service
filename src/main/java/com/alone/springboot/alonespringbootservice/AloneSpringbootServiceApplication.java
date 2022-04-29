package com.alone.springboot.alonespringbootservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing
@SpringBootApplication
public class AloneSpringbootServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(AloneSpringbootServiceApplication.class, args);
  }

}
