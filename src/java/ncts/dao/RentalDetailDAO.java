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
import ntcs.dto.RentalDetailDTO;
import ntcs.dto.ViewDetailDTO;

/**
 *
 * @author WIN 10
 */
public class RentalDetailDAO {

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

    public boolean createDetail(RentalDetailDTO dto) throws NamingException, SQLException {
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "insert into rental_detail "
                        + "values(?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, dto.getIdDetail());
                ps.setString(2, dto.getIdRental());
                ps.setString(3, dto.getResourceId());
                ps.setString(4, dto.getRentalDate());
                ps.setString(5, dto.getReturnDate());
                ps.setInt(6, dto.getQuantity());

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

    public List<ViewDetailDTO> showDetail(String idRental) throws NamingException, SQLException {
        List<ViewDetailDTO> listDetail = null;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "select d.idDetail,re.idResource,re.nameResource,re.color,ca.nameCategory,(re.quantity) as quantityResource ,d.quantity,d.rentalDate,d.returnDate "
                        + "from rental rt join rental_detail d on rt.idRental = d.idRental "
                        + "join resource re on d.idResource = re.idResource "
                        + "join category ca on re.idCategory = ca.idCategory "
                        + "where rt.idRental = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, idRental);

                rs = ps.executeQuery();
                if (listDetail == null) {
                    listDetail = new ArrayList<>();
                }

                while (rs.next()) {
                    String idDetail = rs.getString("idDetail");
                    String idResource = rs.getString("idResource");
                    String nameResource = rs.getString("nameResource");
                    String color = rs.getString("color");
                    String category = rs.getString("nameCategory");
                    int quantityResource = rs.getInt("quantityResource");
                    int quantityRequest = rs.getInt("quantity");
                    String rentalDate = rs.getString("rentalDate");
                    String returnDate = rs.getString("returnDate");

                    ViewDetailDTO dto = new ViewDetailDTO(idDetail, idResource, nameResource, color, category, quantityResource, quantityRequest, rentalDate, returnDate);
                    listDetail.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listDetail;
    }

}
