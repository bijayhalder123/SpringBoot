package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeServiceLayer;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	//inject employee service
	private EmployeeServiceLayer employeeServiceLayer;
	
	
	@Autowired
	public EmployeeRestController(EmployeeServiceLayer theEmployeeServiceLayer)
	{
		employeeServiceLayer=theEmployeeServiceLayer;
	}
	
	//expose /employees and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll()
	{
		return employeeServiceLayer.findAll();
	}
	
	@GetMapping("/employees/{theId}")
	public Employee findById(@PathVariable int theId)
	{
		Employee employee = employeeServiceLayer.findById(theId);
		
		if (employee== null)
		{
			throw new RuntimeException("WRONG ID"+theId);
		}
		
		return employee;
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee)
	{
		employee.setId(0);
		employeeServiceLayer.save(employee);
		
		return employee;
		
	}
	
	@PutMapping("/employees")
	public Employee update(@RequestBody Employee employee)
	{
		
		employeeServiceLayer.save(employee);
		
		return employee;
	}
	@DeleteMapping("/employees/{theId}")
	public String delete(@PathVariable int theId)
	{
		Employee employee = employeeServiceLayer.findById(theId);
		
		if (employee == null)
		{
			throw new RuntimeException("Wrng Input"+theId);
		}
		employeeServiceLayer.delete(theId);
		
		return "Deleted Employee "+theId;
	}
}
