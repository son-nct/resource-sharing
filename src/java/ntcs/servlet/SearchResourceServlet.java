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
import ncts.dao.ResourceDAO;
import ntcs.dto.ResourceDTO;
import ntcs.dto.UserDTO;

/**
 *
 * @author WIN 10
 */
@WebServlet(name = "SearchResourceServlet", urlPatterns = {"/SearchResourceServlet"})
public class SearchResourceServlet extends HttpServlet {

    private final String RENTAL_PAGE = "rentalPage.jsp";

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

        ResourceDAO resourceDAO = new ResourceDAO();
        HttpSession session = request.getSession();
        RentalDetailDAO rentalDetailDAO = new RentalDetailDAO();
        String url = null;
        try {
            String category = request.getParameter("category");
            String date = request.getParameter("date");
            String searchValue = request.getParameter("searchValue").trim();

            int totalElement = 0;
            int totalPage = 0;
            int pageSize = 10;
            
            int page = 0;
            int ordinaryNumber = 0;
            String curPage = request.getParameter("page");
            
            if(curPage == null) {
                ordinaryNumber = 0;
                page = 1;
            }else {
               ordinaryNumber = (Integer.parseInt(curPage) -1) * pageSize;
               page = Integer.parseInt(curPage);
            }
            
            
            
            List<ResourceDTO> listResource = null;

            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            if (userDTO.getRole().equals("Leader")) {
                totalElement = resourceDAO.countAllResourceWithSearchValue(searchValue, category);
                totalPage = (int) Math.ceil(totalElement / 10.0);

                listResource = resourceDAO.loadResourceForLeaderWithSearchValue(ordinaryNumber, searchValue, category);

            } else if (userDTO.getRole().equals("Employee")) {
                totalElement = resourceDAO.countResourceEmployeeWithSearchValue(searchValue, category);
                totalPage = (int) Math.ceil(totalElement / 10.0);

                listResource = resourceDAO.loadResourceForEmployeeWithSearchValue(ordinaryNumber, searchValue, category);

            }

            session.setAttribute("LIST_RESOURCE", listResource);
            session.setAttribute("TOTALPAGE", totalPage);
            session.setAttribute("TOTALELEMENT", totalElement);
            
            /*Lưu session để khi vào history ko bị mất phần đang search*/
            session.setAttribute("SEARCH_VALUE", searchValue);
            session.setAttribute("CATEGORY", category);
            session.setAttribute("DATE", date);
            session.setAttribute("PAGE", page);
            
            url = "DispatchServlet?btnAction=rentalPage&category=" + category
                    + "&date=" + date
                    + "&searchValue=" + searchValue
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
