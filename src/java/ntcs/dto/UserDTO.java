/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ntcs.dto;

import java.io.Serializable;

/**
 *
 * @author WIN 10
 */
public class UserDTO implements Serializable {

    private String email;
    private String password;
    private String phone;
    private String displayName;
    private String address;
    private String createDate;
    private String status;
    private String role;

    public UserDTO() {
    }

    public UserDTO(String email, String password, String phone, String displayName, String address, String createDate, String status, String role) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.displayName = displayName;
        this.address = address;
        this.createDate = createDate;
        this.status = status;
        this.role = role;
    }

    public UserDTO(String email, String phone, String displayName, String address, String status, String role) {
        this.email = email;
        this.phone = phone;
        this.displayName = displayName;
        this.address = address;
        this.status = status;
        this.role = role;
    }

    public UserDTO(String email, String phone, String displayName, String address, String createDate, String role, String status) {
        this.email = email;
        this.phone = phone;
        this.displayName = displayName;
        this.address = address;
        this.createDate = createDate;
        this.status = status;
        this.role = role;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "email=" + email + ", password=" + password + ", phone=" + phone + ", displayName=" + displayName + ", address=" + address + ", createDate=" + createDate + ", status=" + status + ", role=" + role + '}';
    }

   

    
}
