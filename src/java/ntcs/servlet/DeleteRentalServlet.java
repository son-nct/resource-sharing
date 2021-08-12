/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ntcs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ncts.dao.HistoryDAO;
import ntcs.dto.RentalDTO;
import ntcs.dto.UserDTO;

/**
 *
 * @author WIN 10
 */
@WebServlet(name = "DeleteRentalServlet", urlPatterns = {"/DeleteRentalServlet"})
public class DeleteRentalServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String idResource = request.getParameter("idRental");
        String status = request.getParameter("status");
        String url = null;
        HistoryDAO dao = new HistoryDAO();
        HistoryDAO historyDAO = new HistoryDAO();
        boolean check = false;
        try {
            if (status.equals("New")) {
                check = dao.deleteNewRequest(idResource);
            } else {
                check = dao.deleteApprovedRequest(idResource);
            }
            if (check) {
                HttpSession session = request.getSession();
                String dateFrom = (String) session.getAttribute("DATE_FROM");
                String dateTo = (String) session.getAttribute("DATE_TO");
                int curPage = (int) session.getAttribute("PAGE");
                int page = 0;
                int totalRequest = 0;
                UserDTO user = (UserDTO) session.getAttribute("user");
                String email = user.getEmail();
                int ordinaryNumber = 0;
                int pageSize = 10;
                List<RentalDTO> listHistory = null;

                if (dateFrom.equals("") && dateTo.equals("")) {
                    ordinaryNumber = (curPage - 1) * pageSize;
                    listHistory = historyDAO.loadRequestHistory(email, ordinaryNumber);
                    if (listHistory.isEmpty()) {
                        page = curPage - 1;
                        if (page == 0) {
                            page = 1;
                        }
                    } else {
                        page = curPage;
                    }

                } else {
                    ordinaryNumber = (curPage - 1) * pageSize;
                    listHistory = historyDAO.loadRequestHistoryBetweenDate(email, dateFrom, dateTo, ordinaryNumber);
                    if (listHistory.isEmpty()) {
                        page = curPage - 1;
                        if (page == 0) {
                            page = 1;
                        }
                    } else {
                        page = curPage;
                    }
                }

                //ko chuyển về loadRequest nữa lấy data từ db rồi chỉnh trang ==> trả thẳng về historyPage
                url = "DispatchServlet?btnAction=loadRequestHistory"
                        + "&dateFrom=" + dateFrom
                        + "&dateTo=" + dateTo
                        + "&page=" + page;
            }

        } catch (SQLException e) {
            log("Have error with database...", e.getCause());
        } catch (NamingException e) {
            log("Couldn't find the DataSource...", e.getCause());
        } finally {
            response.sendRedirect(url);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
