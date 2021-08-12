/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ntcs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

    private final String SIGN_UP_PAGE = "signUp.jsp";

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
        String url = null;
        try {
            /* TODO output your page here. You may use following sample code. */
            UserDAO dao = new UserDAO();
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String rePassword = request.getParameter("txtRepassword");

            String displayName = request.getParameter("txtDisplayName");
            String phone = request.getParameter("txtPhone");
            String address = request.getParameter("txtAddress");
            String role = "Employee";
            String status = "new";

            int error = 0;

            String email_exit = dao.checkEmail(email);
            if ((email.length() >= 1) && email_exit != null) {
                String exit_user = "Email is already exist. Try again !";
                request.setAttribute("exit_user", exit_user);
                error++;
            }

            if (email.length() == 0) {
                String empty_email = "Please input your Email !";
                request.setAttribute("empty_email", empty_email);
                error++;
            }
            if(!email.matches("^[A-Za-z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$")) {
                String invalid_email = "Not valid email. Try again !";
                request.setAttribute("invalid_email", invalid_email);
                error++;
            }

            if (password.length() == 0) {
                request.setAttribute("empty_password", "Please input your password !");
                error++;
            }
            
            if (!rePassword.equals(password)) {
                request.setAttribute("not_match", "Password and Repassword not match. Try again!");
                error++;
            }

            if (displayName.length() == 0) {
                request.setAttribute("empty_displayName", "Please input your displayName !");
                error++;
            }

            if (phone.length() == 0) {
                String empty_phone = "Please input your phone number !";
                request.setAttribute("empty_phone", empty_phone);
                error++;
            }

            if (phone.length() >= 1 && !phone.matches("^0\\d{9,11}$")) {
                String error_phone = "Not valid phone number. Try again !";
                request.setAttribute("error_phone", error_phone);
                error++;
            }

            if (address.length() == 0) {
                request.setAttribute("empty_address", "Please input your address !");
                error++;
            }

            if (error > 0) {
                url = SIGN_UP_PAGE;
            } else {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                String createDate = format.format(date);

                UserDTO dto = new UserDTO(email, password, phone, displayName, address, createDate, status, role);
                boolean check = dao.createUser(dto);
                if (check) {
                    session.setAttribute("user_register", dto);
                    int code = (int) Math.round(Math.random() * 999999);
                    session.setAttribute("code", code);
                    url = "DispatchServlet?btnAction=verify";
                }

            }

        } catch (SQLException e) {
            log("Have error with database...", e.getCause());
        } catch (NamingException e) {
            log("Couldn't find the Datasource...", e.getCause());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
//            response.sendRedirect(url);
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
