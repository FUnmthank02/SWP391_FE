/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.Comment;

import dal.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

/**
 *
 * @author Admin
 */
public class editComment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO d = new DAO();
        int menteeID = Integer.parseInt(request.getParameter("menteeID"));
        Mentee mt = new Mentee();
        mt.setMenteeID(menteeID);
        String mentorID = request.getParameter("mentorID");
        Mentor m = new Mentor();
        m.setMentorID(Integer.parseInt(mentorID));
        String content = request.getParameter("replyContent");
        int rate = Integer.parseInt(request.getParameter("rateUpdate"));
        Comment c = new Comment();
        c.setMentee(mt);
        c.setCmtContent(content);
        Rating r = new Rating();
        r.setMentee(mt);
        r.setRateStar(rate);
        d.updateRating(r);
        d.updateComment(c);

        //URL reload
        String url = "mentorprofile?mentorID=" + mentorID;
        response.sendRedirect(url);
    }

   
}
