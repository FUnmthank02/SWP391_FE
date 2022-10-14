/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.ChangePassword;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import other.Email;
import other.SendEmail;

/**
 *
 * @author DELL
 */
@WebServlet(name = "ForgotPassword", urlPatterns = {"/forgot-password"})
public class ForgotPassword extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet resetPassword</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet resetPassword at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/ChangePassword/forgotpassword.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        DAO dao = new DAO();
        dao.loadListUser();
        User u = dao.getUserByEmail(email);
        if(u!=null){
            request.setAttribute("mailSended", "Email has been sent");          
            //send email here
            try {
                String url =request.getScheme()+ "://" + 
                    request.getServerName()+":"+
                    request.getServerPort()+
                    request.getContextPath()+
                   "/reset-password?email=" + email;
                
                SendEmail se = new SendEmail();
              
                Email e = new Email();
                e.setFrom("anhndqhe161737@fpt.edu.vn");        //from mail's infor
                e.setFromPassword("");     //from mail's infor
                e.setTo(email);                                     //receive mail's infor
                e.setSubject("Reset password");                 //mail's subject
                StringBuilder sb = new StringBuilder();         
                sb.append("Dear").append(email).append(", Click this link to <b>").append("<a href='"+url+"'>RESET PASSWORD</a>").append("</b>");
                e.setContent(sb.toString());                    //mail's content
                se.sendEmail(e);                                // sending mail
                
                request.setAttribute("mailSended", "Link to active account sended to your email");

                // insert account to db with status: inactive
            } catch (Exception exc) {
                request.setAttribute("errorWhileSendMail", exc.getMessage());
            }
        }
        else{
            request.setAttribute("errNotExistUser", "This user does not existed");
            request.getRequestDispatcher("view/ChangePassword/forgotpassword.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
