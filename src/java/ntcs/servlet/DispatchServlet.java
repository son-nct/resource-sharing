/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ntcs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author WIN 10
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

    private final String LOGIN_PAGE = "signIn.jsp";
    private final String LOGIN_SERVLET = "LoginServlet";
    private final String LOGIN_GG_SERVLET = "LoginGGServlet";
    private final String LOGOUT_SERVLET = "LogOutServlet";
    private final String LOAD_CATEGORY = "LoadCategoryServlet";
    private final String SIGN_UP_GG_SERVLET = "SignUpGGServlet";
    private final String SIGN_UP_SERVLET = "SignUpServlet";
    private final String VERIFY_PAGE = "verifyAccount.jsp";
    private final String CHECK_VERIFY_ACCOUNT = "CheckActivateServlet";
    private final String SEARCH_RESOURCE_SERVLET = "SearchResourceServlet";
    private final String RENTAL_PAGE = "rentalPage.jsp";
    private final String ADD_REQUEST_SERVLET = "AddRequestServlet";
    private final String DELETE_REQUEST_SERVLET = "DeleteRequestServlet";
    private final String VIEW_REQUEST_SERVLET = "ViewRequestServlet";
    private final String UPDATE_QUANTITY_SERVLET = "UpdateQuantityServlet";
    private final String SEND_REQUEST_SERVLET = "SendRequestServlet";
    private final String LOAD_REQUEST_HISTORY_SERVLET = "LoadRequestHistoryServlet";
    private final String HISTORY_PAGE = "requestHistory.jsp";
    private final String LOAD_SEARCH_REQUEST_MANAGER = "LoadSearchRequestManager";
    private final String MANAGER_PAGE = "manager.jsp";
    private final String VIEW_RENTAL_DETAIL = "ViewRentalDetailServlet";
    private final String VIEW_DETAIL_HISTORY = "ViewDetailHistoryServlet";
    private final String DELETE_HISTORY = "DeleteRentalServlet";
    private final String DELETE_PENDING_SERVLET = "DeletePendingServlet";
    private final String ACCEPT_PENDING_SERVLET = "AcceptPendingServlet";

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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = LOGIN_PAGE;

        String button = request.getParameter("btnAction");
        try {
            if (button == null) {
            } else if (button.equals("Login")) {
                url = LOGIN_SERVLET;
            } else if (button.equals("loginGG")) {
                url = LOGIN_GG_SERVLET;
            } else if (button.equals("logOut")) {
                url = LOGOUT_SERVLET;
            } else if (button.equals("signUpGG")) {
                url = SIGN_UP_GG_SERVLET;
            } else if (button.equals("signUp")) {
                url = SIGN_UP_SERVLET;
            } else if (button.equals("verify")) {
                url = VERIFY_PAGE;
            } else if (button.equals("checkActivate")) {
                url = CHECK_VERIFY_ACCOUNT;
            } else if (button.equals("search")) {
                url = SEARCH_RESOURCE_SERVLET;
            } else if (button.equals("rentalPage")) {
                url = RENTAL_PAGE;
            } else if (button.equals("AddRequest")) {
                url = ADD_REQUEST_SERVLET;
            } else if (button.equals("ViewRequest")) {
                url = VIEW_REQUEST_SERVLET;
            } else if (button.equals("updateQuantity")) {
                url = UPDATE_QUANTITY_SERVLET;
            } else if (button.equals("deleteRequest")) {
                url = DELETE_REQUEST_SERVLET;
            } else if (button.equals("sendRequest")) {
                url = SEND_REQUEST_SERVLET;
            } else if (button.equals("loadRequestHistory")) {
                url = LOAD_REQUEST_HISTORY_SERVLET;
            } else if (button.equals("historyRequest")) {
                url = HISTORY_PAGE;
            } else if (button.equals("detailHistory")) {
                url = VIEW_DETAIL_HISTORY;
            } else if (button.equals("loadCategory")) {
                url = LOAD_CATEGORY;
            } else if (button.equalsIgnoreCase("loadSearchManager")) {
                url = LOAD_SEARCH_REQUEST_MANAGER;
            } else if (button.equalsIgnoreCase("manager")) {
                url = MANAGER_PAGE;
            } else if (button.equalsIgnoreCase("viewRentalDetail")) {
                url = VIEW_RENTAL_DETAIL;
            } else if (button.equalsIgnoreCase("deleteHistory")) {
                url = DELETE_HISTORY;
            } else if (button.equalsIgnoreCase("DeletePending")) {
                url = DELETE_PENDING_SERVLET;
            }else if(button.equalsIgnoreCase("AcceptPending")) {
                url = ACCEPT_PENDING_SERVLET;
            }
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
