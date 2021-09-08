package com.george.employees.service;

import com.george.employees.entity.Department;
import com.george.employees.exception.NotFoundException;

import java.util.List;

public interface DepartmentService {
  Department saveDepartment(Department department);

  List<Department> getDepartments();

  Department getDepartment(Long id) throws NotFoundException;

  Department getDepartmentByName(String name);
}
