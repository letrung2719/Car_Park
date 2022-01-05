package com.example.carpark.controller;

import com.example.carpark.dto.EmployeeDto;
import com.example.carpark.model.Employee;
import com.example.carpark.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    private final IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/view")
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/add")
    public Employee addNewEmployee(@ModelAttribute EmployeeDto employeeDto) {
        return employeeService.addNewEmployee(employeeDto);
    }

    @GetMapping("/delete/{employee_id}")
    public String deleteEmployeeById(@PathVariable("employee_id") String employee_id) {
        return employeeService.deleteById(Long.parseLong(employee_id));
    }

    @GetMapping("/edit")
    public String editEmployee(@RequestParam Long id, @ModelAttribute EmployeeDto employeeDto) {
        return employeeService.editEmployee(id, employeeDto);
    }

    @GetMapping("search/{employee_id}")
    public EmployeeDto searchEmployeeById(@PathVariable("employee_id") String employee_id) {
        return employeeService.searchEmployeeById(Long.parseLong(employee_id));
    }
}
