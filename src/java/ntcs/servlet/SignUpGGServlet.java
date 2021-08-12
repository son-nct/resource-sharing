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
@WebServlet(name = "SignUpGGServlet", urlPatterns = {"/SignUpGGServlet"})
public class SignUpGGServlet extends HttpServlet {

    private final String SIGN_UP_GG_PAGE = "signUpGG.jsp";

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
        String name = request.getParameter("TxtGGName");
        String phone = request.getParameter("TxtGGPhone");
        String address = request.getParameter("TxtGGAddress");
        PrintWriter out = response.getWriter();
        String status = "activate";
        String role = "Employee";
        String url = null;

        HttpSession session = request.getSession();
        try {
            /* TODO output your page here. You may use following sample code. */
            int countError = 0;

            if (name.length() == 0) {
                String empty_name = "Please input your display name!";
                request.setAttribute("empty_name", empty_name);
                session.setAttribute("name", name);
                countError++;
            } else {
                session.setAttribute("name", name);
            }

            if (phone.length() == 0) {
                String empty_phone = "Please input your phone number!";
                request.setAttribute("empty_phone", empty_phone);
                countError++;
            }

            if (phone.length() >= 1 && !phone.matches("^0\\d{9,11}$")) {
                String error_phone = "Not valid phone number. Try again!";
                request.setAttribute("error_phone", error_phone);
                countError++;
            }

            if (address.length() == 0) {
                String empty_address = "Please input your address!";
                request.setAttribute("empty_address", empty_address);
                countError++;
            }

            if (countError > 0) {
                url = SIGN_UP_GG_PAGE;
            } else {

                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                String createDate = format.format(date);

                String email = (String) session.getAttribute("email");
                UserDTO dto = new UserDTO(email, phone, name, address, createDate, role, status);
                UserDAO dao = new UserDAO();
                boolean check = dao.createUser(dto);
                if (check) {
                    session.removeAttribute("email");
                    session.removeAttribute("name");
                    url = "DispatchServlet?btnAction=loginGG&ggEmail=" + email;
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
