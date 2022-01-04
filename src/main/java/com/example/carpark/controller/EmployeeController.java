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
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("view")
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("add")
    public String addNewEmployee(@ModelAttribute EmployeeDto employeeDto) {
        employeeService.addNewEmployee(employeeDto);
        return "Add new employee successfully!";
    }

    @GetMapping("delete/{employee_id}")
    public String deleteEmployeeById(@PathVariable("employee_id") String employee_id) {
        if(employeeService.existsById(Long.parseLong(employee_id))){
            employeeService.deleteById(Long.parseLong(employee_id));
            return "Delete employee successfully!";
        }else{
            return "This id is not existed!";
        }
    }

    @GetMapping("edit")
    public String editEmployee(@RequestParam Long id, @ModelAttribute EmployeeDto employeeDto) {
        if(employeeService.existsById(id)){
            employeeService.editEmployee(id, employeeDto);
            return "Edit employee successfully!";
        }else{
            return "This id is not existed!";
        }
    }

    @GetMapping("search/{employee_id}")
    public EmployeeDto searchEmployeeById(@PathVariable("employee_id") String employee_id) {
        if(employeeService.existsById(Long.parseLong(employee_id))){
            return employeeService.searchEmployeeById(Long.parseLong(employee_id));
        }else{
            return null;
        }
    }
}
