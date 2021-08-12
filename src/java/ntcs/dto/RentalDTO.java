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
public class RentalDTO implements Serializable {

    private int ordinaryNumber;
    private String idRental;
    private String email;
    private String requestDate;
    private String status;

    public RentalDTO() {
    }

    public RentalDTO(String idRental, String email, String requestDate, String status) {
        this.idRental = idRental;
        this.email = email;
        this.requestDate = requestDate;
        this.status = status;
    }

    public RentalDTO(int ordinaryNumber, String idRental, String email, String requestDate, String status) {
        this.ordinaryNumber = ordinaryNumber;
        this.idRental = idRental;
        this.email = email;
        this.requestDate = requestDate;
        this.status = status;
    }

    public RentalDTO(int ordinaryNumber, String idRental, String requestDate, String status) {
        this.ordinaryNumber = ordinaryNumber;
        this.idRental = idRental;
        this.requestDate = requestDate;
        this.status = status;
    }


    public String getIdRental() {
        return idRental;
    }

    public void setIdRental(String idRental) {
        this.idRental = idRental;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrdinaryNumber() {
        return ordinaryNumber;
    }

    public void setOrdinaryNumber(int ordinaryNumber) {
        this.ordinaryNumber = ordinaryNumber;
    }

    @Override
    public String toString() {
        return "RentalDTO{" + "ordinaryNumber=" + ordinaryNumber + ", idRental=" + idRental + ", email=" + email + ", requestDate=" + requestDate + ", status=" + status + '}';
    }

}
