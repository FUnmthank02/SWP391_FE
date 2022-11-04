/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.Request;

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
public class CreateRequest extends HttpServlet {

    DAO d = new DAO();
    utility.Utilities u = new Utilities();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get user 
        User U = (User) request.getSession().getAttribute("user");

        if (U != null && u.isValidUser(U)) {
            ArrayList<Request> listReq = new ArrayList<>();
            ArrayList<Response> listRes = new ArrayList<>();
            ArrayList<Invitation> listInvite = new ArrayList<>();
            ArrayList<MentorRegister> listMentorRegister = new ArrayList<>();
            //la admin
            if (d.getAdminByUserId(U) != null) {
                listMentorRegister = d.getNotifyMentorRegister();
                request.setAttribute("isAdmin", true);
            } else {  // khong phai la admin
                // la mentor hoac mentee
                listReq = u.getSizeOfRequest(U);
                listRes = u.getSizeOfResponse(U);

                //chi la mentor
                if (d.getMentorByUserId(U) != null) {
                    listInvite = u.getSizeOfInvitation(U);
                }
            }
            request.setAttribute("isValidUser", u.isValidUser(U));    

            //get all skill for header
            ArrayList<Skill> skills = d.getSkill();
            //get mentee of current user
            Mentee mentee = d.getMentee(U);
            //get information of mentors belong to a mentee
            ArrayList<User> mi = d.getUser(mentee);

            request.setAttribute("listInviteSize", listInvite.size());
            request.setAttribute("listReqSize", listReq.size());
            request.setAttribute("listResSize", listRes.size());
            request.setAttribute("listMentorRegisterSize", listMentorRegister.size());
            request.setAttribute("as", skills);
            request.setAttribute("mi", mi);
            request.getRequestDispatcher("view/createRequest.jsp").forward(request, response);
        } else 
            response.sendRedirect("home");
    }

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

}
