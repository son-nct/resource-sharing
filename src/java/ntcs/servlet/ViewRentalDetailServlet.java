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
import ncts.dao.RentalDetailDAO;
import ntcs.dto.ViewDetailDTO;

/**
 *
 * @author WIN 10
 */
@WebServlet(name = "ViewRentalDetailServlet", urlPatterns = {"/ViewRentalDetailServlet"})
public class ViewRentalDetailServlet extends HttpServlet {

    private final String VIEW_RENTAL_DETAIL = "viewRentalDetail.jsp";

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
        String url = VIEW_RENTAL_DETAIL;
        HttpSession session = request.getSession();
        try {
            String idRental = request.getParameter("idRental");
            String requestDate = request.getParameter("requestDate");
            String email = request.getParameter("email");
            String page = request.getParameter("page");
            String status = request.getParameter("status");
            
            
            session.setAttribute("EMAIL", email);
            session.setAttribute("REQ_DATE", requestDate);
            session.setAttribute("IDRENTAL", idRental);
            session.setAttribute("PAGE", page);
            session.setAttribute("STATUS", status);

            //xóa listError check Quantity
            session.removeAttribute("CANT_ACCEPT");
            session.removeAttribute("LIST_ERROR");

            RentalDetailDAO dao = new RentalDetailDAO();
            List<ViewDetailDTO> listDetail = dao.showDetail(idRental);
            session.setAttribute("LIST_DETAIL", listDetail);

            url = VIEW_RENTAL_DETAIL;

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
