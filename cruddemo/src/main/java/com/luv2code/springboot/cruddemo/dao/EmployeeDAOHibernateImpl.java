package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	//define field for entity manager
	
	private EntityManager entityManager;
	
	//set up constructor injection
	
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager)
	{
		entityManager=theEntityManager;
	}
	
	@Override
	// removed  @Transactional as it will be handled by service class
	public List<Employee> findAll() {
		
		//get the current hibernate session
		Session session = entityManager.unwrap(Session.class);
		
		//create query
		 Query<Employee> theQuery = session.createQuery("from Employee", Employee.class);
		
		//execute the query
		List<Employee> employees = theQuery.getResultList();
		 
		//return the results
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		//create session
		Session session = entityManager.unwrap(Session.class);
		
		//get employee by id
		Employee theEmployee = session.get(Employee.class, theId);
		
		//return employee
		return theEmployee;
	}

	@Override
	public void save(Employee employee) {
		
		//create session
		Session session = entityManager.unwrap(Session.class);
		
		//save employee
		session.saveOrUpdate(employee);
		
		
		
	}

	@Override
	public void delete(int theId) {
		
		//create session
		Session session = entityManager.unwrap(Session.class);
		
		//delete employee
		Query query= session.createQuery("delete from Employee where id=:theId");
		
		query.setParameter("theId", theId);
		
		query.executeUpdate();
		
	}

}
