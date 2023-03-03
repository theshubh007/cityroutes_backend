package com.shubh.cityroutes_backend.CustomerFiles;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Customer_crudrepo extends CrudRepository<Customer, Integer>  {
}

