package com.features.springbootbackend.service;

import com.features.springbootbackend.exception.ResourceNotFoundException;
import com.features.springbootbackend.model.Employee;
import com.features.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Page<Employee> getAllEmployee(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(int sapId, Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(sapId);
        Employee employee = optionalEmployee.orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with SapID:" + sapId));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setDesignation(updatedEmployee.getDesignation());
        employee.setEmailId(updatedEmployee.getEmailId());
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(int sapId) {
        employeeRepository.deleteById(sapId);
    }

    public List<Employee> searchEmployee(String firstName) {
        return employeeRepository.findBy(firstName);
    }

    public List<Employee> sortEmployee(Sort sort) {
        return employeeRepository.findAll(sort);
    }
}
