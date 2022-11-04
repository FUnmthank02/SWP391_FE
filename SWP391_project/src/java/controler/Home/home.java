/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.Home;

import dal.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.*;
import model.*;
import other.PageInfor;
import utility.Utilities;

/**
 *
 * @author Admin
 */
public class home extends HttpServlet {

    ArrayList<Request> listReq = new ArrayList<>();
    ArrayList<Response> listRes = new ArrayList<>();
    ArrayList<Invitation> listInvite = new ArrayList<>();
    ArrayList<MentorRegister> listMentorRegister = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        Utilities uti = new Utilities();
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("user");
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

        int size = uti.getAllActiveMentor().size();
        int cp = 0;
        int nrpp = 1; // update

        if (ses.getAttribute("nrpp") != null) {
            nrpp = (int) ses.getAttribute("nrpp");
        }

        PageInfor page = new PageInfor(cp, nrpp, size);
        page.calc();
        request.setAttribute("cp", page);

        HashMap<Integer, Float> rateMap = dao.getRateByMentorID();

        request.setAttribute("rateMap", rateMap);
        request.setAttribute("listUser", uti.getListUser());
        request.setAttribute("listEnrollSkill", dao.getEnrollSkills());
        request.setAttribute("listMentor", uti.getAllActiveMentor());
        request.setAttribute("listInviteSize", listInvite.size());
        request.setAttribute("listReqSize", listReq.size());
        request.setAttribute("listResSize", listRes.size());
        request.setAttribute("listMentorRegisterSize", listMentorRegister.size());
        request.setAttribute("as", skills);
        request.getRequestDispatcher("view/home.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        Utilities uti = new Utilities();
        HttpSession ses = request.getSession();
        
        User u = (User) ses.getAttribute("user");
        //load all skills 
        ArrayList<Skill> skills = dao.getSkill();

        // da dang nhap roi
        if (u != null) {
            //la admin
            if (dao.getAdminByUserId(u) != null) {
                listMentorRegister = dao.getNotifyMentorRegister();
            } else {  // khong phai la admin
                // la mentor hoac mentee
                listReq = uti.getSizeOfRequest(u);
                listRes = uti.getSizeOfResponse(u);

                //chi la mentor
                if (dao.getMentorByUserId(u) != null) {
                    listInvite = uti.getSizeOfInvitation(u);
                }
            }

        }

        int np = Integer.parseInt(request.getParameter("np"));
        int cp = Integer.parseInt(request.getParameter("cp"));
        int nrpp = 3; //update later
        nrpp = Integer.parseInt(request.getParameter("nrpp"));
        ses.setAttribute("nrpp", nrpp);

        if (request.getParameter("home") != null) {//click home btn
            cp = 0;
        }
        if (request.getParameter("pre") != null) {//click pre btn
            cp = cp - 1;
        }
        if (request.getParameter("next") != null) {//click next btn
            cp = cp + 1;
        }
        if (request.getParameter("end") != null) {//click end btn
            cp = np - 1;
        }
        for (int i = 0; i < np; i++) {
            if (request.getParameter("btn" + i) != null) {//click i btn
                cp = i;
            }
        }

        PageInfor page = new PageInfor(cp, nrpp, uti.getAllActiveMentor().size());
        page.calc();
        request.setAttribute("listMentor", uti.getAllActiveMentor()); //set attribute to send listmentor data to home.jsp
        request.setAttribute("cp", page);

        HashMap<Integer, Float> rateMap = dao.getRateByMentorID();

        request.setAttribute("rateMap", rateMap);
        request.setAttribute("listUser", uti.getListUser());
        request.setAttribute("listEnrollSkill", dao.getEnrollSkills());
        request.setAttribute("listInviteSize", listInvite.size());
        request.setAttribute("listReqSize", listReq.size());
        request.setAttribute("listResSize", listRes.size());
        request.setAttribute("listMentorRegisterSize", listMentorRegister.size());
        request.setAttribute("as", skills);
        request.getRequestDispatcher("view/home.jsp").forward(request, response);

    }
}
