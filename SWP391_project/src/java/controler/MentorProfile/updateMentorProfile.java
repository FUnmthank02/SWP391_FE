/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.MentorProfile;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utility.Utilities;

/**
 *
 * @author Admin
 */
public class updateMentorProfile extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utilities uti = new Utilities();
        DAO dao = new DAO();
        String bio = request.getParameter("bio");
        String[] skill = request.getParameterValues("skill");
        String exp = request.getParameter("exp");
        String achievement = request.getParameter("achievement");
        int mentorId = Integer.parseInt(request.getParameter("mentorId"));
        
        dao.deleteEnrollSkillByMentorID(mentorId);
        for(int i = 0; i < skill.length; i++) {
            dao.insertEnrollSkill(mentorId, Integer.parseInt(skill[i]));
            
        }
        response.sendRedirect("view-list-mentor-register");
        
    }

}
