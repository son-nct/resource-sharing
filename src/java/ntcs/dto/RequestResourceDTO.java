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
public class RequestResourceDTO implements Serializable {

    private String idResource;
    private String nameResource;
    private int quantityResource;
    private String color;
    private String nameCate;
    private String rentalDate;

    public RequestResourceDTO() {
    }

    public RequestResourceDTO(String idResource, String nameResource, int quantityResource, String color, String nameCate, String rentalDate) {
        this.idResource = idResource;
        this.nameResource = nameResource;
        this.quantityResource = quantityResource;
        this.color = color;
        this.nameCate = nameCate;
        this.rentalDate = rentalDate;
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

    public int getQuantityResource() {
        return quantityResource;
    }

    public void setQuantityResource(int quantityResource) {
        this.quantityResource = quantityResource;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNameCate() {
        return nameCate;
    }

    public void setNameCate(String nameCate) {
        this.nameCate = nameCate;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    @Override
    public String toString() {
        return "RequestResourceDTO{" + "idResource=" + idResource + ", nameResource=" + nameResource + ", quantityResource=" + quantityResource + ", color=" + color + ", nameCate=" + nameCate + ", rentalDate=" + rentalDate + '}';
    }

   

}
