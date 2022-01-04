package com.example.carpark.service;

import com.example.carpark.dto.EmployeeDto;
import com.example.carpark.model.Employee;

import java.util.List;

public interface IEmployeeService {

    // get all employees
    List<EmployeeDto> getAllEmployees();

    // add a new employee
    void addNewEmployee(EmployeeDto employeeDto);

    //delete employee by id
    void deleteById(Long employee_id);

    //edit employee by id
    void editEmployee(Long id, EmployeeDto employeeDto);

    //search employee by id
    EmployeeDto searchEmployeeById(Long employee_id);

    //check employee existed
    boolean existsById(Long aLong);

    //convert Entity to DTO
    EmployeeDto mapToDto(Employee employee);

    //convert DTO to Entity
    Employee mapToEntity(EmployeeDto employeeDto);
}
