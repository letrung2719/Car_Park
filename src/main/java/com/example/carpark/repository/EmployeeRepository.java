package com.example.carpark.repository;

import com.example.carpark.dto.EmployeeDto;
import com.example.carpark.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}