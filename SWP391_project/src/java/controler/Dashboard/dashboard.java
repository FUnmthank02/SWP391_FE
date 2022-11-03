/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.Dashboard;

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
public class dashboard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utilities uti = new Utilities();
        DAO dao = new DAO();
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("user");

        if (u != null) {
            request.setAttribute("isValidUser", uti.isValidUser(u));    

            //check co phai admin hay ko
            if (dao.getAdminByUserId(u) != null) {

                ArrayList<Mentee> listMentee = dao.getListMenteeDashboard();
                ArrayList<Mentor> listMentor = dao.getListMentorDashboard();
                ArrayList<Skill> skills = dao.getSkill();
                
                // all request statistic
                HashMap<java.sql.Date, Float> averageRequest = dao.getAvrReqPerUserPerDay();
                HashMap<String, Integer> countRequest = dao.countReqPerMonth();
                float[] percentage = dao.getPercentage();
                
                request.setAttribute("dataInvite", dao.statisticInvitation());
                request.setAttribute("dateInvite", dao.formatDate());
                request.setAttribute("totalMentee", dao.statisticMentee());
                request.setAttribute("totalMentor", dao.statisticMentor());
                request.setAttribute("totalInvite", dao.statisticInvite());
                

                request.setAttribute("averageRequest", averageRequest);
                request.setAttribute("countRequest", countRequest);
                request.setAttribute("percentage", percentage);

                int sizeMentee = listMentee.size();
                int sizeMentor = listMentor.size();
                int cp = 0;
                int nrpp = 1; // update

                if (ses.getAttribute("nrpp") != null) {
                    nrpp = (int) ses.getAttribute("nrpp");
                }

                PageInfor pageMentee = new PageInfor(cp, nrpp, sizeMentee);
                pageMentee.calc();
                request.setAttribute("cpMentee", pageMentee);

                PageInfor pageMentor = new PageInfor(cp, nrpp, sizeMentor);
                pageMentor.calc();
                request.setAttribute("cpMentor", pageMentor);

                request.setAttribute("listMentee", listMentee);
                request.setAttribute("listMentor", listMentor);
                request.setAttribute("isAdmin", true);
                request.setAttribute("as", skills);
                request.getRequestDispatcher("view/dashboard.jsp").forward(request, response);

            } else {
                response.sendRedirect("home");
            }
        } else {
            response.sendRedirect("home");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        Utilities uti = new Utilities();
        HttpSession ses = request.getSession();

        User u = (User) ses.getAttribute("user");

        if (u != null) {
            //check co phai admin hay ko
            if (dao.getAdminByUserId(u) != null) {

                ArrayList<Mentee> listMentee = dao.getListMenteeDashboard();
                ArrayList<Mentor> listMentor = dao.getListMentorDashboard();

                int sizeMentee = listMentee.size();
                int sizeMentor = listMentor.size();

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

                PageInfor pageMentee = new PageInfor(cp, nrpp, sizeMentee);
                pageMentee.calc();
                request.setAttribute("cpMentee", pageMentee);

                PageInfor pageMentor = new PageInfor(cp, nrpp, sizeMentor);
                pageMentor.calc();
                request.setAttribute("cpMentor", pageMentor);

                request.setAttribute("listMentee", listMentee);
                request.setAttribute("listMentor", listMentor);
                request.setAttribute("isAdmin", true);
                request.getRequestDispatcher("view/dashboard.jsp").forward(request, response);

            } else {
                response.sendRedirect("home");
            }
        } else {
            response.sendRedirect("home");
        }

    }

}
