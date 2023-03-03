package com.shubh.cityroutes_backend.CustomerFiles;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Customer_controller {

  // @Autowired
  // Customer_service customer_service;


  
@GetMapping("/item")
public ResponseEntity<String> getStockItem() {
        return new ResponseEntity<String>("It's working...!", HttpStatus.OK);
}
  


}
