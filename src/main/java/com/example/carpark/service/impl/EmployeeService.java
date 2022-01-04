package com.example.carpark.service.impl;

import com.example.carpark.dto.EmployeeDto;
import com.example.carpark.model.Employee;
import com.example.carpark.repository.EmployeeRepository;
import com.example.carpark.service.IEmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements IEmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    // get all employees
    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // add a new employee
    @Override
    public void addNewEmployee(EmployeeDto employeeDto) {
        employeeRepository.save(this.mapToEntity(employeeDto));
    }

    //delete employee by id
    @Override
    public void deleteById(Long employee_id) {
        employeeRepository.deleteById(employee_id);
    }

    //edit employee by id
    @Override
    public void editEmployee(Long id, EmployeeDto employeeDto) {
            Employee employee = this.mapToEntity(employeeDto);
            Employee edited = employeeRepository.getById(id);
            edited.setAccount(employee.getAccount());
            edited.setDepartment(employee.getDepartment());
            edited.setEmployeeAddress(employee.getEmployeeAddress());
            edited.setEmployeeBirthdate(employee.getEmployeeBirthdate());
            edited.setEmployeeEmail(employee.getEmployeeEmail());
            edited.setEmployeeName(employee.getEmployeeName());
            edited.setEmployeePhone(employee.getEmployeePhone());
            edited.setPassword(employee.getPassword());
            edited.setSex(employee.getSex());
            employeeRepository.save(edited);
    }

    //search employee by id
    @Override
    public EmployeeDto searchEmployeeById(Long employee_id) {
        return this.mapToDto(employeeRepository.getById(employee_id));
    }

    //check employee existed
    @Override
    public boolean existsById(Long aLong) {
        return employeeRepository.existsById(aLong);
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
