package controler.Invitation;

import dal.DAO;
import java.io.IOException;
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
public class invitation extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        Utilities uti = new Utilities();
        HttpSession ses = request.getSession();
        User u = (User) request.getSession().getAttribute("user");
        ArrayList<Request> listReq = new ArrayList<>();
        ArrayList<Response> listRes = new ArrayList<>();
        ArrayList<Invitation> listInvite = new ArrayList<>();
        ArrayList<MentorRegister> listMentorRegister = new ArrayList<>();

        // da dang nhap roi
        if (u != null) {
            //la admin
            if (dao.getAdminByUserId(u) != null) {
                listMentorRegister = dao.getNotifyMentorRegister();
                request.setAttribute("isAdmin", true);
            } else {  // khong phai la admin
                // la mentor hoac mentee

                //chi la mentor
                if (dao.getMentorByUserId(u) != null) {
                    Mentor m = dao.getMentorByUserId(u);
                    dao.UpdateListInvitationSeen(m);
                    ArrayList<Invitation> listInv = dao.getInvitationOfMentor(m);
                    listInvite = uti.getSizeOfInvitation(u);
                    request.setAttribute("invitations", listInv);
                    request.setAttribute("isMentor", true);
                }

                listReq = uti.getSizeOfRequest(u);
                listRes = uti.getSizeOfResponse(u);
            }
            request.setAttribute("listInviteSize", listInvite.size());
            request.setAttribute("listReqSize", listReq.size());
            request.setAttribute("listResSize", listRes.size());
            request.setAttribute("listMentorRegisterSize", listMentorRegister.size());
            request.getRequestDispatcher("view/invitation.jsp").forward(request, response);
        }
        response.sendRedirect("home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        int id = Integer.parseInt(request.getParameter("invitationId"));

        if (request.getParameter("accept") != null) {
            dao.UpdateStatus(id);
        }
        if (request.getParameter("reject") != null) {
            dao.deleteInvitation(id);
        }
        doGet(request, response);
    }

}
