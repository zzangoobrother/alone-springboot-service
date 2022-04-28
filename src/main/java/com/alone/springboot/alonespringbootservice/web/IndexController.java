package com.alone.springboot.alonespringbootservice.web;

import com.alone.springboot.alonespringbootservice.config.auth.dto.SessionUser;
import com.alone.springboot.alonespringbootservice.service.posts.PostsService;
import com.alone.springboot.alonespringbootservice.web.dto.PostsResponseDto;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

  private final PostsService postsService;
  private final HttpSession httpSession;

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("posts", postsService.findAllDesc());

    SessionUser user = (SessionUser) httpSession.getAttribute("user");

    if (user != null) {
      model.addAttribute("userName", user.getName());
    }

    return "index";
  }

  @GetMapping("/posts/save")
  public String postsSave() {
    return "posts-save";
  }

  @GetMapping("/posts/update/{id}")
  public String postsUpdate(@PathVariable Long id, Model model) {
    PostsResponseDto dto = postsService.findById(id);
    model.addAttribute("post", dto);

    return "posts-update";
  }
}
