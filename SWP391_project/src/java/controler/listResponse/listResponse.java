/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.listResponse;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Response;
import model.User;

/**
 *
 * @author Admin
 */
public class listResponse extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession(true);
        if(ses.getAttribute("user")==null) {
            response.sendRedirect("login");
            return;
        }
        User a = (User) ses.getAttribute("user");
        ArrayList<Response> resList = new ArrayList<>();
        ArrayList<String> dateList = new ArrayList<>();
        DAO dao = new DAO();
        if (dao.getMentee(a) != null) {
            resList = dao.loadResponse(dao.getMentee(a).getMenteeID(), "menteeID");
            dateList = dao.formatDate(dao.getMentee(a).getMenteeID(), "Response", "menteeID");
        } else {
            resList = dao.loadResponse(dao.getMentor(a).getMentorID(), "mentorID");
            dateList = dao.formatDate(dao.getMentor(a).getMentorID(), "Response", "mentorID");
        }
        request.setAttribute("dateList", dateList);
        request.setAttribute("resList", resList);
        request.getRequestDispatcher("view/viewResponse.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        String action = request.getParameter("action");
        if (action.equals("reply")) {
            int id = Integer.parseInt(request.getParameter("reqId"));
            String replyReq = request.getParameter("replyContent");
            dao.insertRequest(id, replyReq);
        } else {
            int id = Integer.parseInt(request.getParameter("resId"));
            String updateRes = request.getParameter("updateResponse");
            dao.updateResponse(id, updateRes);
        }
        doGet(request, response);
    }
}
