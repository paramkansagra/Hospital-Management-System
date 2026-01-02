package com.paramkansagra.Hospital.Management.System.repository;

import com.paramkansagra.Hospital.Management.System.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}