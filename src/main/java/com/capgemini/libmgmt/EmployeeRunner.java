package com.capgemini.libmgmt;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeRunner {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost/example1";
	public static final String USER = "root";
	public static final String PASS = "1911";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		EmployeeDao em = new EmployeeDao();
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch ( Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(URL,USER,PASS)){
			while (true) {
				System.out.println("1. Insert new employee details ");
				System.out.println("2. Retrieve and display all employee details ");
				System.out.println("3. Update the salary of an existing employee based on empId ");
				System.out.println("4. Delete an employee record based on empId ");
				System.out.println("5. Exit the program ");
				System.out.println("enter choice: ");
				int choice= sc.nextInt();
				
				switch(choice)
				{
				case 1:
					em.createNewEmployee(conn, sc);
					break;
					
				case 2:
					em.displayEmployee(conn);
					break;
				case 3: 
					em.updateEmployee(conn, sc);
					break;
				case 4:
					em.deleteEmployee(conn, sc);
					break;
				case 5:
					sc.close();
					System.out.println("Exiting System wait...");
					System.exit(0);
				default: System.out.println("Invalid choice ");
				}
			}
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
 	}
}
