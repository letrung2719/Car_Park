package com.example.carpark.controller;

import com.example.carpark.dto.EmployeeDto;
import com.example.carpark.model.Employee;
import com.example.carpark.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("car-park")
public class EmployeeController {
    private final IEmployeeService employeeService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/list")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employee/get/{employee_id}")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("employee_id") String employee_id) {
        return employeeService.getEmployeeById(Long.parseLong(employee_id));
    }

    @PostMapping("/employee/add")
//    @PreAuthorize("hasAuthority('permission:add')")
    public ResponseEntity<Employee> addNewEmployee(@ModelAttribute EmployeeDto employeeDto) {
        return employeeService.addNewEmployee(employeeDto);
    }

    @DeleteMapping("/employee/delete/{employee_id}")
//    @PreAuthorize("hasAuthority('permission:delete')")
    public ResponseEntity<Map<String, Boolean>> deleteEmployeeById(@PathVariable("employee_id") String employee_id) {
        return employeeService.deleteById(Long.parseLong(employee_id));
    }

    @PutMapping("/employee/edit")
//    @PreAuthorize("hasAuthority('permission:edit')")
    public ResponseEntity<EmployeeDto> editEmployee(@RequestParam Long id, @ModelAttribute EmployeeDto employeeDto) {
        return employeeService.editEmployee(id, employeeDto);
    }
}
