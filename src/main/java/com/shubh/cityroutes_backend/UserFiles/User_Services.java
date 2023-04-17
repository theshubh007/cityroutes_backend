package com.shubh.cityroutes_backend.UserFiles;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shubh.cityroutes_backend.Common_response;

@Service
public class User_Services {

  @Autowired
  private User_crudrepo user_crudrepo;


  
  public ResponseEntity<Object> signup(User user) {
    try {
      // Check if user with given email already exists
      User existingStudent = user_crudrepo.findByEmail(user.getEmail());
      if (existingStudent != null) {
        return Common_response.errorResponse("User with given email already exists", HttpStatus.BAD_REQUEST);
      }

      // Create a new user entity and save it to the database
      User newStudent = new User(
          user.getName(),
          user.getEmail(),
          user.getPassword(),
          user.getPhone(),
          user.getPincode(),
          new Date(System.currentTimeMillis()),
          user.getMpin(),
          new Date(System.currentTimeMillis()));

      user_crudrepo.save(newStudent);

      // Return success details
      Map<String, Object> details = new HashMap<>();
      details.put("userdetails", newStudent);
      details.put("uid", newStudent.getId());
      return Common_response.successResponse(details);
    } catch (Exception e) {
      e.printStackTrace();
      return Common_response.errorResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
 

  public ResponseEntity<Object> login(User user) {

    User optionalUser = user_crudrepo.findByEmail(user.getEmail());

    if (optionalUser != null) {
      if (optionalUser.getPassword().equals(user.getPassword())) {
        // return ResponseEntity.ok().body(optionalUser);
        Map<String, Object> details = new HashMap<>();

        details.put("userdetails", optionalUser);
        details.put("user", "user");
        details.put("uid", optionalUser.getId().toString());
        return Common_response.successResponse(details);
      }
    }

    // if user not found or password incorrect, return error details
    return Common_response.errorResponse("Invalid email or password", HttpStatus.UNAUTHORIZED);
  }

    
  public ResponseEntity<Object> loginWithMPIN(String email, String mpin) {
    try {

      User user = user_crudrepo.findByEmail(email);
      if (user == null) {
        // User not found
        return Common_response.errorResponse("User not found", HttpStatus.NOT_FOUND);
      }

      boolean authenticated = user.authenticateMpin(mpin);
      if (!authenticated) {
        // Incorrect MPIN
        return Common_response.errorResponse("Incorrect MPIN", HttpStatus.UNAUTHORIZED);
      }

      Map<String, Object> details = new HashMap<>();
      details.put("userdetails", user);
      details.put("uid", user.getId());
      return Common_response.successResponse(details);
    } catch (Exception e) {
      e.printStackTrace();
      return Common_response.errorResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }



  public ResponseEntity<Object> updateMPIN(Long userId, String currentMpin, String newMpin) {
    try {
        // Check if the user exists in the database
        Optional<User> optionalUser = user_crudrepo.findById(userId);
        if (!optionalUser.isPresent()) {
            return Common_response.errorResponse("User not found", HttpStatus.NOT_FOUND);
        }

        // Get the user from the optional
        User user = optionalUser.get();

        // Authenticate the user with their current MPIN
        if (!user.authenticateMpin(currentMpin)) {
            return Common_response.errorResponse("Invalid MPIN", HttpStatus.UNAUTHORIZED);
        }

        // Set the new MPIN and update time
        user.setMpin(newMpin);
        user.setLastMpinUpdate(new Date(System.currentTimeMillis()));

        // Save the updated user to the database
        user_crudrepo.save(user);

        // Return success details
        Map<String, Object> details = new HashMap<>();
        details.put("message", "MPIN updated successfully");
        return Common_response.successResponse(details);

    } catch (Exception e) {
        e.printStackTrace();
        return Common_response.errorResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}




public ResponseEntity<Object> resetMPIN(String email, String newMpin) {
    try {
      
        User user = user_crudrepo.findByEmail(email);
        if (user == null) {
            return Common_response.errorResponse("User not found", HttpStatus.NOT_FOUND);
        }

        // Set the new MPIN and update time
        user.setMpin(newMpin);
        user.setLastMpinUpdate(new Date(System.currentTimeMillis()));

        // Save the updated user to the database
        user_crudrepo.save(user);

        // Return success details
        Map<String, Object> details = new HashMap<>();
        details.put("message", "MPIN reset successfully");
        return Common_response.successResponse(details);

    } catch (Exception e) {
        e.printStackTrace();
        return Common_response.errorResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

}
