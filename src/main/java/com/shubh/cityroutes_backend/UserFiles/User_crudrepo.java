package com.shubh.cityroutes_backend.UserFiles;

import org.springframework.data.repository.CrudRepository;

public interface User_crudrepo extends CrudRepository<User, Long> {

  User findByEmail(String email);
  
}
