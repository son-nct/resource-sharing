/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ntcs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ncts.dao.RentalDAO;
import ncts.dao.RentalDetailDAO;
import ntcs.dto.CheckQuantityDTO;
import ntcs.dto.RentalDTO;
import ntcs.dto.RentalDetailDTO;
import ntcs.dto.RequestObj;
import ntcs.dto.RequestResourceDTO;
import ntcs.dto.UserDTO;

/**
 *
 * @author WIN 10
 */
@WebServlet(name = "SendRequestServlet", urlPatterns = {"/SendRequestServlet"})
public class SendRequestServlet extends HttpServlet {

    private final String RENTAL_PAGE = "rentalPage.jsp";
    private final String VIEW_REQUEST_PAGE = "viewRequest.jsp";

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
        HttpSession session = request.getSession();
        RentalDAO rentalDAO = new RentalDAO();
        RentalDetailDAO detailDAO = new RentalDetailDAO();
        String idRental = null;
        try {

            RequestObj cart = (RequestObj) session.getAttribute("CART");
            if (cart != null) {
                session.removeAttribute("rentalId");
                Map<RequestResourceDTO, Integer> items = cart.getItems();
                List<CheckQuantityDTO> listError = new ArrayList<>();
                for (RequestResourceDTO requestResource : items.keySet()) {
                    int quantity = requestResource.getQuantityResource() - items.get(requestResource);
                    if (quantity < 0) {
                        CheckQuantityDTO error = new CheckQuantityDTO(requestResource.getIdResource(), requestResource.getRentalDate());
                        listError.add(error);
                    }
                }
                if (!listError.isEmpty()) {
                    session.setAttribute("LIST_ERROR", listError);
                    url = VIEW_REQUEST_PAGE;
                } else {
                    session.removeAttribute("LIST_ERROR");
                    idRental = (String) session.getAttribute("rentalId");
                    if (idRental == null) {
                        idRental = UUID.randomUUID().toString();
                        session.setAttribute("rentalId", idRental);
                    }
                    UserDTO user = (UserDTO) session.getAttribute("user");
                    String email = user.getEmail();
                    String dateRequest = java.time.LocalDate.now().toString();
                    String status = "New";
                    RentalDTO rentalDTO = new RentalDTO(idRental, email, dateRequest, status);
                    boolean createRental = rentalDAO.createRental(rentalDTO);
                    if (createRental) {
                        for (RequestResourceDTO requestResource : items.keySet()) {
                            String idDetail = UUID.randomUUID().toString();
                            String idResource = requestResource.getIdResource();
                            String date = requestResource.getRentalDate();
                            int quantity = items.get(requestResource);

                            RentalDetailDTO detailDTO = new RentalDetailDTO(idDetail, idRental, idResource, date, date, quantity);
                            detailDAO.createDetail(detailDTO);
                        }
                        session.removeAttribute("MAP_ITEMS");
                        session.removeAttribute("CART");

                        url = "DispatchServlet?btnAction=loadRequestHistory";
                    }
                }
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
