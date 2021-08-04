package com.solvd.carfactory.models.employee;

public class Department {
    private long id;
    private String name;
    private String phone;
    private String email;
    private Employee headEmployee;

    public Department() {
    }
    public Department(long id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Employee getHeadEmployee() {
        return headEmployee;
    }
    public void setHeadEmployee(Employee headEmployee) {
        this.headEmployee = headEmployee;
    }
}
