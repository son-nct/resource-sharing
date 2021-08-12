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
@WebServlet(name = "DeletePendingServlet", urlPatterns = {"/DeletePendingServlet"})
public class DeletePendingServlet extends HttpServlet {

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
        String url = null;
        String idRental = request.getParameter("idRental");
        ProcessRequestDAO processDAO = new ProcessRequestDAO();
        HttpSession session = request.getSession();
        try {

            String dateFrom = (String) session.getAttribute("DATE_FROM");
            String dateTo = (String) session.getAttribute("DATE_TO");
            String status = (String) session.getAttribute("STATUS");
            String searchValue = (String) session.getAttribute("SEARCH_VALUE");
            String curPage = (String) session.getAttribute("PAGE");

            int count = 0;
            int pageSize = 10;
            int page = 0;
            int totalPage = 0;

            List<RentalDTO> listProcess = null;

            boolean check = processDAO.deleteRequest(idRental);
            if (check) {
                int ordinaryNumber = (Integer.parseInt(curPage) - 1) * pageSize;
                page = Integer.parseInt(curPage);

                if (dateFrom == null && dateTo == null || dateFrom.equals("") && dateTo.equals("")) {
                    listProcess = processDAO.loadRequestExceptDeleteStatus(status, searchValue, ordinaryNumber);

                    //nếu list trống và  date = "" ==> chuyển về trang trước
                    // nếu trang hiện tại bằng 1 thì giữ nguyên
                    if (listProcess.isEmpty()) {
                        if (curPage != null) {
                            ordinaryNumber = (Integer.parseInt(curPage) - 2) * pageSize;
                            if (ordinaryNumber < 0) {
                                ordinaryNumber = 0;
                                page = 1;
                            }
                            page = Integer.parseInt(curPage) - 1;
                            listProcess = processDAO.loadRequestExceptDeleteStatus(status, searchValue, ordinaryNumber);

                        }
                    }
                    count = processDAO.countRequestExceptDeleteStatus(status, searchValue);
                    totalPage = (int) Math.ceil(count / 10.0);

                } else {
                    listProcess = processDAO.loadRequestBetweenDateExceptDeleteStatus(status, searchValue, dateFrom, dateTo, ordinaryNumber);

                    if (listProcess.isEmpty() && !dateFrom.equals("") && !dateTo.equals("")) {
                        if (curPage != null) {
                            ordinaryNumber = (Integer.parseInt(curPage) - 2) * pageSize;
                            if (ordinaryNumber < 0) {
                                ordinaryNumber = 0;
                                page = 1;
                            }
                        }
                        page = Integer.parseInt(curPage) - 1;
                        listProcess = processDAO.loadRequestBetweenDateExceptDeleteStatus(status, searchValue, dateFrom, dateTo, ordinaryNumber);
                    }

                    count = processDAO.countRequestBetweenDateExceptDeleteStatus(status, searchValue, dateFrom, dateTo);
                    totalPage = (int) Math.ceil(count / 10.0);

                }

                session.setAttribute("LIST_PROCESS", listProcess);
                session.setAttribute("TOTAL_REQUEST", count);
                session.setAttribute("TOTAL_PAGE", totalPage);

                session.setAttribute("DATE_FROM", dateFrom);
                session.setAttribute("DATE_TO", dateTo);
                session.setAttribute("SEARCH_VALUE", searchValue);

                url = "DispatchServlet?btnAction=manager&status=" + status
                        + "&searchValue=" + searchValue
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
