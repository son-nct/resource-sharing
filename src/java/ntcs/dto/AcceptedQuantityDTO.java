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
public class AcceptedQuantityDTO implements Serializable{
    String rentalDate;
    String idResource;
    int QuantityRent;

    public AcceptedQuantityDTO() {
    }

    public AcceptedQuantityDTO(String rentalDate, String idResource, int QuantityRent) {
        this.rentalDate = rentalDate;
        this.idResource = idResource;
        this.QuantityRent = QuantityRent;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getIdResource() {
        return idResource;
    }

    public void setIdResource(String idResource) {
        this.idResource = idResource;
    }

    public int getQuantityRent() {
        return QuantityRent;
    }

    public void setQuantityRent(int QuantityRent) {
        this.QuantityRent = QuantityRent;
    }

    @Override
    public String toString() {
        return "AcceptedQuantityDTO{" + "rentalDate=" + rentalDate + ", idResource=" + idResource + ", QuantityRent=" + QuantityRent + '}';
    }

}
