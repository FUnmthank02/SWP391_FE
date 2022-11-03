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
import jakarta.servlet.http.HttpSession;
import java.util.*;
import model.*;
import utility.Utilities;

/**
 *
 * @author Admin
 */
public class viewRequest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        Utilities uti = new Utilities();
        HttpSession ses = request.getSession(true);
        if (ses.getAttribute("user") == null) {
            response.sendRedirect("home");
            return;
        }
        User a = (User) ses.getAttribute("user");
        ArrayList<Request> reqList = new ArrayList<>();
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<Skill> skills = dao.getSkill();

        ArrayList<Request> listReq = new ArrayList<>();
        ArrayList<Response> listRes = new ArrayList<>();
        ArrayList<Invitation> listInvite = new ArrayList<>();
        ArrayList<MentorRegister> listMentorRegister = new ArrayList<>();
        //la admin
        if (dao.getAdminByUserId(a) != null) {
            listMentorRegister = dao.getNotifyMentorRegister();
            request.setAttribute("isAdmin", true);
        } else {  // khong phai la admin
            // la mentor hoac mentee
            //chi la mentor
            if (dao.getMentorByUserId(a) != null) {
                Mentor m = dao.getMentorByUserId(a);
                dao.UpdateListRequestSeen(m.getMentorID(), 0);
                listInvite = uti.getSizeOfInvitation(a);
                request.setAttribute("isMentor", true);

            } else {
                Mentee m = dao.getMenteeByUserId(a);
                dao.UpdateListRequestSeen(m.getMenteeID(), 1);
                listInvite = uti.getSizeOfInvitation(a);

            }

            listReq = uti.getSizeOfRequest(a);
            listRes = uti.getSizeOfResponse(a);
        }

        if (dao.getMentee(a) != null) {
            reqList = dao.loadRequest(dao.getMentee(a).getMenteeID(), "menteeID");
            dateList = dao.formatDate(dao.getMentee(a).getMenteeID(), "Request", "menteeID");
            request.setAttribute("isMentee", true);
        } else {
            reqList = dao.loadRequest(dao.getMentor(a).getMentorID(), "mentorID");
            dateList = dao.formatDate(dao.getMentor(a).getMentorID(), "Request", "mentorID");
            request.setAttribute("isMentor", true);
        }
        
        request.setAttribute("isValidUser", uti.isValidUser(a));    
        request.setAttribute("listInviteSize", listInvite.size());
        request.setAttribute("listReqSize", listReq.size());
        request.setAttribute("listResSize", listRes.size());
        request.setAttribute("listMentorRegisterSize", listMentorRegister.size());
        request.setAttribute("dateList", dateList);
        request.setAttribute("reqList", reqList);
        request.setAttribute("as", skills);
        request.getRequestDispatcher("view/viewRequest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        String action = request.getParameter("action");
        if (action.equals("reply")) {
            int id = Integer.parseInt(request.getParameter("reqId"));
            String replyReq = request.getParameter("replyContent");
            dao.insertResponse(id, replyReq);
        }
        doGet(request, response);
    }
}
