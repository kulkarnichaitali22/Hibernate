package com.super20.dao;

import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.super20.config.HibernateConfiguration;
import com.super20.entity.Department;
import com.super20.entity.Employee;

public class DaoImpliment {

	SessionFactory sf = HibernateConfiguration.getSessionFactory();

	// add employee
	public String addEmployee(Employee employee) {

		String msg = "";

		try {
			
			// get session object
			Session session = sf.openSession();

			session.save(employee);
			session.beginTransaction().commit();

			msg = "Added!!!";

		} catch (Exception e) {
			e.printStackTrace();
			msg = "Something went wrong!!!";
		}

		return msg;
	}

	// add department
	public String addDepartment(Department department) {

		String msg = "";

		try {

			// get session object
			Session session = sf.openSession();

			session.save(department);
			session.beginTransaction().commit();

			msg = "Added!!!";

		} catch (Exception e) {
			e.printStackTrace();
			msg = "Something went wrong!!!";
		}

		return msg;
	}

	// 1- retrive all employee
	public List<Employee> getEmploye() {
		List<Employee> list;
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(Employee.class); // To get record from DB
		list = criteria.list();
		for(Employee i : list)
		{
			System.out.println(i.getFirstname()+i.getLastname()+i.getEmail());
		}
		return list;
	}

	// 2- salary greater than 5000
	public List<Employee> getSalary(double salary) {
		List<Employee> list;
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.gt("salary", salary));
		list = criteria.list();
		return list;
	}

	// 3- ascending order by last name
	public List<Employee> getAscOrderLastName() {
		List<Employee> list;
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.addOrder(Order.asc("lastname"));
		list = criteria.list();
		return list;

	}

	// 4-pagination
	public List<Department> getDeptPagination() {
		int pageNumber = 6;
		int pageSize = 9;
		int firstResult = (pageNumber - 1) * pageSize;
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(Department.class);
		String hql = "FROM Department";
		Query query = session.createQuery(hql);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		List<Department> list = criteria.list();
		for (Department i : list) {
			System.out.println(i.getName() + " " + i.getId());
		}
		return list;

	}

	// 5- fetching specific column
	public List<Employee> getFirstALastName() {

		Session session = sf.openSession();
		// Transaction tx=session.beginTransaction();
		Query query = session.createQuery("select firstName,lastName from Employee where id=i");

		query.setParameter("i", 5);
		List<Employee> list = query.getResultList();
		return list;

	}

	// 6-count employee
	public Long getCountEmp() {
		Long count;
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setProjection(Projections.rowCount());
		List<Long> list = criteria.list();// here use <long> because count is not specific
		count = list.get(0);// it spcify 0th index
		return count;

	}

	// 7-max salary
	public Double getMaxsalaryEmp() {
		Double maxbalance;
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setProjection(Projections.max("salary"));
		List<Double> list = criteria.list();
		maxbalance = list.get(0);
		return maxbalance;

	}

	// 8-sum of salary
	public double getSumSalary() { // using projection we can get maxBalance from table
		double maxBalance;
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setProjection(Projections.sum("salary"));
		List<Double> list = criteria.list();
		maxBalance = list.get(0);
		return maxBalance;

	}

	// 9- avg salary
	public double getAvgSalary() { // using projection we can get maxBalance from table
		double maxBalance;
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setProjection(Projections.avg("salary"));
		List<Double> list = criteria.list();
		maxBalance = list.get(0);
		return maxBalance;
	}

	// 10- distinct count
	public List<Department> getDistinctCount() {
		long count;
		Session session = sf.openSession();
		Query query = session.createQuery("select count(name)from Department");
		List list = query.list();
//		Criteria criteria = session.createCriteria(Employee.class);
//		cq.select(cb.countDistinct(departmentNamePath));
		return list;

	}

	// 11- special profiles
	public List<Employee> get() {

		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		PropertyProjection firstNameProjection = Projections.property("firstName");
		PropertyProjection lastNameProjection = Projections.property("lastName");
		criteria.setProjection(Projections.projectionList().add(firstNameProjection).add(lastNameProjection));
		List<Employee> resultList = criteria.list();
		return resultList;

	}

	// 12- DTO projection
	public List<Employee> getEmployeeNamesDTO() {
		List<Employee> employeeDTOs;
		Session session = sf.openSession();
		// HQL query to select specific columns (firstName, lastName) and map to
		// EmployeeDTO
		Query<Employee> query = session.createQuery("SELECT NEW Employee(e.firstname, e.lastname) FROM Employee e",
				Employee.class);

		List list = query.list();
		return list;

	}
	
	//13 - Entiry projection
	
}