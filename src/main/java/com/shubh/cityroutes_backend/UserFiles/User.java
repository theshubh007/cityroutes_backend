package com.shubh.cityroutes_backend.UserFiles;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Base64;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @Column(nullable = false)
    private String name;

   
 @Column(nullable = false, unique = true, length = 20, updatable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Long phone;

    @Column(nullable = false)
    private Long pincode;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = true)
    private String mpin;

    // Add new field for mpin expiry date
    @Column(nullable = true)
    private Date lastmpinupdate;

    public User() {
    }

    public User( String name, String email, String password, Long phone, Long pincode, Date date, String mpin,
            Date lastmpinupdate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.pincode = pincode;
        this.date = date;
        this.mpin = mpin;
        this.lastmpinupdate = lastmpinupdate;
      
    }

    


    public Long getId() {
      return id;
    }

    public void setId(Long id) {
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
        try {
            // Create a SHA-256 message digest instance
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Generate a hash of the password
            byte[] hashedPasswordBytes = digest.digest(password.getBytes());

            // Encode the hashed password as a Base64 string
            String hashedPassword = Base64.getEncoder().encodeToString(hashedPasswordBytes);

            // Set the hashed password in the password field
            this.password = hashedPassword;

        } catch (NoSuchAlgorithmException e) {
            // Handle the exception appropriately
            e.printStackTrace();
        }
    }

    public Long getPhone() {
      return phone;
    }

    public void setPhone(Long phone) {
      this.phone = phone;
    }

    public Long getPincode() {
      return pincode;
    }

    public void setPincode(Long pincode) {
      this.pincode = pincode;
    }

    public Date getDate() {
      return date;
    }

    public void setDate(Date date) {
      this.date = date;
    }
      public String getMpin() {
        return mpin;
    }

    public void setMpin(String mpin) {
        this.mpin = mpin;
    }
    
    public Date getLastMpinUpdate() {
        return lastmpinupdate;
    }

    public void setLastMpinUpdate(Date lastMpinUpdate) {
      this.lastmpinupdate = lastMpinUpdate;
    }
    public void createMpin(String mpin) {
        // Set the user's MPIN and lastMpinUpdate fields
        this.mpin = mpin;
        this.lastmpinupdate = new Date(System.currentTimeMillis());
    }
    
    public boolean authenticateMpin(String mpin) {
        // Check if the provided MPIN matches the user's MPIN and if it has been less than or equal to 3 months since the last MPIN update
        return this.mpin.equals(mpin) && this.lastmpinupdate.getTime() + (3 * 30 * 24 * 60 * 60 * 1000) >= System.currentTimeMillis();
    }
}
