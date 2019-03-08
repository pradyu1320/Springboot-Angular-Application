package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repo.EmployeeRepository;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	private EmployeeRepository empRepository;
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		System.out.println("Getting all employee information");
		List<Employee> employees = new ArrayList<>();
		empRepository.findAll().forEach(employees::add);
		return employees;
	}
	@PostMapping("/employees/create")
	public Employee createEmployee(@RequestBody Employee emp) {
		Employee employee = empRepository.save(new Employee(emp.getFirstName(),emp.getLastName(),emp.getAge(),emp.getEmail()));
		return employee;
	}
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("id")long id){
		System.out.println("Delete Employee of id " +id);
		empRepository.deleteById(id);
		return new ResponseEntity<>("Employee has been deleted!",HttpStatus.OK);
	}
	@DeleteMapping("/employees/delete")
	public ResponseEntity<String> deleteAllEmployees(){
		System.out.println("Delete All Employees...");
		empRepository.deleteAll();
		return new ResponseEntity<>("All Employees have been deleted",HttpStatus.OK);
	}
	@GetMapping("/employees/age/{age}")
	public List<Employee> findEmployeeById(@PathVariable("age")int age){
		List<Employee> employees = empRepository.findEmployeeByAge(age);
		return employees;
	}
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id")long id,@RequestBody Employee empDetails){
		System.out.println("Update employee for id :" +id);
		Optional<Employee> empdata = empRepository.findById(id);
		if(empdata.isPresent()) {
			Employee emp = empdata.get();
			emp.setFirstName(empDetails.getFirstName());
			emp.setLastName(empDetails.getLastName());
			emp.setEmail(empDetails.getEmail());
			emp.setAge(empDetails.getAge());
			emp.setActive(empDetails.isActive());
			return new ResponseEntity<>(empRepository.save(emp),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
