package com.example.a76773.myapplication.BuValue;

public class RegisiterValue {
    public  RegisiterValue(){}
    //   账号userId，密码passWord，姓名name，专业subject，电话phone，QQ号qq,地址address
    private    String  userId;
    private  String  password;
    private  String  name;
    private  String subject;
    private  String  phone;
    private  String qq;
    private  String address;

    public RegisiterValue(String userId, String password, String name, String subject, String phone, String qq, String address) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.subject = subject;
        this.phone = phone;
        this.qq = qq;
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
