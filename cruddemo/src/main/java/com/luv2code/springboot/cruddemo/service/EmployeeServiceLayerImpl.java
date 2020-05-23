package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceLayerImpl implements EmployeeServiceLayer {
	
	private EmployeeDAO employeeDAO;
	
	@Autowired
	public EmployeeServiceLayerImpl(EmployeeDAO theEmployeeDAO)
	{
		employeeDAO=theEmployeeDAO;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return employeeDAO.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
		// TODO Auto-generated method stub
		return employeeDAO.findById(theId);
	}

	@Override
	@Transactional
	public void save(Employee employee) {
		employeeDAO.save(employee);
		
	}

	@Override
	@Transactional
	public void delete(int theId) {
		employeeDAO.delete(theId);
		
	}
	
	

}
