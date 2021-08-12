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
import javax.naming.NamingException;
import ntcs.dto.UserDTO;
import ncts.dbUtility.ConnectDatabase;

/**
 *
 * @author WIN 10
 */
public class UserDAO {

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

    public UserDTO checkLogin(String email, String password) throws NamingException, SQLException {
        UserDTO dto = null;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "select email ,displayName,phone_number,address,status,role "
                        + "from registration "
                        + "where email = ? and password = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);

                rs = ps.executeQuery();

                if (rs.next()) {
                    String email_query = rs.getString("email");
                    String displayName = rs.getString("displayName");
                    String phoneNumber = rs.getString("phone_number");
                    String address = rs.getString("address");
                    String status = rs.getString("status");
                    String role = rs.getString("role");

                    dto = new UserDTO(email_query, phoneNumber, displayName, address, status, role);
                }

            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public UserDTO checkGGAccount(String email) throws NamingException, SQLException {
        UserDTO dto = null;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "select email ,displayName,phone_number,address,status,role "
                        + "from registration "
                        + "where email = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();

                if (rs.next()) {
                    String email_query = rs.getString("email");
                    String displayName = rs.getString("displayName");
                    String phone = rs.getString("phone_number");
                    String address = rs.getString("address");
                    String status = rs.getString("status");
                    String role = rs.getString("role");

                    dto = new UserDTO(email_query, phone, displayName, address, status, role);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public String checkEmail(String email) throws NamingException, SQLException {
        UserDTO dto = null;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "select email "
                        + "from registration "
                        + "where email = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getString("email");
                }
            }
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean createUser(UserDTO dto) throws NamingException, SQLException {
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "Insert into registration "
                        + "values(?,?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, dto.getEmail());
                ps.setString(2, dto.getPassword());
                ps.setString(3, dto.getPhone());
                ps.setString(4, dto.getDisplayName());
                ps.setString(5, dto.getAddress());
                ps.setString(6, dto.getCreateDate());
                ps.setString(7, dto.getRole());
                ps.setString(8, dto.getStatus());
                int affectedRow = ps.executeUpdate();
                if (affectedRow > 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean changeStatusAccount(String status, String email) throws NamingException, SQLException {
        try {

            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "update registration "
                        + "set status = ? "
                        + "where email = ?";

                ps = con.prepareStatement(sql);
                ps.setString(1, status);
                ps.setString(2, email);

                int affectedRow = ps.executeUpdate();
                if (affectedRow > 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }
}
