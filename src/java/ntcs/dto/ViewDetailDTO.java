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
public class ViewDetailDTO implements Serializable {
    String idDetail;
    String idResource;
    String nameResource;
    String color;
    String nameCategory;
    int quantityResource;
    int quantityRequest;
    String rentalDate;
    String returnDate;

    public ViewDetailDTO() {
    }

    public ViewDetailDTO(String idDetail, String idResource, String nameResource, String color, String nameCategory, int quantityResource, int quantityRequest, String rentalDate, String returnDate) {
        this.idDetail = idDetail;
        this.idResource = idResource;
        this.nameResource = nameResource;
        this.color = color;
        this.nameCategory = nameCategory;
        this.quantityResource = quantityResource;
        this.quantityRequest = quantityRequest;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public String getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(String idDetail) {
        this.idDetail = idDetail;
    }

    public String getIdResource() {
        return idResource;
    }

    public void setIdResource(String idResource) {
        this.idResource = idResource;
    }

    public String getNameResource() {
        return nameResource;
    }

    public void setNameResource(String nameResource) {
        this.nameResource = nameResource;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public int getQuantityResource() {
        return quantityResource;
    }

    public void setQuantityResource(int quantityResource) {
        this.quantityResource = quantityResource;
    }

    public int getQuantityRequest() {
        return quantityRequest;
    }

    public void setQuantityRequest(int quantityRequest) {
        this.quantityRequest = quantityRequest;
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

   

    

    

    @Override
    public String toString() {
        return "ViewDetailDTO{" + "idDetail=" + idDetail + ", idResource=" + idResource + ", nameResource=" + nameResource + ", color=" + color + ", nameCategory=" + nameCategory + ", quantityResource=" + quantityResource + ", quantityRequest=" + quantityRequest + ", rentalDate=" + rentalDate + ", returnDate=" + returnDate + '}';
    }

    
}
