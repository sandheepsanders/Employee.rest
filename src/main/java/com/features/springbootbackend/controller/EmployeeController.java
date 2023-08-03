package com.features.springbootbackend.controller;

import com.features.springbootbackend.exception.ResourceNotFoundException;
import com.features.springbootbackend.model.Employee;
import com.features.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/{page}/{size}")
    public List<Employee> allEmployees(@PathVariable int page,@PathVariable int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("firstName"));
        return employeeRepository.findAll(pageable).get().toList();
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("{sapId}")
    public Employee updateEmployee(@PathVariable int sapId, @RequestBody Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(sapId);
        Employee employee = optionalEmployee.orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with sapId: " + sapId));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setDesignation(updatedEmployee.getDesignation());
        employee.setEmailId(updatedEmployee.getEmailId());
        return employeeRepository.save(employee);
    }

    @DeleteMapping("{sapId}")
    public void deleteEmployee(@PathVariable int sapId) {
        if (employeeRepository.existsById(sapId)) {
            employeeRepository.deleteById(sapId);
        } else {
            throw new ResourceNotFoundException("Employee does not exist with sapId: " + sapId);
        }
    }

    @GetMapping("/search")
    public List<Employee> searchEmployees(@RequestParam String firstName) {
        return employeeRepository.findBy(firstName);
    }

}




