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
import jakarta.servlet.http.HttpSession;
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
        HttpSession ses = request.getSession();
        ArrayList<MentorRegister> listMentorRegister = new ArrayList<>();

        User user = (User) ses.getAttribute("user");

        // da dang nhap roi
        if (user != null) {
            request.setAttribute("isValidUser", uti.isValidUser(user));    

            //la admin
            if (dao.getAdminByUserId(user) != null) {
                dao.UpdateListMentorRegisterSeen();
                listMentorRegister = dao.getNotifyMentorRegister();
                request.setAttribute("isAdmin", true);
                ArrayList<MentorRegister> listMentorRegisters = dao.getAllMentorRegister();
                ArrayList<Skill> skills = dao.getSkill();
                ArrayList<MentorRegister> newMentorRegister = new ArrayList<>();

                if (!listMentorRegisters.isEmpty()) {
                    String newSkill = "";
                    for (MentorRegister i : listMentorRegisters) {
                        if (i.getNewSkill() != null) {
                            newSkill = uti.handleSplitNewSkill(i.getNewSkill(), ",");
                        }
                        String existedSkill = uti.handleSplitExistedSkill(i.getExistedSkill(), " ");

                        //gan i.getUser() = getUserbyuserID()
                        User u = uti.getUser(i.getUser().getUserId());

                        // add to new list Mentor register
                        newMentorRegister.add(new MentorRegister(i.getMentorRegisterId(), i.getAchievement(), i.getExp(),
                                i.getBio(), existedSkill, newSkill, u, i.getSeenStatus()));

                    }
                }
                if (!newMentorRegister.isEmpty()) {
                    request.setAttribute("dao", dao);
                    request.setAttribute("listMentorRegisters", newMentorRegister);
                } else {
                    request.setAttribute("listMentorRegisterEmpty", "There is no mentor register's request");
                }

                request.setAttribute("listMentorRegisterSize", listMentorRegister.size());
                request.setAttribute("as", skills);
                request.getRequestDispatcher("view/viewMentorRegisterRequest.jsp").forward(request, response);
            } 
            else response.sendRedirect("home");
        }
        else response.sendRedirect("home");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
