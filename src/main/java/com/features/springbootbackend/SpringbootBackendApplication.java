package com.features.springbootbackend;

import com.features.springbootbackend.model.Employee;
import com.features.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {

		Employee emp1 = new Employee();
		emp1.setFirstName("Tharun");
		emp1.setLastName("Bhaskar");
		emp1.setDesignation("Software Engineer");
		emp1.setEmailId("bhaskar@gmail.com");
		employeeRepository.save(emp1);

		Employee emp2 = new Employee();
		emp2.setFirstName("Raja");
		emp2.setLastName("Shekar");
		emp2.setDesignation("Analyst");
		emp2.setEmailId("raja@gmail.com");
		employeeRepository.save(emp2);

		Employee emp3 = new Employee();
		emp3.setFirstName("Akira");
		emp3.setLastName("Anand");
		emp3.setDesignation("Project Manager");
		emp3.setEmailId("anand@gmail.com");
		employeeRepository.save(emp3);

	}



}
