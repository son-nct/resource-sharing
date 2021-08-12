/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ntcs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ncts.dao.UserDAO;
import ntcs.dto.UserDTO;

/**
 *
 * @author WIN 10
 */
@WebServlet(name = "CheckActivateServlet", urlPatterns = {"/CheckActivateServlet"})
public class CheckActivateServlet extends HttpServlet {

    private final String VERYFI_PAGE = "verifyAccount.jsp";
    private final String SUCCESS_PAGE = "success.jsp";
    private final String ERROR_SERVER = "errorInternalServer.html";

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
        int codeEmail = (int) session.getAttribute("code");
        String code = request.getParameter("txtCode").trim();
        UserDTO userDTO = (UserDTO) session.getAttribute("user_register");
        String url = null;
        try {

            if (code.length() == 0) {
                request.setAttribute("empty_code", "Please input activate code!");
                request.setAttribute("already_send", "true");
                url = VERYFI_PAGE;
            } else {
                int code_activate = Integer.parseInt(code);
                if (code_activate != codeEmail) {
                    request.setAttribute("error_code", "Invalid Code");
                    request.setAttribute("already_send", "true");
                    url = VERYFI_PAGE;
                } else {
                    String status = "activate";
                    UserDAO dao = new UserDAO();
                    boolean check = dao.changeStatusAccount(status, userDTO.getEmail());
                    if (check) {
                        url = SUCCESS_PAGE;
                        session.invalidate();
                    } else {
                        url = ERROR_SERVER;
                    }
                }
            }
        } catch (SQLException e) {
            log("Have error with database...", e.getCause());
        } catch (NamingException e) {
            log("Couldn't find the DataSource...", e.getCause());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
