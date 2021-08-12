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
import ncts.dao.CategoryDAO;
import ncts.dao.ResourceDAO;
import ntcs.dto.CategoryDTO;
import ntcs.dto.UserDTO;

/**
 *
 * @author WIN 10
 */
@WebServlet(name = "LoadCategoryServlet", urlPatterns = {"/LoadCategoryServlet"})
public class LoadCategoryServlet extends HttpServlet {

    private final String RENTAL_PAGE = "rentalPage.jsp";
    private final String MANAGER_PAGE = "manager.jsp";
    

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
        try {
            CategoryDAO dao = new CategoryDAO();
            ResourceDAO resource_dao = new ResourceDAO();
            HttpSession session = request.getSession();
            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            List<CategoryDTO> listCate = null;

            if (userDTO.getRole().equalsIgnoreCase("Employee")) {
                listCate = dao.loadCategoryEmployee();
                session.setAttribute("LISTCATE", listCate);

                url = RENTAL_PAGE;

            } else if (userDTO.getRole().equalsIgnoreCase("Leader")) {
                listCate = dao.loadCategoryFull();
                session.setAttribute("LISTCATELEADER", listCate);
                url = RENTAL_PAGE;
            } else {
                List<String> listCateManager = dao.loadCategoryForManager();
                session.setAttribute("LISTCATEMANAGER", listCateManager);
                url = "DispatchServlet?btnAction=loadSearchManager";
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
