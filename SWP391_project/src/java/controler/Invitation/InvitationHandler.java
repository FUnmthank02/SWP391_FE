
package controler.Invitation;

import dal.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class InvitationHandler extends HttpServlet {

   

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("home");
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO d = new DAO();
        
        int menteeID = Integer.parseInt(request.getParameter("menteeID"));
        int mentorID = Integer.parseInt(request.getParameter("mentorID"));

        int choice = Integer.parseInt(request.getParameter("button"));
        if (choice == 1) {
            d.insertInvitation(mentorID, menteeID);
        } else {
            d.breakRelationship(mentorID, menteeID);
        }
        String url ="mentorprofile?mentorID=" + mentorID;
        response.sendRedirect(url);
    }

  
}
