package com.alone.springboot.alonespringbootservice.domain.posts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class PostsRepositoryTest {

  @Autowired
  PostsRepository postsRepository;

  @After
  void cleanup() {
    postsRepository.deleteAll();
  }

  @Test
  void 게시글저장_불러오기() {
    String title = "테스트 게시글";
    String content = "테스트 본문";

    postsRepository.save(Posts.builder()
                            .title(title)
                            .content(content)
                            .author("com@naver.com")
                            .build());

    List<Posts> postsList = postsRepository.findAll();

    Posts posts = postsList.get(0);
    assertThat(posts.getTitle()).isEqualTo(title);
    assertThat(posts.getContent()).isEqualTo(content);
  }

  @Test
  void BaseTimeEntity_등록() {
    LocalDateTime now = LocalDateTime.of(2022, 4, 27, 0, 0, 0);
    postsRepository.save(Posts.builder()
                              .title("title")
                              .content("content")
                              .author("author")
                              .build());

    List<Posts> postsList = postsRepository.findAll();

    Posts posts = postsList.get(0);

    assertThat(posts.getCreateDate()).isAfter(now);
    assertThat(posts.getModifiedDate()).isAfter(now);
  }
}