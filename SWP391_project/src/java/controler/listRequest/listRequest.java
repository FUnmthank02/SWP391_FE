/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.listRequest;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Request;
import model.User;

/**
 *
 * @author Admin
 */
public class listRequest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession(true);
        if(ses.getAttribute("user")==null) {
            response.sendRedirect("login");
            return;
        }
        User a = (User) ses.getAttribute("user");
        ArrayList<Request> reqList = new ArrayList<>();
        ArrayList<String> dateList = new ArrayList<>();
        DAO dao = new DAO();
        System.out.println(dao.getMentee(a));
        if (dao.getMentee(a) != null) {
            reqList = dao.loadRequest(dao.getMentee(a).getMenteeID(), "menteeID");
            dateList = dao.formatDate(dao.getMentee(a).getMenteeID(), "Request", "menteeID");
        } else {
            reqList = dao.loadRequest(dao.getMentor(a).getMentorID(), "mentorID");
            dateList = dao.formatDate(dao.getMentor(a).getMentorID(), "Request", "mentorID");
        }
        request.setAttribute("dateList", dateList);
        request.setAttribute("reqList", reqList);
        request.getRequestDispatcher("view/viewRequest.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        String action = request.getParameter("action");
        if (action.equals("reply")) {
            int id = Integer.parseInt(request.getParameter("reqId"));
            String replyReq = request.getParameter("replyContent");
            System.out.println(id);
            System.out.println(replyReq);
            dao.insertResponse(id, replyReq);
        } else {
            int id = Integer.parseInt(request.getParameter("reqId"));
            String updateRes = request.getParameter("updateResponse");
            dao.updateResponse(id, updateRes);
        }
        doGet(request, response);
    }

}
