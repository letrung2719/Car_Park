package com.example.carpark.service.impl;

import com.example.carpark.dto.EmployeeDto;
import com.example.carpark.exception.ResourceNotFoundException;
import com.example.carpark.model.Employee;
import com.example.carpark.repository.EmployeeRepository;
import com.example.carpark.service.IEmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements IEmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    // get all employees
    @Override
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> list = employeeRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    //get employee by id
    @Override
    public ResponseEntity<EmployeeDto> getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This id " + id + " does not exists!"));
        EmployeeDto employeeDto = this.mapToDto(employee);
        return ResponseEntity.ok(employeeDto);
    }

    // add a new employee
    @Override
    public ResponseEntity<Employee> addNewEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.save(this.mapToEntity(employeeDto));
        return ResponseEntity.ok(employee);
    }

    //delete employee by id
    @Override
    public ResponseEntity<Map<String, Boolean>> deleteById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This id " + id + " does not exists!"));
        employeeRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //edit employee by id
    @Override
    public ResponseEntity<EmployeeDto> editEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This id " + id + " does not exists!"));
        employee.setAccount(employeeDto.getAccount());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setEmployeeAddress(employeeDto.getEmployeeAddress());
        employee.setEmployeeBirthdate(employeeDto.getEmployeeBirthdate());
        employee.setEmployeeEmail(employeeDto.getEmployeeEmail());
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setEmployeePhone(employeeDto.getEmployeePhone());
        employee.setPassword(employeeDto.getPassword());
        employee.setSex(employeeDto.getSex());

        Employee edited = employeeRepository.save(employee);
        EmployeeDto response = this.mapToDto(edited);
        return ResponseEntity.ok(response);
    }

    //convert Entity to DTO
    @Override
    public EmployeeDto mapToDto(Employee employee) {
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

    //convert DTO to Entity
    @Override
    public Employee mapToEntity(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        return employee;
    }
}
