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
import ncts.dbUtility.ConnectDatabase;
import ntcs.dto.RentalDTO;

/**
 *
 * @author WIN 10
 */
public class RentalDAO {

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

    public boolean createRental(RentalDTO dto) throws NamingException, SQLException {
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "Insert into rental "
                        + "values(?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, dto.getIdRental());
                ps.setString(2, dto.getEmail());
                ps.setString(3, dto.getRequestDate());
                ps.setString(4, dto.getStatus());

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
