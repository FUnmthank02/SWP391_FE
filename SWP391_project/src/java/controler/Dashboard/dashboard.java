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

/**
 *
 * @author Admin
 */
public class dashboard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DAO dao = new DAO();
        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("user");

        if (u != null) {
            //check co phai admin hay ko
            if (dao.getAdminByUserId(u) != null) {
                
                ArrayList<Mentee> listMentee = dao.getListMenteeDashboard();
                ArrayList<Mentor> listMentor = dao.getListMentorDashboard();
                
                request.setAttribute("listMentee", listMentee);
                request.setAttribute("listMentor", listMentor);
                request.setAttribute("isAdmin", true);
                request.getRequestDispatcher("view/dashboard.jsp").forward(request, response);

            }
            else response.sendRedirect("home");
        } else response.sendRedirect("home");
        

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
