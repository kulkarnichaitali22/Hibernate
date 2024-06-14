package com.super20.main;

import java.math.BigDecimal;

import com.super20.dao.DaoImpliment;
import com.super20.entity.Department;
import com.super20.entity.Employee;

public class Main {
	
	public static void main(String[] args) {
		
		Department d1 = new Department("HR", "Pune", null);
		Department d2 = new Department("Testing", "mumbai", null);
		Department d3 = new Department("Development", "nashik", null);
		Department d4 = new Department("marketing", "dhule", null);
		Department d5 = new Department("accounts", "bandra", null);

		
		Employee e1 = new Employee("Chaitali","Kulkarni","chaitali@gamil.com",BigDecimal.valueOf(3000.45),d1);
		Employee e2 = new Employee("Shubham","Manikeri","shubham@gamil.com",BigDecimal.valueOf(6000.45),d2);
		Employee e3 = new Employee("Sanika","Sarwadnya","sanika@gamil.com",BigDecimal.valueOf(76000.45),d3);
		Employee e4 = new Employee("poonam","matle","poonam@gamil.com",BigDecimal.valueOf(4500.45),d4);
		Employee e5 = new Employee("Lucky","jutik","lucky@gamil.com",BigDecimal.valueOf(23000.45),d5);
		
		DaoImpliment dao = new DaoImpliment();
		
		/*
		 * dao.addDepartment(d1); dao.addDepartment(d2); dao.addDepartment(d3);
		 * dao.addDepartment(d4); dao.addDepartment(d5);
		 * 
		 * 
		 * dao.addEmployee(e1); dao.addEmployee(e2); dao.addEmployee(e3);
		 * dao.addEmployee(e4);
		 dao.addEmployee(e5);*/

		//dao.getEmploye();
		
		dao.getSalary(5000);
		
		//dao.getAscOrderLastName();
		
		//dao.getDeptPagination();
		
		//dao.getFirstALastName();
		
		//dao.getCountEmp();
		
		//dao.getMaxsalaryEmp();
		
		//dao.getSumSalary();
		
		dao.getDistinctCount();
		
	}

}
