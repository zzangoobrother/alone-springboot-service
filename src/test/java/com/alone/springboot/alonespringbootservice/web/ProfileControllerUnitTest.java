package com.alone.springboot.alonespringbootservice.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

class ProfileControllerUnitTest {

  @Test
  void real_profile이_조회된다() {
    String expectedProfile = "real";
    MockEnvironment env = new MockEnvironment();
    env.addActiveProfile(expectedProfile);
    env.addActiveProfile("oauth");
    env.addActiveProfile("real-db");

    ProfileController controller = new ProfileController(env);

    String profile = controller.profile();

    assertThat(profile).isEqualTo(expectedProfile);
  }

  @Test
  void real_profile이_없으면_첫_번째가_조회된다() {
    String expectedProfile = "oauth";
    MockEnvironment env = new MockEnvironment();

    env.addActiveProfile(expectedProfile);
    env.addActiveProfile("real-db");

    ProfileController controller = new ProfileController(env);

    String profile = controller.profile();

    assertThat(profile).isEqualTo(expectedProfile)
    ;  }

  @Test
  void active_profile이_없으면_default가_조회된다() {
    String expectedProfile = "default";
    MockEnvironment env = new MockEnvironment();
    ProfileController controller = new ProfileController(env);

    String profile = controller.profile();

    assertThat(profile).isEqualTo(expectedProfile);
  }
}
