/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.ChangePassword;

import dal.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author DELL
 */
@WebServlet(name = "ResetPassword", urlPatterns = {"/reset-password"})
public class ResetPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        request.setAttribute("email", email);
        request.getRequestDispatcher("view/ChangePassword/resetpassword.jsp").forward(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newPass = request.getParameter("newPass");
        String rpNewPass = request.getParameter("rpNewPass");
        String email = request.getParameter("email");
        DAO dao = new DAO();
        User u = dao.getUserByEmail(email);
        if(rpNewPass.equals(newPass)){
            dao.changePassword(u.getUserId(), newPass);
            request.setAttribute("resetSuccess", "Reset password successful");
        }
        else{
            request.setAttribute("errRpPassNotMatch", "Password does not match");
            request.getRequestDispatcher("view/ChangePassword/resetpassword.jsp").forward(request, response);
        }
    }

}
