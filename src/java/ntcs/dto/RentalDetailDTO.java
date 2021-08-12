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
public class RentalDetailDTO implements Serializable {

    String idDetail;
    String idRental;
    String resourceId;
    private String rentalDate;
    private String returnDate;
    int quantity;

    public RentalDetailDTO() {
    }

    public RentalDetailDTO(String idDetail, String idRental, String resourceId, String rentalDate, String returnDate, int quantity) {
        this.idDetail = idDetail;
        this.idRental = idRental;
        this.resourceId = resourceId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.quantity = quantity;
    }

    public String getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(String idDetail) {
        this.idDetail = idDetail;
    }

    public String getIdRental() {
        return idRental;
    }

    public void setIdRental(String idRental) {
        this.idRental = idRental;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "RentalDetailDTO{" + "idDetail=" + idDetail + ", idRental=" + idRental + ", resourceId=" + resourceId + ", rentalDate=" + rentalDate + ", returnDate=" + returnDate + ", quantity=" + quantity + '}';
    }
    
}
