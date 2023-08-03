package com.features.springbootbackend.repository;

import com.features.springbootbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE LOWER(:firstName)")
    List<Employee> findBy(String firstName);
}
