/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.Login;

import dal.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;
import utility.*;

/**
 *
 * @author Admin
 */
public class login extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       request.getRequestDispatcher("view/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        Utilities uti = new Utilities();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // goi ham tim user
        User user = uti.getExistedUser(username, password);
        
        // tim thay user
        if(user!=null) {
            HttpSession ses = request.getSession();
            ses.setAttribute("user", user);    // set session as a User object
            ses.setMaxInactiveInterval(60*10); //don vi: second
            
            if(!uti.isValidUser(user)) {
                response.sendRedirect("select-role");
            } else
                response.sendRedirect("home");
            
        }
        else {      // khong tim dc user
            request.setAttribute("errWrongAcc", "Username or password is in correct!");
            request.getRequestDispatcher("view/login.jsp").forward(request, response);
        }
    }

}
