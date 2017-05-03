package com.bridgeLab;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(urlPatterns={"/reg"})
public class ServletInsertData extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter printWriter=response.getWriter();
    String name=request.getParameter("nm");
    String cid=request.getParameter("id");
    int id=Integer.parseInt(cid);
    String cage=request.getParameter("age");
    int age=Integer.parseInt(cage);
    String csalary=request.getParameter("sal");
    double salary=Double.parseDouble(csalary);
    String MobNo=request.getParameter("mn");
    String mail=request.getParameter("em");
    String password=request.getParameter("ps");
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    int resultSet;
    String query="insert into login_validation.Customer values(?,?,?,?,?,?,?)";
    try {
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=sanju");
		preparedStatement=connection.prepareStatement(query);
		preparedStatement.setString(1, name);
		preparedStatement.setInt(2, id);
		preparedStatement.setInt(3, age);
		preparedStatement.setDouble(4, salary);
		preparedStatement.setString(5, MobNo);
		preparedStatement.setString(6, mail);
		preparedStatement.setString(7, password);
		resultSet=preparedStatement.executeUpdate();
		if(resultSet==1)
		{
			System.out.println("succssfull");
			response.sendRedirect("ServletLogin.html");
		}
		else
		{
			response.sendRedirect("ServletRegister.html");
		}
		
		
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
    
    
	}

}
