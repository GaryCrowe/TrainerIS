/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formHandlers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trainerISControlers.CustomerController;
import trainerISModels.Customer;

/**
 *
 * @author gc
 */
@WebServlet(name = "CustomerFormHandler", urlPatterns = {"/CustomerFormHandler"})
public class CustomerFormHandler extends HttpServlet {

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
        try {
            String fname = request.getParameter("fName");
            String lname = request.getParameter("lName");
            String address = request.getParameter("address");
            String mobile = request.getParameter("mobile");
            String landLine = request.getParameter("landLine");
// figure out later
            //boolean gender = request.getParameter("male");
            String option = request.getParameter("option");
            
            Customer c = new Customer(fname,lname,address,mobile,landLine,"");
            CustomerController cc = new CustomerController();
            boolean result = cc.customerAction(option,c);
            
            
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            if (result) {
                request.setAttribute("message", "Customer Saved");
            } else {
                request.setAttribute("message", "Customer did NOT save");
            }
            rd.forward(request, response);
            

        } finally {
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
