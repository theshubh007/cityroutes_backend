package com.shubh.cityroutes_backend.CustomerFiles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

  @Id
  @Column
  int id;

  @Column(name = "customer_name")
  String name;

  public Customer(int id, String name, String email, String password, String phone) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.phone = phone;
  }

  @Column(name = "customer_email")
  String email;

  @Column(name = "customer_password")
  String password;

  @Column(name = "customer_phone")
  String phone;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
  

  
}
