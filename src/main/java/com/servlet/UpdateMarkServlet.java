package com.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.MarkDAO;
import com.model.StudentMark;

@WebServlet("/updateMark")
public class UpdateMarkServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            StudentMark m = new StudentMark();

            m.setStudentId(Integer.parseInt(request.getParameter("id")));
            m.setStudentName(request.getParameter("name"));
            m.setSubject(request.getParameter("subject"));
            m.setMarks(Integer.parseInt(request.getParameter("marks")));
            m.setExamDate(Date.valueOf(request.getParameter("date")));

            MarkDAO dao = new MarkDAO();
            dao.updateMark(m);

            response.sendRedirect("index.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error in Updating");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.getWriter().println("Use POST method");
    }
}