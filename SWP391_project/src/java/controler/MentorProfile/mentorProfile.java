/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.MentorProfile;

import dal.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.*;
import model.*;
import utility.Utilities;

/**
 *
 * @author Admin
 */
public class mentorProfile extends HttpServlet {

    DAO d = new DAO();
    Utilities u = new Utilities();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        Utilities uti = new Utilities();
        User U = (User) request.getSession().getAttribute("user");

        ArrayList<Request> listReq = new ArrayList<>();
        ArrayList<Response> listRes = new ArrayList<>();
        ArrayList<Invitation> listInvite = new ArrayList<>();
        ArrayList<MentorRegister> listMentorRegister = new ArrayList<>();
        // da dang nhap roi
        if (U != null) {
            request.setAttribute("isValidUser", uti.isValidUser(U));    
                        
            //la admin
            if (dao.getAdminByUserId(U) != null) {
                listMentorRegister = dao.getNotifyMentorRegister();
                request.setAttribute("isAdmin", true);
            } else {  // khong phai la admin
                // la mentor hoac mentee
                listReq = uti.getSizeOfRequest(U);
                listRes = uti.getSizeOfResponse(U);

                //chi la mentor
                if (dao.getMentorByUserId(U) != null) {
                    listInvite = uti.getSizeOfInvitation(U);
                    request.setAttribute("isMentor", true);
                }
            }
        }

        //get mentorID in URL
        String mentorID = request.getParameter("mentorID");

        //create mentor
        Mentor m = new Mentor();
        m.setMentorID(Integer.parseInt(mentorID));

        Mentor currentMentor = dao.getMentorByUserId(U);
        
        //get list skill of mentor
        ArrayList<Skill> skills = d.getSkills(m);

        //get profile of mentor
        Profile profile = d.getProfile(m);

        //get user info of mentor
        User user = d.getUser(m);

        //get comment of mentor
        ArrayList<Comment> comments = d.getComments(m);

        //get rates of mentor
        ArrayList<Rating> rates = d.getRates(m);

        //get mentee of current user
        Mentee mentee = d.getMentee(U);

        //get invitation of mentee and mentor
        Invitation invitation = d.getInvitation(m, mentee);

        //get all skills
        ArrayList<Skill> allSkills = d.getSkill();

        //get formatted date of comments belong a mentor
        HashMap<Integer, String> formattedDates = d.formattedDate(m);
 //check if mentee comment mentor
        boolean checkcomment = d.checkComment(mentee, m);
        request.setAttribute("currentMentor", currentMentor);
        request.setAttribute("checkcmt", checkcomment);
        request.setAttribute("m", m);
        request.setAttribute("mt", mentee);
        request.setAttribute("fd", formattedDates);
        request.setAttribute("as", allSkills);
        request.setAttribute("i", invitation);
        request.setAttribute("c", comments);
        request.setAttribute("r", rates);
        request.setAttribute("u", user);
        request.setAttribute("s", skills);
        request.setAttribute("p", profile);
        request.setAttribute("listInviteSize", listInvite.size());
        request.setAttribute("listReqSize", listReq.size());
        request.setAttribute("listResSize", listRes.size());
        request.setAttribute("listMentorRegisterSize", listMentorRegister.size());
        request.getRequestDispatcher("view/mentorprofile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int rate = Integer.parseInt(request.getParameter("rate"));
        String comment = request.getParameter("comment");

        //get current user
        User U = (User) request.getSession().getAttribute("user");

        //get mentorID in URL
        String mentorID = request.getParameter("hiddenMentorID");
        Mentor m = new Mentor();
        m.setMentorID(Integer.parseInt(mentorID));

        //get mentee of current user
        Mentee mentee = d.getMentee(U);

        //create new comment
        Comment c = new Comment();
        c.setCmtContent(comment);
        c.setMentee(mentee);
        c.setMentor(m);
        java.util.Date utilDate = new java.util.Date();
        c.setTime(new Date(utilDate.getTime()));

        //insert comment to db
        d.insertComment(c);

        //create new rate
        Rating r = new Rating();
        r.setMentee(mentee);
        r.setMentor(m);
        r.setRateStar(rate);
        d.insertRate(r);

        //URL reload
        String url = "mentorprofile?mentorID=" + mentorID;
        response.sendRedirect(url);

    }

}
