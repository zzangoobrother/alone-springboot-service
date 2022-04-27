package com.alone.springboot.alonespringbootservice.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class IndexControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void 메인페이지_로딩() {
    String body = this.restTemplate.getForObject("/", String.class);

    assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
  }
}