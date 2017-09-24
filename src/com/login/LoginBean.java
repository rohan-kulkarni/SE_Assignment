package com.login;

public class LoginBean {
String uname, pwd;

    
    public LoginBean() {
    }
    
    public String check(){
        if(uname.equals("admin") && pwd.equals("admin"))
        {
            return "login";
        }
        return "failed";
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
