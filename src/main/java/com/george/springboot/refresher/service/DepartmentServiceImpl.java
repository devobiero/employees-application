package com.george.springboot.refresher.service;

import com.george.springboot.refresher.entity.Department;
import com.george.springboot.refresher.exception.NotFoundException;
import com.george.springboot.refresher.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
  @Autowired
  private DepartmentRepository departmentRepository;

  @Override
  public Department saveDepartment(Department department) {
    return this.departmentRepository.save(department);
  }

  @Override
  public List<Department> getDepartments() {
    return this.departmentRepository.findAll();
  }

  @Override
  public Department getDepartment(Long id) throws NotFoundException {
    return this.departmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Department not found"));
  }

  @Override
  public Department getDepartmentByName(String name) {
    return this.departmentRepository.findByDepartmentName(name);
  }
}
