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
import ncts.dao.ProcessRequestDAO;
import ntcs.dto.RentalDTO;

/**
 *
 * @author WIN 10
 */
@WebServlet(name = "LoadSearchRequestManager", urlPatterns = {"/LoadSearchRequestManager"})
public class LoadSearchRequestManager extends HttpServlet {

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

        HttpSession session = request.getSession();
        String status = request.getParameter("cbo");

        String searchValue = request.getParameter("searchValue");
        String currentPage = request.getParameter("page");

        String date_from = request.getParameter("dateFrom");
        String date_to = request.getParameter("dateTo");

        String url = null;

        ProcessRequestDAO processRequestDAO = new ProcessRequestDAO();
        try {
            

            int ordinaryNumber = 0;
            int page = 0;
            int pageSize = 10;
            int totalPage = 0;
            int count = 0;

            if (currentPage == null) {
                ordinaryNumber = 0;
                page = 1;
            } else {
                ordinaryNumber = (Integer.parseInt(currentPage) - 1) * pageSize;
                page = Integer.parseInt(currentPage);
            }

            if (searchValue == null) {
                searchValue = "";
            }

            if (status == null) {
                status = "New";
            }

            List<RentalDTO> listProcess = null;
            if (date_from == null && date_to == null || date_from.equals("") && date_to.equals("")) {

                if (!status.equals("Delete")) {
                    listProcess = processRequestDAO.loadRequestExceptDeleteStatus(status, searchValue, ordinaryNumber);
                    count = processRequestDAO.countRequestExceptDeleteStatus(status, searchValue);

                } else {
                    listProcess = processRequestDAO.loadRequestWithDeleteStatus(status, searchValue, ordinaryNumber);
                    count = processRequestDAO.countRequestWithDeleteStatus(status, searchValue);
                }

                session.setAttribute("LIST_PROCESS", listProcess);
                totalPage = (int) Math.ceil(count / 10.0);

            } else {
                if (!status.equals("Delete")) {
                    listProcess = processRequestDAO.loadRequestBetweenDateExceptDeleteStatus(status, searchValue, date_from, date_to, ordinaryNumber);
                    count = processRequestDAO.countRequestBetweenDateExceptDeleteStatus(status, searchValue, date_from, date_to);
                } else {
                    listProcess = processRequestDAO.loadRequestBetweenDateWithDeleteStatus(status, searchValue, date_from, date_to, ordinaryNumber);
                    count = processRequestDAO.countRequestBetweenDateWithDeleteStatus(status, searchValue, date_from, date_to);
                }

                session.setAttribute("LIST_PROCESS", listProcess);
                totalPage = (int) Math.ceil(count / 10.0);

            }
            session.setAttribute("DATE_FROM", date_from);
            session.setAttribute("DATE_TO", date_to);

            session.setAttribute("TOTAL_REQUEST", count);
            session.setAttribute("TOTAL_PAGE", totalPage);
            session.setAttribute("STATUS", status);
            session.setAttribute("SEARCH_VALUE", searchValue);

            url = "DispatchServlet?btnAction=manager&status=" + status
                    + "&searchValue=" + searchValue
                    + "&dateFrom=" + date_from
                    + "&dateTo=" + date_to
                    + "&page=" + page;

        } catch (SQLException e) {
            log("Have error with database...", e.getCause());
        } catch (NamingException e) {
            log("Couldn't find the DataSource...", e.getCause());
        } finally {
            response.sendRedirect(url);
            out.close();
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
