/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import ncts.dbUtility.ConnectDatabase;
import ntcs.dto.CategoryDTO;

/**
 *
 * @author WIN 10
 */
public class CategoryDAO {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public List<CategoryDTO> loadCategoryEmployee() throws NamingException, SQLException {
        List<CategoryDTO> listCate = null;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "select idCategory, nameCategory "
                        + "from category "
                        + "where idCategory != 'C003'";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                if (listCate == null) {
                    listCate = new ArrayList<>();
                }
                while (rs.next()) {

                    String idCategory = rs.getString("idCategory");
                    String nameCategory = rs.getString("nameCategory");

                    CategoryDTO dto = new CategoryDTO(idCategory, nameCategory);
                    listCate.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listCate;
    }

    public List<CategoryDTO> loadCategoryFull() throws NamingException, SQLException {
        List<CategoryDTO> listCate = null;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "select idCategory, nameCategory "
                        + "from category";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                if (listCate == null) {
                    listCate = new ArrayList<>();
                }
                while (rs.next()) {

                    String idCategory = rs.getString("idCategory");
                    String nameCategory = rs.getString("nameCategory");

                    CategoryDTO dto = new CategoryDTO(idCategory, nameCategory);
                    listCate.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listCate;
    }

    public List<String> loadCategoryForManager() throws NamingException, SQLException {
        List<String> listCate = null;
        if(listCate == null) {
            listCate = new ArrayList<>();
        }
        listCate.add("Accept");
        listCate.add("Delete");
        
        return listCate;
    }

}
