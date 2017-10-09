package com.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class RegisterBean implements DBConnection{
	String fname,lname,address,email,PhNo,password,username;
	int reg_id=0;
	public String registration() {
		Connection con = null;
		try {
			
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
			ds.setServerName(ICSI518_SERVER);
			ds.setPortNumber(ICSI518_PORT);
			ds.setDatabaseName(ICSI518_DB);
			ds.setUser(ICSI518_USER);
			ds.setPassword(ICSI518_PASSWORD);
			con = ds.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into Registration(R_fullname,R_address,R_phno,R_email) values(?,?,?,?)");
            ps.setString(1, fname+" "+lname);
            ps.setString(2, address);
            ps.setString(3, PhNo);
            ps.setString(4, email);
            
            int i=ps.executeUpdate();
            if(i>0) {
            	
            	Statement stmt=con.createStatement();
            	String sql="Select * from Registration where R_email='"+email+"'";
            	ResultSet rs=stmt.executeQuery(sql);
            	if(rs.next()) {
            		reg_id=rs.getInt("id");
            	}
            	int j=0;
            	if(reg_id!=0) {
            		PreparedStatement ps2 = con.prepareStatement("insert into users(username,password,reg_id) values(?,?,?)");
                    ps2.setInt(3, reg_id);
                    ps2.setString(1, username);
                    ps2.setString(2, password);
                    j=ps2.executeUpdate();
            	}
            	if(j>0) {
            		FacesContext facesContext = FacesContext.getCurrentInstance();
            		Flash flash = facesContext.getExternalContext().getFlash();
            		flash.setKeepMessages(true);
            		flash.setRedirect(true);
            		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Registered Succesfully", "Registered Succesfully"));
            		return "success";
            	}
            	
            }
        } catch (Exception e2) {
            System.out.println(e2);
        }
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Flash flash = facesContext.getExternalContext().getFlash();
		flash.setKeepMessages(true);
		flash.setRedirect(true);
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Registered Succesfully", "Registered Succesfully"));
		return "failed";
	}
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getPhNo()
	{
		return PhNo;
	}
	public void setPhNo(String PhNo)
	{
		this.PhNo = PhNo;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
