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
import ntcs.dto.ResourceDTO;

/**
 *
 * @author WIN 10
 */
public class ResourceDAO {

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

    public List<ResourceDTO> loadResourceByPageForEmployee(int page) throws NamingException, SQLException {
        List<ResourceDTO> listResource = null;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "SELECT ROW_NUMBER () OVER(ORDER BY nameResource ) AS RowNum, "
                        + "R.idResource,R.nameResource,R.color,R.quantity,R.status,C.idCategory,C.nameCategory "
                        + "FROM resource r join category c on r.idCategory = c.idCategory "
                        + "WHERE r.level ='1' "
                        + "ORDER BY nameResource "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT 10 ROWS ONLY";
                ps = con.prepareStatement(sql);

                ps.setInt(1, page);
                rs = ps.executeQuery();
                if (listResource == null) {
                    listResource = new ArrayList<>();
                }
                while (rs.next()) {
                    int RowNumber = rs.getInt("RowNum");
                    String idResource = rs.getString("idResource");
                    String name = rs.getString("nameResource");
                    int quantity = rs.getInt("quantity");
                    String color = rs.getString("color");
                    String status = rs.getString("status");
                    String idCate = rs.getString("idCategory");
                    String nameCate = rs.getString("nameCategory");

                    ResourceDTO dto = new ResourceDTO(RowNumber, idResource, name, quantity, color, status, idCate, nameCate);
                    listResource.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listResource;
    }

    public List<ResourceDTO> loadResourceByPageForLeader(int page) throws NamingException, SQLException {
        List<ResourceDTO> listResource = null;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "SELECT ROW_NUMBER () OVER(ORDER BY nameResource ) AS RowNum, "
                        + "R.idResource,R.nameResource,R.color,R.quantity,R.status,C.idCategory,C.nameCategory "
                        + "FROM resource r join category c on r.idCategory = c.idCategory "
                        + "ORDER BY nameResource "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT 10 ROWS ONLY";
                ps = con.prepareStatement(sql);

                ps.setInt(1, page);
                rs = ps.executeQuery();
                if (listResource == null) {
                    listResource = new ArrayList<>();
                }
                while (rs.next()) {
                    int RowNumber = rs.getInt("RowNum");
                    String idResource = rs.getString("idResource");
                    String name = rs.getString("nameResource");
                    int quantity = rs.getInt("quantity");
                    String color = rs.getString("color");
                    String status = rs.getString("status");
                    String idCate = rs.getString("idCategory");
                    String nameCate = rs.getString("nameCategory");

                    ResourceDTO dto = new ResourceDTO(RowNumber, idResource, name, quantity, color, status, idCate, nameCate);
                    listResource.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listResource;
    }

    public int countAllResource() throws NamingException, SQLException {
        int count = 0;
        try {
            con = ConnectDatabase.makeConnection();

            if (con != null) {
                String sql = "SELECT COUNT(idResource) AS NumOfResource "
                        + "FROM resource";
                ps = con.prepareStatement(sql);

                rs = ps.executeQuery();

                if (rs.next()) {
                    count = rs.getInt("NumOfResource");
                }
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public int countResourceForEmployee() throws NamingException, SQLException {
        int count = 0;
        try {
            con = ConnectDatabase.makeConnection();

            if (con != null) {
                String sql = "SELECT COUNT(idResource) AS NumOfResource "
                        + "FROM resource "
                        + "where level = '1'";
                ps = con.prepareStatement(sql);

                rs = ps.executeQuery();

                if (rs.next()) {
                    count = rs.getInt("NumOfResource");
                }
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public List<ResourceDTO> loadResourceForLeaderWithSearchValue(int page, String searchValue,String category) throws NamingException, SQLException {
        List<ResourceDTO> listResource = null;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "SELECT ROW_NUMBER () OVER(ORDER BY nameResource ) AS RowNum, "
                        + "R.idResource,R.nameResource,R.color,R.quantity,R.status,C.idCategory,C.nameCategory "
                        + "FROM resource r join category c on r.idCategory = c.idCategory "
                        + "WHERE r.nameResource like ? and r.idCategory like ? "
                        + "ORDER BY nameResource "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT 10 ROWS ONLY";
                ps = con.prepareStatement(sql);

                ps.setString(1, '%' + searchValue + '%');
                ps.setString(2, '%' + category + '%');
                ps.setInt(3, page);
                rs = ps.executeQuery();

                if (listResource == null) {
                    listResource = new ArrayList<>();
                }

                while (rs.next()) {
                    int RowNumber = rs.getInt("RowNum");
                    String idResource = rs.getString("idResource");
                    String name = rs.getString("nameResource");
                    int quantity = rs.getInt("quantity");
                    String color = rs.getString("color");
                    String status = rs.getString("status");
                    String idCate = rs.getString("idCategory");
                    String nameCate = rs.getString("nameCategory");

                    ResourceDTO dto = new ResourceDTO(RowNumber, idResource, name, quantity, color, status, idCate, nameCate);
                    listResource.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listResource;
    }

    public int countAllResourceWithSearchValue(String searchValue, String category) throws NamingException, SQLException {
        int count = 0;
        try {
            con = ConnectDatabase.makeConnection();

            if (con != null) {
                String sql = "SELECT COUNT(idResource) AS NumOfResource "
                        + "FROM resource "
                        + "Where nameResource like ? and idCategory like ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, '%' + searchValue + '%');
                ps.setString(2, '%' + category + '%');
                rs = ps.executeQuery();

                if (rs.next()) {
                    count = rs.getInt("NumOfResource");
                }
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public List<ResourceDTO> loadResourceForEmployeeWithSearchValue(int page, String searchValue ,String category) throws NamingException, SQLException {
        List<ResourceDTO> listResource = null;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "SELECT ROW_NUMBER () OVER(ORDER BY nameResource ) AS RowNum, "
                        + "R.idResource,R.nameResource,R.color,R.quantity,R.status,C.idCategory,C.nameCategory "
                        + "FROM resource r join category c on r.idCategory = c.idCategory "
                        + "WHERE r.nameResource like ? and r.level = '1' and r.idCategory like ? "
                        + "ORDER BY nameResource "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT 10 ROWS ONLY";
                ps = con.prepareStatement(sql);

                ps.setString(1, '%' + searchValue + '%');
                ps.setString(2, '%' + category + '%');
                ps.setInt(3, page);
                rs = ps.executeQuery();

                if (listResource == null) {
                    listResource = new ArrayList<>();
                }

                while (rs.next()) {
                    int RowNumber = rs.getInt("RowNum");
                    String idResource = rs.getString("idResource");
                    String name = rs.getString("nameResource");
                    int quantity = rs.getInt("quantity");
                    String color = rs.getString("color");
                    String status = rs.getString("status");
                    String idCate = rs.getString("idCategory");
                    String nameCate = rs.getString("nameCategory");

                    ResourceDTO dto = new ResourceDTO(RowNumber, idResource, name, quantity, color, status, idCate, nameCate);
                    listResource.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listResource;
    }

    public int countResourceEmployeeWithSearchValue(String searchValue,String category) throws NamingException, SQLException {
        int count = 0;
        try {
            con = ConnectDatabase.makeConnection();

            if (con != null) {
                String sql = "SELECT COUNT(idResource) AS NumOfResource "
                        + "FROM resource "
                        + "Where nameResource like ? and level = '1' and idCategory like ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, '%' + searchValue + '%');
                ps.setString(2, '%' + category + '%');
                rs = ps.executeQuery();
                
                if (rs.next()) {
                    count = rs.getInt("NumOfResource");
                }
            }
        } finally {
            closeConnection();
        }
        return count;
    }


  
}
