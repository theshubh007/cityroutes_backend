package com.shubh.cityroutes_backend.CustomerFiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Customer_service {
  
  @Autowired
  Customer_crudrepo customer_crudrepo;

  public Customer getCustomer(int id) {
    return customer_crudrepo.findById(id).get();
  }

  public void addCustomer(Customer customer) {
    customer_crudrepo.save(customer);
  }

  public void deleteCustomer(int id) {
    customer_crudrepo.deleteById(id);
  }

  public void updateCustomer(Customer customer) {
    customer_crudrepo.save(customer);
  }
}
