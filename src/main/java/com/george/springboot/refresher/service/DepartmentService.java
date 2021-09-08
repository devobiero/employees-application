package com.george.springboot.refresher.service;

import com.george.springboot.refresher.entity.Department;
import com.george.springboot.refresher.exception.NotFoundException;

import java.util.List;

public interface DepartmentService {
  Department saveDepartment(Department department);

  List<Department> getDepartments();

  Department getDepartment(Long id) throws NotFoundException;

  Department getDepartmentByName(String name);
}
