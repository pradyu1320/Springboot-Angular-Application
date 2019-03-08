package com.example.demo.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,Long>{
	List<Employee> findEmployeeByAge(int age);
}
