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
import java.util.ArrayList;
import model.*;
import utility.Utilities;

/**
 *
 * @author Admin
 */
public class viewListMentorRegister extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        Utilities uti = new Utilities();
        
        ArrayList<MentorRegister> listMentorRegister = dao.getAllMentorRegister();
        ArrayList<Skill> skills = dao.getSkill();
        ArrayList<MentorRegister> newMentorRegister = new ArrayList<>();
        
        if (!listMentorRegister.isEmpty()) {
            String newSkill = "";
            for (MentorRegister i : listMentorRegister) {
                if(i.getNewSkill()!=null) 
                    newSkill = uti.handleSplitNewSkill(i.getNewSkill(), ",");
                String existedSkill = uti.handleSplitExistedSkill(i.getExistedSkill(), " ");

                //gan i.getUser() = getUserbyuserID()
                User u = uti.getUser(i.getUser().getUserId());
                
                // add to new list Mentor register
                newMentorRegister.add(new MentorRegister(i.getMentorRegisterId(), i.getAchievement(), i.getExp(),
                        i.getBio(), existedSkill, newSkill, u, i.getSeenStatus()));
                                
            }
        }
        if(!newMentorRegister.isEmpty()) {
            request.setAttribute("dao", dao);
            request.setAttribute("listMentorRegister", newMentorRegister);
        } else request.setAttribute("listMentorRegisterEmpty", "There is no mentor register's request");

        request.setAttribute("as", skills);
        request.getRequestDispatcher("view/viewMentorRegisterRequest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
