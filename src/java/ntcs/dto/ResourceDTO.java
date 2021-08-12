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
public class ResourceDTO implements Serializable{

    private int ordinalNumber;
    private String resourceId;
    private String nameResource;
    private int quantity;
    private String color;
    private String status;
    private String level;
    private String idCate;
    private String nameCate;

    public ResourceDTO() {
    }

    public ResourceDTO(int ordinalNumber, String resourceId, String nameResource, int quantity, String color, String status, String level, String idCate, String nameCate) {
        this.ordinalNumber = ordinalNumber;
        this.resourceId = resourceId;
        this.nameResource = nameResource;
        this.quantity = quantity;
        this.color = color;
        this.status = status;
        this.level = level;
        this.idCate = idCate;
        this.nameCate = nameCate;
    }

    public ResourceDTO(int ordinalNumber, String resourceId, String nameResource, int quantity, String color, String status, String idCate, String nameCate) {
        this.ordinalNumber = ordinalNumber;
        this.resourceId = resourceId;
        this.nameResource = nameResource;
        this.quantity = quantity;
        this.color = color;
        this.status = status;
        this.idCate = idCate;
        this.nameCate = nameCate;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getNameResource() {
        return nameResource;
    }

    public void setNameResource(String nameResource) {
        this.nameResource = nameResource;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIdCate() {
        return idCate;
    }

    public void setIdCate(String idCate) {
        this.idCate = idCate;
    }

    public String getNameCate() {
        return nameCate;
    }

    public void setNameCate(String nameCate) {
        this.nameCate = nameCate;
    }

    public int getOrdinalNumber() {
        return ordinalNumber;
    }

    public void setOrdinalNumber(int ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

    @Override
    public String toString() {
        return "ResourceDTO{" + "ordinalNumber=" + ordinalNumber + ", resourceId=" + resourceId + ", nameResource=" + nameResource + ", quantity=" + quantity + ", color=" + color + ", status=" + status + ", level=" + level + ", idCate=" + idCate + ", nameCate=" + nameCate + '}';
    }

    

}
