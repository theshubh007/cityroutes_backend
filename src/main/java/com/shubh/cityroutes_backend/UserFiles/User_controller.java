package com.shubh.cityroutes_backend.UserFiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class User_controller {

  @Autowired
  private User_Services userServices;

   @PostMapping("/signup")
  public ResponseEntity<Object> signup(@RequestBody User user) {
      return userServices.signup(user);
  }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
      return userServices.login(user);
    }
   @PostMapping("/loginWithMPIN")
    public ResponseEntity<Object> loginWithMpin(@RequestParam String email, @RequestParam String mpin) {
        return userServices.loginWithMPIN(email, mpin);
    }

    @PutMapping("/{userId}/mpin")
    public ResponseEntity<Object> updateMpin(@PathVariable Long userId, @RequestParam String currentMpin, @RequestParam String newMpin) {
        return userServices.updateMPIN(userId, currentMpin, newMpin);
    }

    @PutMapping("/resetMpin")
    public ResponseEntity<Object> resetMpin(@RequestParam String email, @RequestParam String newMpin) {
        return userServices.resetMPIN(email, newMpin);
    }
}






