/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.MentorRegister;

import dal.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import model.*;
import utility.Utilities;

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
        Utilities uti = new Utilities();
        String accept = request.getParameter("accept");
        String reject = request.getParameter("reject");
        int userId = Integer.parseInt(request.getParameter("userID"));
        String newSkill = request.getParameter("newSkills");
        
        if(accept != null) {
            int mentorID = uti.getMentorIDByUser(userId);
            
            dao.updateActiveMentor(userId);
            
            if(newSkill != null) {
                String[] arrNewSkill = newSkill.split(" ");
                for(int i = 0; i < arrNewSkill.length; i++) {
                    String itemSkill = arrNewSkill[i].substring(0, 1).toUpperCase() + arrNewSkill[i].substring(1).toLowerCase();
                    dao.insertNewSkills(itemSkill);
                    int skillID = uti.getSkillIdBySkillName(arrNewSkill[i]);
                    dao.insertEnrollSkill(mentorID, skillID);
                    
                }
                
            }
        }
        if(reject != null) {
            dao.deleteMentorRegister(userId);
            dao.deleteMentor(userId);
        }
        
        doGet(request, response);
    }
}
