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
import ntcs.dto.AcceptedQuantityDTO;

/**
 *
 * @author WIN 10
 */
public class ProcessRequestDAO {

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

    public int countRequestExceptDeleteStatus(String status, String email) throws NamingException, SQLException {
        int count = 0;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "select count(idRental) as NumOfRequest "
                        + "from rental "
                        + "where status != 'DeleteNew' and status like ? and email like ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, '%' + status + '%');
                ps.setString(2, '%' + email + '%');

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

    public List<RentalDTO> loadRequestExceptDeleteStatus(String status, String searchValue, int ordinaryNumber) throws NamingException, SQLException {
        List<RentalDTO> listRequestManager = null;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "select ROW_NUMBER() over (order by requestDate) as RowNumber, idRental ,email,requestDate,status "
                        + "from rental "
                        + "where status != 'DeleteNew' and status like ? and email like ? "
                        + "order by requestDate "
                        + "offset ? rows "
                        + "fetch next 10 rows only ";

                ps = con.prepareStatement(sql);
                ps.setString(1, '%' + status + '%');
                ps.setString(2, '%' + searchValue + '%');
                ps.setInt(3, ordinaryNumber);

                rs = ps.executeQuery();

                if (listRequestManager == null) {
                    listRequestManager = new ArrayList<>();
                }

                while (rs.next()) {
                    int rowNumber = rs.getInt("RowNumber");
                    String idRental = rs.getString("idRental");
                    String email = rs.getString("email");
                    String requestDate = rs.getString("requestDate");
                    String status_query = rs.getString("status");

                    RentalDTO dto = new RentalDTO(rowNumber, idRental, email, requestDate, status_query);

                    listRequestManager.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listRequestManager;
    }

    public int countRequestWithDeleteStatus(String status, String email) throws NamingException, SQLException {
        int count = 0;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "select count(idRental) as NumOfRequest "
                        + "from rental "
                        + "where status != 'DeleteNew' and (status like ? or status like '%Inactive%') and email like ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, '%' + status + '%');
                ps.setString(2, '%' + email + '%');

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

    public List<RentalDTO> loadRequestWithDeleteStatus(String status, String searchValue, int ordinaryNumber) throws NamingException, SQLException {
        List<RentalDTO> listRequestManager = null;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "select ROW_NUMBER() over (order by requestDate)as RowNumber, idRental ,email,requestDate,status, "
                        + "case when status = 'Inactive' then 'Delete' "
                        + "when status = 'New' then 'New' "
                        + "when status = 'Accept' then 'Accept' "
                        + "when status = 'Delete' then 'Delete' "
                        + "end as statusManager "
                        + "from rental "
                        + "where (status like ? or status like '%Inactive%') and email like ?  and status != 'DeleteNew' "
                        + "order by requestDate "
                        + "offset ? rows "
                        + "fetch next 10 rows only";

                ps = con.prepareStatement(sql);
                ps.setString(1, '%' + status + '%');
                ps.setString(2, '%' + searchValue + '%');
                ps.setInt(3, ordinaryNumber);

                rs = ps.executeQuery();

                if (listRequestManager == null) {
                    listRequestManager = new ArrayList<>();
                }

                while (rs.next()) {
                    int rowNumber = rs.getInt("RowNumber");
                    String idRental = rs.getString("idRental");
                    String email = rs.getString("email");
                    String requestDate = rs.getString("requestDate");
                    String status_query = rs.getString("statusManager");

                    RentalDTO dto = new RentalDTO(rowNumber, idRental, email, requestDate, status_query);

                    listRequestManager.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listRequestManager;
    }

    public int countRequestBetweenDateExceptDeleteStatus(String status, String email, String dateFrom, String dateTo) throws NamingException, SQLException {
        int count = 0;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "select count(idRental) as NumOfRequest "
                        + "from rental "
                        + "where status != 'DeleteNew' and status like ? and email like ? and requestDate between ? and ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, '%' + status + '%');
                ps.setString(2, '%' + email + '%');
                ps.setString(3, dateFrom);
                ps.setString(4, dateTo);

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

    public List<RentalDTO> loadRequestBetweenDateExceptDeleteStatus(String status, String searchValue, String dateFrom, String dateTo, int ordinaryNumber) throws NamingException, SQLException {
        List<RentalDTO> listRequestManager = null;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "select ROW_NUMBER() over (order by requestDate) as RowNumber, idRental ,email,requestDate,status "
                        + "from rental "
                        + "where status != 'DeleteNew' and status like ? and email like ? and requestDate between ? and ? "
                        + "order by requestDate "
                        + "offset ? rows "
                        + "fetch next 10 rows only ";

                ps = con.prepareStatement(sql);
                ps.setString(1, '%' + status + '%');
                ps.setString(2, '%' + searchValue + '%');
                ps.setString(3, dateFrom);
                ps.setString(4, dateTo);
                ps.setInt(5, ordinaryNumber);

                rs = ps.executeQuery();

                if (listRequestManager == null) {
                    listRequestManager = new ArrayList<>();
                }

                while (rs.next()) {
                    int rowNumber = rs.getInt("RowNumber");
                    String idRental = rs.getString("idRental");
                    String email = rs.getString("email");
                    String requestDate = rs.getString("requestDate");
                    String status_query = rs.getString("status");

                    RentalDTO dto = new RentalDTO(rowNumber, idRental, email, requestDate, status_query);
                    listRequestManager.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listRequestManager;
    }

    public List<RentalDTO> loadRequestBetweenDateWithDeleteStatus(String status, String searchValue, String dateFrom, String dateTo, int ordinaryNumber) throws NamingException, SQLException {
        List<RentalDTO> listRequestManager = null;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "select ROW_NUMBER() over (order by requestDate)as RowNumber, idRental ,email,requestDate,status, "
                        + "case when status = 'Inactive' then 'Delete' "
                        + "when status = 'New' then 'New' "
                        + "when status = 'Accept' then 'Accept' "
                        + "when status = 'Delete' then 'Delete' "
                        + "end as statusManager "
                        + "from rental "
                        + "where (status like ? or status like '%Inactive%') and email like ? "
                        + "and status != 'DeleteNew' and requestDate between ? and ? "
                        + "order by requestDate "
                        + "offset ? rows "
                        + "fetch next 10 rows only";

                ps = con.prepareStatement(sql);
                ps.setString(1, '%' + status + '%');
                ps.setString(2, '%' + searchValue + '%');
                ps.setString(3, dateFrom);
                ps.setString(4, dateTo);
                ps.setInt(5, ordinaryNumber);

                rs = ps.executeQuery();

                if (listRequestManager == null) {
                    listRequestManager = new ArrayList<>();
                }

                while (rs.next()) {
                    int rowNumber = rs.getInt("RowNumber");
                    String idRental = rs.getString("idRental");
                    String email = rs.getString("email");
                    String requestDate = rs.getString("requestDate");
                    String status_query = rs.getString("statusManager");

                    RentalDTO dto = new RentalDTO(rowNumber, idRental, email, requestDate, status_query);
                    listRequestManager.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listRequestManager;
    }

    public int countRequestBetweenDateWithDeleteStatus(String status, String email, String dateFrom, String dateTo) throws NamingException, SQLException {
        int count = 0;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "select count(idRental) as NumOfRequest "
                        + "from rental "
                        + "where status != 'DeleteNew' and (status like ? or status like '%Inactive%') and email like ? and requestDate between ? and ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, '%' + status + '%');
                ps.setString(2, '%' + email + '%');
                ps.setString(3, dateFrom);
                ps.setString(4, dateTo);

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

    public List<AcceptedQuantityDTO> listSumQuantityAccepted(String today) throws NamingException, SQLException {
        List<AcceptedQuantityDTO> listSumQuantityAccepted = null;
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "select d.rentalDate, re.idResource ,sum(d.quantity) as SumQuantity "
                        + "from  rental rt join rental_detail  d on rt.idRental = d.idRental "
                        + "join resource re on d.idResource = re.idResource "
                        + "where rt.status = 'Accept' and  rentalDate >= ? "
                        + "group by d.rentalDate, re.idResource "
                        + "order by rentalDate ASC";

                ps = con.prepareStatement(sql);
                ps.setString(1, today);
                rs = ps.executeQuery();

                if (listSumQuantityAccepted == null) {
                    listSumQuantityAccepted = new ArrayList<>();
                }

                while (rs.next()) {
                    String rentalDate = rs.getString("rentalDate");
                    String idResource = rs.getString("idResource");
                    int quantityRent = rs.getInt("SumQuantity");

                    AcceptedQuantityDTO dto = new AcceptedQuantityDTO(rentalDate, idResource, quantityRent);
                    listSumQuantityAccepted.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listSumQuantityAccepted;
    }

    public boolean acceptRequest(String idRental) throws NamingException, SQLException {
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "update rental "
                        + "set status = 'Accept' "
                        + "where idRental = ? ";

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
    
     public boolean deleteRequest(String idRental) throws NamingException, SQLException {
        try {
            con = ConnectDatabase.makeConnection();
            if (con != null) {
                String sql = "update rental "
                        + "set status = 'Delete' "
                        + "where idRental = ? ";

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
}
