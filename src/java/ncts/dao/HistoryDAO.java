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
import ntcs.dto.RentalDTO;
import ntcs.dto.ViewDetailDTO;

/**
 *
 * @author WIN 10
 */
public class HistoryDAO {

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

    public List<RentalDTO> loadRequestHistory(String email, int ordinaryNumber) throws NamingException, SQLException {
        List<RentalDTO> listHistoryRequest = null;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "select ROW_NUMBER() over (order by requestDate) as RowNumber, idRental , requestDate, status "
                        + "from rental "
                        + "where  email like ? and status not in ('Inactive','DeleteNew') "
                        + "order by requestDate "
                        + "offset ? rows "
                        + "fetch next 10 rows only ";
                ps = con.prepareStatement(sql);
                ps.setString(1, '%' + email + '%');
                ps.setInt(2, ordinaryNumber);

                rs = ps.executeQuery();

                if (listHistoryRequest == null) {
                    listHistoryRequest = new ArrayList<>();
                }

                while (rs.next()) {

                    int rowNumber = rs.getInt("RowNumber");
                    String idRental = rs.getString("idRental");
                    String requestDate = rs.getString("requestDate");
                    String status_query = rs.getString("status");

                    RentalDTO dto = new RentalDTO(rowNumber, idRental, requestDate, status_query);
                    listHistoryRequest.add(dto);
                }
            }

        } finally {
            closeConnection();
        }
        return listHistoryRequest;
    }

    public int countHistory(String email) throws NamingException, SQLException {
        int count = 0;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "select count(idRental) as NumOfRequest "
                        + "from rental "
                        + "where status not in ('Inactive','DeleteNew') and email like ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, '%' + email + '%');

                rs = ps.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("NumOfRequest");
                }
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public boolean deleteNewRequest(String idRental) throws NamingException, SQLException {
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "Update rental "
                        + "set status ='DeleteNew' "
                        + "where idRental = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, idRental);

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

    public boolean deleteApprovedRequest(String idRental) throws NamingException, SQLException {
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "Update rental "
                        + "set status = 'Inactive' "
                        + "where idRental = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, idRental);

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

    public int countHistoryBetweenDate(String email, String dateFrom, String dateTo) throws NamingException, SQLException {
        int count = 0;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "select count(idRental) as NumOfRequest "
                        + "from rental "
                        + "where status not in ('Inactive','DeleteNew') and email like ? and requestDate between ? and ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, '%' + email + '%');
                ps.setString(2, dateFrom);
                ps.setString(3, dateTo);

                rs = ps.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("NumOfRequest");
                }
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public List<RentalDTO> loadRequestHistoryBetweenDate(String email, String dateFrom, String dateTo, int ordinaryNumber) throws NamingException, SQLException {
        List<RentalDTO> listHistoryRequest = null;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "select ROW_NUMBER() over (order by requestDate) as RowNumber, idRental , requestDate, status "
                        + "from rental "
                        + "where  email like ? and status not in ('Inactive','DeleteNew') and requestDate between ? and ? "
                        + "order by requestDate "
                        + "offset ? rows "
                        + "fetch next 10 rows only ";
                ps = con.prepareStatement(sql);
                ps.setString(1, '%' + email + '%');
                ps.setString(2, dateFrom);
                ps.setString(3, dateTo);
                ps.setInt(4, ordinaryNumber);

                rs = ps.executeQuery();

                if (listHistoryRequest == null) {
                    listHistoryRequest = new ArrayList<>();
                }

                while (rs.next()) {

                    int rowNumber = rs.getInt("RowNumber");
                    String idRental = rs.getString("idRental");
                    String requestDate = rs.getString("requestDate");
                    String status_query = rs.getString("status");

                    RentalDTO dto = new RentalDTO(rowNumber, idRental, requestDate, status_query);
                    listHistoryRequest.add(dto);
                }
            }

        } finally {
            closeConnection();
        }
        return listHistoryRequest;
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
