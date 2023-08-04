package com.features.springbootbackend.service;

import com.features.springbootbackend.exception.ResourceNotFoundException;
import com.features.springbootbackend.model.Employee;
import com.features.springbootbackend.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

   private Employee employee1;
    private Employee employee2;
    @BeforeEach
    void setUp() {
        Employee employee1 = new Employee();
        employee1.setSapId(1);
        employee1.setFirstName("Raja");
        employee1.setLastName("Kumar");
        employee1.setDesignation("Engineer");
        employee1.setEmailId("raja@gmail.com");
        Employee employee2 = new Employee();
        employee2.setSapId(2);
        employee2.setFirstName("Ram");
        employee2.setLastName("Nath");
        employee2.setDesignation("Manager");
        employee2.setEmailId("ramnath@gmail.com");
    }

    @Test
    void testGetAllEmployees() {

        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));


        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(2, result.size());
        assertEquals(employee1, result.get(0));
        assertEquals(employee2, result.get(1));
    }

    @Test
    void testGetAllEmployee() {
        PageRequest pageable = PageRequest.of(0, 10);
        List<Employee> employees = Arrays.asList(employee1, employee2);
        Page<Employee> page = new PageImpl<>(employees);

        when(employeeRepository.findAll(pageable)).thenReturn(page);

        Page<Employee> result = employeeService.getAllEmployee(pageable);
        assertEquals(2, result.getTotalElements());
        assertEquals(employee1, result.getContent().get(0));
        assertEquals(employee2, result.getContent().get(1));
    }

    @Test
    void testAddEmployee() {
        Employee employeeToAdd = new Employee();
        employeeToAdd.setSapId(1);
        employeeToAdd.setFirstName("hari");
        employeeToAdd.setLastName("shankat");
        employeeToAdd.setDesignation("Support Engineer");
        employeeToAdd.setEmailId("hari@example.com");

        when(employeeRepository.save(any(Employee.class))).thenReturn(employeeToAdd);

        Employee result = employeeService.addEmployee(employeeToAdd);

        assertNotNull(result);
        assertEquals(employeeToAdd, result);
    }

    @Test
    void testUpdateEmployee() {
        int sapId = 1;
        Employee employeeToUpdate = new Employee();
        employeeToUpdate.setSapId(1);
        employeeToUpdate.setFirstName("Jaya");
        employeeToUpdate.setLastName("Raj");
        employeeToUpdate.setDesignation("Analyst");
        employeeToUpdate.setEmailId("jaya@gmail.com");
        Optional<Employee> optionalEmployee = Optional.of(employeeToUpdate);

        when(employeeRepository.findById(sapId)).thenReturn(optionalEmployee);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employeeToUpdate);

        Employee result = employeeService.updateEmployee(sapId, employeeToUpdate);

        assertNotNull(result);
        assertEquals(employeeToUpdate, result);
        assertEquals(employeeToUpdate.getLastName(), result.getLastName());
        assertEquals(employeeToUpdate.getDesignation(), result.getDesignation());
        assertEquals(employeeToUpdate.getEmailId(), result.getEmailId());
    }

    @Test
    void testUpdateEmployee_ThrowsResourceNotFoundException() {

        int sapId = 100;
        Employee updatedEmployee = new Employee();
        updatedEmployee.setSapId(1);
        updatedEmployee.setFirstName("Jaya");
        updatedEmployee.setLastName("Raj");
        updatedEmployee.setDesignation("Analyst");
        updatedEmployee.setEmailId("jaya@.com");
        Optional<Employee> optionalEmployee = Optional.empty();

        when(employeeRepository.findById(sapId)).thenReturn(optionalEmployee);

        assertThrows(ResourceNotFoundException.class, () -> employeeService.updateEmployee(sapId, updatedEmployee));
    }

    @Test
    void testDeleteEmployee() {
        int sapId = 1;
        employeeService.deleteEmployee(sapId);
        verify(employeeRepository, times(1)).deleteById(sapId);
    }

    @Test
    void testSearchEmployee() {
        String firstName = "Jaya";
        List<Employee> expectedEmployees = Arrays.asList(employee1);
        when(employeeRepository.findBy(firstName)).thenReturn(expectedEmployees);
        List<Employee> result = employeeService.searchEmployee(firstName);
        assertEquals(1, result.size());
        assertEquals(expectedEmployees.get(0), result.get(0));
    }

    @Test
    void testSortEmployee() {
        Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
        when(employeeRepository.findAll(sort)).thenReturn(Arrays.asList(employee1, employee2));
        List<Employee> result = employeeService.sortEmployee(sort);

        assertEquals(2, result.size());
        assertEquals(employee1, result.get(0));
        assertEquals(employee2, result.get(1));
    }
}
