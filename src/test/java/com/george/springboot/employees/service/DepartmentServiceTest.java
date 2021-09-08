package com.george.springboot.employees.service;

import com.george.springboot.employees.entity.Department;
import com.george.springboot.employees.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class DepartmentServiceTest {
  @Autowired
  private DepartmentService departmentService;

  @MockBean
  private DepartmentRepository departmentRepository;

  @BeforeEach
  void setUp() {
    Department department = Department
        .builder()
        .address("Nairobi, Kenya")
        .code("Tech")
        .name("Engineering")
        .id(1L)
        .build();

    when(departmentRepository.findByDepartmentName("Engineering")).thenReturn(department);
  }

  @Test
  @DisplayName("Get department by name")
  public void canFetchDepartmentByName() {
    Department found = departmentService.getDepartmentByName("Engineering");
    assertEquals("Engineering", found.getName());
  }
}
