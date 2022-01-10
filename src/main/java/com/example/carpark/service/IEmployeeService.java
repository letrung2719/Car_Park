package com.example.carpark.service;

import com.example.carpark.dto.EmployeeDto;
import com.example.carpark.model.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IEmployeeService {

    // get all employees
    ResponseEntity<List<EmployeeDto>> getAllEmployees();

    //get employee by id
    ResponseEntity<EmployeeDto> getEmployeeById(Long employee_id);

    // add a new employee
    ResponseEntity<Employee> addNewEmployee(EmployeeDto employeeDto);

    //delete employee by id
    ResponseEntity<Map<String, Boolean>> deleteById(Long employee_id);

    //edit employee by id
    ResponseEntity<EmployeeDto> editEmployee(Long id, EmployeeDto employeeDto);

    //convert Entity to DTO
    EmployeeDto mapToDto(Employee employee);

    //convert DTO to Entity
    Employee mapToEntity(EmployeeDto employeeDto);
}
