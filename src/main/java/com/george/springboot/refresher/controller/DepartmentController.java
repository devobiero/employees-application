package com.george.springboot.refresher.controller;

import com.george.springboot.refresher.entity.Department;
import com.george.springboot.refresher.exception.NotFoundException;
import com.george.springboot.refresher.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DepartmentController {
  private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

  @Autowired
  private DepartmentService departmentService;

  @PostMapping("/departments")
  public Department createDepartment(@Valid @RequestBody Department department) {
    LOGGER.info("Creating a department");
    return this.departmentService.saveDepartment(department);
  }

  @GetMapping("/departments")
  public List<Department> listDepartments() {
    LOGGER.info("Fetching departments");
    return this.departmentService.getDepartments();
  }

  @GetMapping("/departments/{id}")
  public Department getDepartment(@PathVariable Long id) throws NotFoundException {
    return this.departmentService.getDepartment(id);
  }

  @GetMapping("/department/name/{name}")
  public Department getDepartmentByName(@PathVariable String name) {
    return this.departmentService.getDepartmentByName(name);
  }
}
