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
public class mentorRegister extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        Utilities uti = new Utilities();
        HttpSession ses = request.getSession();
        User user = (User) ses.getAttribute("user");
        ArrayList<Request> listReq = new ArrayList<>();
        ArrayList<Response> listRes = new ArrayList<>();
        ArrayList<Invitation> listInvite = new ArrayList<>();
        ArrayList<MentorRegister> listMentorRegister = new ArrayList<>();

        if (user != null) {             // da dang nhap roi
            ArrayList<Skill> listSkill = dao.getSkill();
            //la admin
            if (dao.getAdminByUserId(user) != null) {
                listMentorRegister = dao.getNotifyMentorRegister();
                request.setAttribute("isAdmin", true);
            } else {  // khong phai la admin
                // la mentor hoac mentee
                listReq = uti.getSizeOfRequest(user);
                listRes = uti.getSizeOfResponse(user);

                //chi la mentor
                if (dao.getMentorByUserId(user) != null) {
                    listInvite = uti.getSizeOfInvitation(user);
                    request.setAttribute("isMentor", true);
                }
            }
            request.setAttribute("isValidUser", uti.isValidUser(user));    


            request.setAttribute("userId", user.getUserId());
            request.setAttribute("as", listSkill);
            request.setAttribute("listInviteSize", listInvite.size());
            request.setAttribute("listReqSize", listReq.size());
            request.setAttribute("listResSize", listRes.size());
            request.setAttribute("listMentorRegisterSize", listMentorRegister.size());
            request.getRequestDispatcher("view/mentorRegister.jsp").forward(request, response);
        } else {
            response.sendRedirect("home");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        Utilities uti = new Utilities();

        String bio = request.getParameter("bio");
        String[] skill = request.getParameterValues("skill");
        String ckbOtherTech = request.getParameter("ckbOtherTech");
        String otherSkills = request.getParameter("otherSkills");
        String exp = request.getParameter("exp");
        String achievement = request.getParameter("achievement");
        int userId = Integer.parseInt(request.getParameter("userId"));
        String existedSkill = "";
        for (String i : skill) {
            existedSkill += (i + " ");
        }
        existedSkill = existedSkill.trim();

        //check userId trong bang mentor register neu existed thi thong bao la da gui request
        if (uti.getMentorRegisterByUserID(userId) == null) {
            if (ckbOtherTech != null) {
                //neu checked vao other thi insert new skill
                dao.insertMentorRegister(achievement, exp, bio, existedSkill, otherSkills, userId, 0);
            } else {
                dao.insertMentorRegister(achievement, exp, bio, existedSkill, otherSkills, userId, 1);
            }

            // add to mentor table with 'inactive'
            dao.insertUserToMentor(userId);

            request.setAttribute("sendRequestSuccess", "Your mentor register's request has been sent successfully!");
        } else {
            request.setAttribute("requestInProgress", "You've sent request before. You can not send more request!");
        }
        doGet(request, response);

    }

}
