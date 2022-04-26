package com.alone.springboot.alonespringbootservice.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.alone.springboot.alonespringbootservice.domain.posts.Posts;
import com.alone.springboot.alonespringbootservice.domain.posts.PostsRepository;
import com.alone.springboot.alonespringbootservice.web.dto.PostsSaveRequestDto;
import java.util.List;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private PostsRepository postsRepository;

  @After
  void tearDown() {
    postsRepository.deleteAll();
  }

  @Test
  void Posts_등록된다() {
    String title = "title";
    String content = "content";
    PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                                                        .title(title)
                                                        .content(content)
                                                        .author("author")
                                                        .build();

    String url = "http://localhost:" + port + "/api/v1/posts";

    ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isGreaterThan(0L);
    List<Posts> all = postsRepository.findAll();
    assertThat(all.get(0).getTitle()).isEqualTo(title);
    assertThat(all.get(0).getContent()).isEqualTo(content);
  }
}