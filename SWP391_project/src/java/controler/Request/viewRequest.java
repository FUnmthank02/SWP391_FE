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

    int pagesize = 1;

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
        if (!uti.isValidUser(a)) {
            response.sendRedirect("home");
        } else {
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

            ArrayList<Request> newlist1 = new ArrayList<>();
            ArrayList<String> newlist2 = new ArrayList<>();
            int index = 1;
            if (request.getParameter("index") != null) {
                index = Integer.parseInt(request.getParameter("index"));
            }
            try {
                pagesize = Integer.parseInt(request.getParameter("pagesize"));
            } catch (Exception e) {
            }
            int count = reqList.size();
            int startpage = index - 2;
            if (startpage <= 0) {
                startpage = 1;
            }
            int nummberpage = count / pagesize;
            if (count % pagesize != 0) {
                nummberpage++;
            }
            int endpage = index + 2;
            if (endpage > nummberpage) {
                endpage = nummberpage;
            }
            for (int i = pagesize * (index - 1); i <= pagesize * index - 1; i++) {
                if (i == count) {
                    break;
                }
                newlist1.add(reqList.get(i));
                newlist2.add(dateList.get(i));
            }

            request.setAttribute("pagesize", pagesize);
            request.setAttribute("index", index);
            request.setAttribute("startpage", startpage);
            request.setAttribute("endpage", endpage);
            request.setAttribute("nummberpage", nummberpage);
            request.setAttribute("dateList", newlist2);
            request.setAttribute("reqList", newlist1);

            request.setAttribute("isValidUser", uti.isValidUser(a));
            request.setAttribute("listInviteSize", listInvite.size());
            request.setAttribute("listReqSize", listReq.size());
            request.setAttribute("listResSize", listRes.size());
            request.setAttribute("listMentorRegisterSize", listMentorRegister.size());

            request.setAttribute("as", skills);
            request.getRequestDispatcher("view/viewRequest.jsp").forward(request, response);
        }
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
