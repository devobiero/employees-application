package com.george.springboot.refresher.controller;

import com.george.springboot.refresher.SpringBootRefresherApplication;
import com.george.springboot.refresher.entity.Department;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {SpringBootRefresherApplication.class})
class IntegrationTests {
  @Value("${local.server.port:0}")
  protected int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }
}

class DepartmentControllerTest extends IntegrationTests {
  @Test
  public void canCreateDepartment() {
    Department department = Department
        .builder()
        .address("Nairobi, Kenya")
        .code("Tech")
        .name("Engineering")
        .id(1L)
        .build();

    given().
        contentType(ContentType.JSON).
        body(department).
        when().
        post("/departments").
        then().
        statusCode(200);
  }
}
