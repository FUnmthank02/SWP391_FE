/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.MentorRegister;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class actionForMentorRegister extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("view-list-mentor-register");
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        String accept = request.getParameter("accept");
        String reject = request.getParameter("reject");
        int userId = Integer.parseInt(request.getParameter("userID"));
        
        if(accept != null) {
            dao.updateActiveMentor(userId);
        }
        if(reject != null) {
            dao.deleteMentorRegister(userId);
            dao.deleteMentor(userId);
        }
        
        doGet(request, response);
    }
}
