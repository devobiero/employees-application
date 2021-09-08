package com.george.springboot.refresher.repository;

import com.george.springboot.refresher.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
  @Query("select d from Department d where d.name like ?1")
  Department findByDepartmentName(String name);
}
