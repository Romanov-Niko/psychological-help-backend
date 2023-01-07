package com.kpi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kpi.dto.response.UserResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql({"/fixtures/users_fixture.sql"})
public class UserControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void shouldReturnUserById() throws Exception {
    UserResponseDto expected =
        UserResponseDto.builder()
            .id(1)
            .name("user_1")
            .email("email_1")
            .phoneNumber("phone_number_1")
            .role("SPECIALIST")
            .build();

    String token = getToken("email_2", "password");
    String response =
        mockMvc
            .perform(get("/users/1").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()
            .getResponse()
            .getContentAsString();
    UserResponseDto actual = convertResponseToEntity(response);

    assertEquals(expected, actual);
  }

  @Test
  void shouldReturn401UnauthorizedWhenTokenWasNotPassed() throws Exception {
    mockMvc.perform(get("/users/1")).andExpect(status().isUnauthorized());
  }

  @Test
  void shouldReturn403ForbiddenWhenTokenHasNoPermissions() throws Exception {
    String token = getToken("email_3", "password");
    mockMvc
        .perform(get("/users/1").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
        .andExpect(status().isForbidden());
  }

  private String getToken(String email, String password) throws Exception {
    String response =
        mockMvc
            .perform(
                post("/token")
                    .content(
                        String.format(
                            """
                            {
                                 "email": "%s",
                                 "password": "%s"
                            }
                            """,
                            email, password))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()
            .getResponse()
            .getContentAsString();

    return new ObjectMapper().readTree(response).get("token").asText();
  }

  private UserResponseDto convertResponseToEntity(String json) throws JsonProcessingException {
    return new ObjectMapper().readValue(json, UserResponseDto.class);
  }
}
