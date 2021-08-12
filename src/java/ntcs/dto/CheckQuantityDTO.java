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
public class CheckQuantityDTO implements Serializable{
    String idResource;
    String rentalDate;

    public CheckQuantityDTO() {
    }

    public CheckQuantityDTO(String idResource, String rentalDate) {
        this.idResource = idResource;
        this.rentalDate = rentalDate;
    }

    public String getIdResource() {
        return idResource;
    }

    public void setIdResource(String idResource) {
        this.idResource = idResource;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    @Override
    public String toString() {
        return "CheckQuantityDTO{" + "idResource=" + idResource + ", rentalDate=" + rentalDate + '}';
    }
    
    
}
