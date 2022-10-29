package controler.UserProfile;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import utility.Utilities;

/**
 *
 * @author Admin
 */
public class userprofile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utilities uti = new Utilities();
        HttpSession ses = request.getSession();
        DAO dao = new DAO();

        User user = (User) ses.getAttribute("user");

        if (user != null) {
            if (uti.getUser(user.getUserId()) != null) { //tim duoc doi tuong va load thong tin
                
            //check co phai admin hay ko
            if (dao.getAdminByUserId(user) != null) {
                request.setAttribute("isAdmin", true);
            }
                request.setAttribute("userinfor", uti.getUser(user.getUserId()));
                request.getRequestDispatcher("view/userprofile.jsp").forward(request, response);
            } else {
                response.sendRedirect("home"); // ko tim duoc doi tuong chuyen ve trang home
            }
        }
        else response.sendRedirect("home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
