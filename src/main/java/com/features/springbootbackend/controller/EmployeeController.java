package com.features.springbootbackend.controller;

import com.features.springbootbackend.model.Employee;
import com.features.springbootbackend.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeServiceImpl.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee addedEmployee = employeeServiceImpl.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedEmployee);
    }

    @PutMapping("/{sapId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int sapId, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeServiceImpl.updateEmployee(sapId, employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{sapId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int sapId) {
        employeeServiceImpl.deleteEmployee(sapId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Employee>> getAllEmployeesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = employeeServiceImpl.getAllEmployee(pageable);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Employee>> sortEmployee(
            @RequestParam(defaultValue = "firstName") String sortBy,
            @RequestParam(defaultValue = "asc") String direct) {

        Sort.Direction direction = Sort.Direction.ASC;
        if (direct.equalsIgnoreCase("desc")) {
            direction = Sort.Direction.DESC;
        }
        Sort sort = Sort.by(direction, sortBy);
        List<Employee> employees = employeeServiceImpl.sortEmployee(sort);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployee(@RequestParam String firstName) {
        List<Employee> employees = employeeServiceImpl.searchEmployee(firstName);
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employees);
    }
}
