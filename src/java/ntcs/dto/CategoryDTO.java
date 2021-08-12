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
public class CategoryDTO implements Serializable{
    private String idCate;
    private String nameCate;

    public CategoryDTO() {
    }

    public CategoryDTO(String idCate, String nameCate) {
        this.idCate = idCate;
        this.nameCate = nameCate;
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

    @Override
    public String toString() {
        return "CategoryDTO{" + "idCate=" + idCate + ", nameCate=" + nameCate + '}';
    }
    
    
}
