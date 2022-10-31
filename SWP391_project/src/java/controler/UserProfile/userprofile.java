package controler.UserProfile;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.*;
import model.*;
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

        ArrayList<Request> listReq = new ArrayList<>();
        ArrayList<Response> listRes = new ArrayList<>();
        ArrayList<Invitation> listInvite = new ArrayList<>();
        ArrayList<MentorRegister> listMentorRegister = new ArrayList<>();

        User user = (User) ses.getAttribute("user");

        if (user != null) {
            if (uti.getUser(user.getUserId()) != null) { //tim duoc doi tuong va load thong tin

                // da dang nhap roi
                //la admin
                if (dao.getAdminByUserId(user) != null) {
                    listMentorRegister = dao.getNotifyMentorRegister();
                    request.setAttribute("isAdmin", true);
                } else {  // khong phai la admin
                    // la mentor hoac mentee
                    listReq = uti.getSizeOfRequest(user);
                    listRes = uti.getSizeOfResponse(user);

                    //chi la mentor
                    if (dao.getMentorByUserId(user) != null) {
                        listInvite = uti.getSizeOfInvitation(user);
                        request.setAttribute("isMentor", true);
                    }
                }

                request.setAttribute("listInviteSize", listInvite.size());
                request.setAttribute("listReqSize", listReq.size());
                request.setAttribute("listResSize", listRes.size());
                request.setAttribute("listMentorRegisterSize", listMentorRegister.size());
                request.setAttribute("userinfor", uti.getUser(user.getUserId()));
                request.getRequestDispatcher("view/userprofile.jsp").forward(request, response);
            } else {
                response.sendRedirect("home"); // ko tim duoc doi tuong chuyen ve trang home
            }
        } else {
            response.sendRedirect("home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
