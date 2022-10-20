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
import java.util.ArrayList;
import java.util.HashMap;
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
        String tech = request.getParameter("technology");
        String rating = request.getParameter("rating");

        DAO dao = new DAO();
        Utilities uti = new Utilities();
        
        HashMap<Integer,Float> rateMap = dao.getRateByMentorID();

        request.setAttribute("rateMap", rateMap);
        request.setAttribute("listMentor", dao.getMentorWithTech(tech));
        request.setAttribute("listUser", uti.getListUser());
        request.setAttribute("listSkill", dao.getSkill());
        request.setAttribute("listEnrollSkill", dao.getEnrollSkills());
        request.setAttribute("tech", tech);
        
        request.getRequestDispatcher("view/viewmentor.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
