/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Request;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Mentee;
import model.Mentor;
import model.Request;
import model.User;

/**
 *
 * @author Admin
 */
public class ViewRequest extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewRequest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewRequest at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    DAO d = new DAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        //paramter that express user is mentor (true) or mentee (false)
        boolean role = false;

        //get user 
        User U = (User) request.getSession().getAttribute("user"); 
        User from = new User();
        User to = new User();
        //find out if user is mentee or mentor
        Mentor mentor = null;
        Mentee mentee = null;
        try {
            mentor = d.getMentor(U);
        } catch (Exception e) {
        }
        if (mentor != null) {
            role = true;
            to = U;             
        }
        else
        {
            mentee = d.getMentee(U);
            from = U;
        }
        ArrayList<Request> requests = d.getRequests(U, role);
        ArrayList<User> mentorUsers = d.getUser(mentee);
        ArrayList<User> menteeUsers = d.getMenteeUsers(mentor);
        
        request.setAttribute("user", U);
        request.setAttribute("mentorUsers", mentorUsers);
        request.setAttribute("menteeUsers", menteeUsers);
        request.setAttribute("role", role);
        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("requests", requests);
        request.getRequestDispatcher("view/viewRequest.jsp").forward(request, response);
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
        //processRequest(request, response);

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
