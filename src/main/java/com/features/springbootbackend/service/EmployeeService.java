package com.features.springbootbackend.service;

import com.features.springbootbackend.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Page<Employee> getAllEmployee(Pageable pageable);
    Employee addEmployee(Employee employee);
    Employee updateEmployee(int sapId, Employee updatedEmployee);
    void deleteEmployee(int sapId);
    List<Employee> searchEmployee(String firstName);
    List<Employee> sortEmployee(Sort sort);

}
