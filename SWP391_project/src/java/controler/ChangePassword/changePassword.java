/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.ChangePassword;

import dal.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author DELL
 */
public class changePassword extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/ChangePassword/changepassword.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oldPass = request.getParameter("oldPass");
        String newPass = request.getParameter("newPass");
        String cfNewPass = request.getParameter("cfNewPass");
        User u = (User)request.getSession(true).getAttribute("user");
        boolean isCorrect = false;
        DAO dao = new DAO();
        if(u.getPassword().equals(oldPass)){
            if(newPass.equals(cfNewPass)){
            dao.changePassword(u.getUserId(), newPass);
            request.getSession().removeAttribute("user");
            isCorrect = true;
            }
            else{
                request.setAttribute("errRpPassNotMatch", "Confirm password does not match!!!");
            }
        }
        else{
            request.setAttribute("errNewPassNotValid", "Wrong password!!!");    
        }
        if(isCorrect){
            response.sendRedirect("login");
        }
        else{
            request.getRequestDispatcher("view/ChangePassword/changepassword.jsp").forward(request, response);
        }
    }

}
