package com.Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public class LoginBean implements DBConnection{
	String uname, pwd;

	public LoginBean() {
	}

	public String check() {
		Connection con = null;
		try {
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
			ds.setServerName(ICSI518_SERVER);
			ds.setPortNumber(ICSI518_PORT);
			ds.setDatabaseName(ICSI518_DB);
			ds.setUser(ICSI518_USER);
			ds.setPassword(ICSI518_PASSWORD);
			con = ds.getConnection();
			Statement stmt = con.createStatement();
			String sql = "select * from users where username='"+uname+"' and password='"+pwd+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", getUname()); 
				return "success";
			}
			
		} catch (Exception e2) {
			System.out.println(e2);
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Invalid Username or password", "Invalid Username or Password"));
		return "failed";
	}
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "logout";
	}
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
