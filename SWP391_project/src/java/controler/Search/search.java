/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.Search;

import dal.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.*;
import model.*;
import utility.*;

/**
 *
 * @author Admin
 */
public class search extends HttpServlet {

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

        }

        if (request.getParameter("technologyID") != null) {
            int tech = Integer.parseInt(request.getParameter("technologyID"));
            request.setAttribute("listMentorTech", dao.getMentorWithTech(tech));
            request.setAttribute("tech", tech);
        }
        if (request.getParameter("preRating") != null && request.getParameter("aftRating") != null) {
            float preRating = Float.parseFloat(request.getParameter("preRating"));
            float aftRating = Float.parseFloat(request.getParameter("aftRating"));
            request.setAttribute("preRating", preRating);
            request.setAttribute("aftRating", aftRating);
        }

        HashMap<Integer, Float> rateMap = dao.getRateByMentorID();

        request.setAttribute("listInviteSize", listInvite.size());
        request.setAttribute("listReqSize", listReq.size());
        request.setAttribute("listResSize", listRes.size());
        request.setAttribute("listMentorRegisterSize", listMentorRegister.size());
        request.setAttribute("rateMap", rateMap);
        request.setAttribute("listMentor", dao.getAllMentor());
        request.setAttribute("listUser", uti.getListUser());
        request.setAttribute("as", dao.getSkill());
        request.setAttribute("listEnrollSkill", dao.getEnrollSkills());
//        

        if (request.getParameter("technologyID") == null && request.getParameter("preRating") == null && request.getParameter("aftRating") == null) {
            request.getRequestDispatcher("view/search.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("view/viewmentor.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
