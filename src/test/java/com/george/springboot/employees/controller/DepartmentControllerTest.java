package com.george.springboot.employees.controller;

import com.george.springboot.employees.SpringBootEmployeesApplication;
import com.george.springboot.employees.entity.Department;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {SpringBootEmployeesApplication.class})
class IntegrationTests {
  @Value("${local.server.port:0}")
  protected int port;

  @Value("${jwt.token}")
  protected String token;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
    RestAssured.requestSpecification = new RequestSpecBuilder()
        .addHeader("Authorization", String.format("Bearer %s", token))
        .build();
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
