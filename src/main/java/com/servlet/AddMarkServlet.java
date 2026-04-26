package com.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.MarkDAO;
import com.model.StudentMark;

@WebServlet("/addMark")
public class AddMarkServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            StudentMark m = new StudentMark();

            m.setStudentId(Integer.parseInt(request.getParameter("id")));
            m.setStudentName(request.getParameter("name"));
            m.setSubject(request.getParameter("subject"));
            m.setMarks(Integer.parseInt(request.getParameter("marks")));
            m.setExamDate(Date.valueOf(request.getParameter("date")));

            MarkDAO dao = new MarkDAO();
            dao.addMark(m);

            response.sendRedirect("index.jsp");

        }catch(Exception e){
            e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().println("<h3 style='color:red'>" + e.getMessage() + "</h3>");
        }
    }

    // Optional (prevents 405 error)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().println("Use POST method to add data");
    }
}