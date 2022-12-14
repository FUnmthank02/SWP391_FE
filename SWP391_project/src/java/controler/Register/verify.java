
package controler.Register;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import utility.Utilities;

/**
 *
 * @author Admin
 */
public class verify extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String status = request.getParameter("status");
        String username = request.getParameter("username");
        DAO dao = new DAO();
        Utilities uti = new Utilities();
        User u = uti.getUserbyUsername(username);
        //neu status la active thi update status cua account trong db
        if(status.equals("active") && u != null) {
            
            dao.activeUser(username);
            //insert acc to mentee table
            request.setAttribute("activeSuccess", "Active account successfully, now you can login"); //dua thong bao thanh cong
            request.getRequestDispatcher("view/login.jsp").forward(request, response); //forward sang trang login
        }
        else response.sendRedirect("home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

}
