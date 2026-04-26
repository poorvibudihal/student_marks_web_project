package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.MarkDAO;
import com.model.StudentMark;

@WebServlet("/displayMarks")
public class DisplayMarksServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            MarkDAO dao = new MarkDAO();
            List<StudentMark> list = dao.getAllMarks();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<link rel='stylesheet' href='style.css'>");
            out.println("<h2>All Student Marks</h2>");

            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Subject</th><th>Marks</th><th>Date</th></tr>");

            for (StudentMark m : list) {
                out.println("<tr>");
                out.println("<td>" + m.getStudentId() + "</td>");
                out.println("<td>" + m.getStudentName() + "</td>");
                out.println("<td>" + m.getSubject() + "</td>");
                out.println("<td>" + m.getMarks() + "</td>");
                out.println("<td>" + m.getExamDate() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}