/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.AboutUs;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import model.Invitation;
import model.MentorRegister;
import model.Request;
import model.Response;
import model.Skill;
import model.User;
import other.PageInfor;
import utility.Utilities;

/**
 *
 * @author Admin
 */
public class about extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        Utilities uti = new Utilities();
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("user");

        ArrayList<Request> listReq = new ArrayList<>();
        ArrayList<Response> listRes = new ArrayList<>();
        ArrayList<Invitation> listInvite = new ArrayList<>();
        ArrayList<MentorRegister> listMentorRegister = new ArrayList<>();
        //load all skills 
        ArrayList<Skill> skills = dao.getSkill();

        // da dang nhap roi
        if (u != null) {
            //la admin
            if (dao.getAdminByUserId(u) != null) {
                listMentorRegister = dao.getNotifyMentorRegister();
                request.setAttribute("isAdmin", true);
            } else {  // khong phai la admin
                // la mentor hoac mentee
                listReq = uti.getSizeOfRequest(u);
                listRes = uti.getSizeOfResponse(u);

                //chi la mentor
                if (dao.getMentorByUserId(u) != null) {
                    listInvite = uti.getSizeOfInvitation(u);
                    request.setAttribute("isMentor", true);
                }
            }
            request.setAttribute("isValidUser", uti.isValidUser(u));
        }

        request.setAttribute("listInviteSize", listInvite.size());
        request.setAttribute("listReqSize", listReq.size());
        request.setAttribute("listResSize", listRes.size());
        request.setAttribute("listMentorRegisterSize", listMentorRegister.size());
        request.setAttribute("as", skills);
        request.getRequestDispatcher("view/about.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
