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
@WebServlet(name = "LoadRequestHistoryServlet", urlPatterns = {"/LoadRequestHistoryServlet"})
public class LoadRequestHistoryServlet extends HttpServlet {

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

        String date_from = request.getParameter("dateFrom");
        String date_to = request.getParameter("dateTo");

        String url = null;
        HttpSession session = request.getSession();
        try {
            HistoryDAO historyDAO = new HistoryDAO();
            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            String email = userDTO.getEmail();

            int pageSize = 10;
            int page = 0;
            int ordinaryNumber = 0;
            int totalPage = 0;
            int totalRequest;

            String curPage = request.getParameter("page");
            if (curPage == null) {
                ordinaryNumber = 0;
                page = 1;
            } else {
                ordinaryNumber = (Integer.parseInt(curPage) - 1) * pageSize;
                page = Integer.parseInt(curPage);
            }

            List<RentalDTO> listHistory = null;

            if (date_from == null && date_to == null) {
                date_from = "";
                date_to = "";
            }

            if (date_from.equals("") && date_to.equals("")) {
                totalRequest = historyDAO.countHistory(email);
                listHistory = historyDAO.loadRequestHistory(email, ordinaryNumber);

            } else {
                totalRequest = historyDAO.countHistoryBetweenDate(email, date_from, date_to);
                listHistory = historyDAO.loadRequestHistoryBetweenDate(email, date_from, date_to, ordinaryNumber);
            }

            totalPage = (int) Math.ceil(totalRequest / 10.0);

            if (listHistory.isEmpty() && date_from.equals("") && date_to.equals("")) {
                if (curPage != null) {
                    ordinaryNumber = (Integer.parseInt(curPage) - 2) * pageSize;
                    if (ordinaryNumber < 0) {
                        ordinaryNumber = 0;
                        page = 1;
                    }
                    page = Integer.parseInt(curPage);
                    listHistory = historyDAO.loadRequestHistory(email, ordinaryNumber);
                }
            } else if (listHistory.isEmpty() && !date_from.equals("") && !date_to.equals("")) {
                listHistory = historyDAO.loadRequestHistoryBetweenDate(email, date_from, date_to, ordinaryNumber);
            }


            session.setAttribute("LIST_HISTORY", listHistory);
            session.setAttribute("TOTAL_PAGE", totalPage);
            session.setAttribute("TOTAL_REQUEST", totalRequest);

            session.setAttribute("DATE_FROM", date_from);
            session.setAttribute("DATE_TO", date_to);

            session.setAttribute("PAGE", page);

            url = "DispatchServlet?btnAction=historyRequest"
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
