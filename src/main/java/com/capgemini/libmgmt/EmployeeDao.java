package com.capgemini.libmgmt;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeDao {
	public void createNewEmployee(Connection conn , Scanner sc) throws SQLException {
		Employee emp = new Employee();
		emp.acceptData(sc);
		
		String SQL = "insert into employee(empname , salary) values(?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		pstmt.setString(1, emp.getEmpName());
		pstmt.setInt(2, emp.getSalary());
		
		int result = pstmt.executeUpdate();
		
		if(result == 1) {
			System.out.println("Employee Record created Successfully.");
		}
		else {
			System.out.println("Employee Record is not Created.");
		}
	}
	
	public void displayEmployee(Connection conn) throws SQLException {
		String sQLString = "select * from employee";
		Statement stmt = conn.createStatement();
		ResultSet rSet = stmt.executeQuery(sQLString);
		while(rSet.next()) {
			Employee employee = new Employee();
			employee.setEmpId(rSet.getInt("empid"));
			employee.setEmpName(rSet.getString("empname"));
			employee.setSalary(rSet.getInt("salary"));
			System.out.println(employee);
		}
		
		rSet.close();
	}
	
	public void updateEmployee(Connection conn , Scanner sc) throws SQLException {
		Employee emp = new Employee();
		System.out.println("Enter the Emp ID : ");
		int empID = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the Emp Salary : ");
		int empsalary = sc.nextInt();
		String SQL = "update employee set salary=? where empid=?";
		
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		pstmt.setInt(1, empsalary);
		pstmt.setInt(2, empID);
		
		int result = pstmt.executeUpdate();
		
		if(result == 1) {
			System.out.println("Employee salary Updated Successfully.");
		}
		else {
			System.out.println("Employee salary is not updated.");
		}
	}
	
	public void deleteEmployee(Connection conn , Scanner sc) throws SQLException {
		Employee emp = new Employee();
		System.out.println("Enter the Emp ID : ");
		int empID = sc.nextInt();

		String SQL = "delete from employee where empid=?";
		
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		pstmt.setInt(1, empID);
		
		int result = pstmt.executeUpdate();
		
		if(result == 1) {
			System.out.println("Employee deleted Successfully.");
		}
		else {
			System.out.println("Employee is not deleted.");
		}
	}
	
}
