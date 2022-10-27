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
import model.Skill;
import model.User;
import utility.Utilities;

/**
 *
 * @author Admin
 */
public class CreateRequest extends HttpServlet {

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
            out.println("<title>Servlet CreateRequest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateRequest at " + request.getContextPath() + "</h1>");
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
    utility.Utilities u = new Utilities();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get user 
        User U = (User) request.getSession().getAttribute("user");

        //get all skill for header
        ArrayList<Skill> skills = d.getSkill();
        //get mentee of current user
        Mentee mentee = d.getMentee(U);
        //get information of mentors belong to a mentee
        ArrayList<User> mi = d.getUser(mentee);
        request.setAttribute("as", skills);
        request.setAttribute("mi", mi);
        request.getRequestDispatcher("view/createRequest.jsp").forward(request, response);
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

        //get user 
        User U = (User) request.getSession().getAttribute("user");

//        //get mentee of current user
        Mentee mentee = d.getMentee(U);

        //get userID of mentor
        int userID = Integer.parseInt(request.getParameter("mentor"));
        User u = new User();
        u.setUserId(userID);
        Mentor mentor = d.getMentor(u);

        //get request title
        String title = request.getParameter("title");

        //get request content
        String content = request.getParameter("requestContent");

        //request status
        String status = "Processing";

        //request skill ID
        int skillID = 1;
        Skill skill = new Skill();
        skill.setSkillId(skillID);

        model.Request req = new Request();
        req.setMentee(mentee);
        req.setMentor(mentor);
        req.setReqContent(content);
        req.setStatus(status);
        req.setTitle(title);

        d.insertRequest(req);
        response.sendRedirect("CreateRequest");
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
