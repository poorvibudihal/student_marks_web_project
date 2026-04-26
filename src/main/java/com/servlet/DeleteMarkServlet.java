package com.servlet;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.MarkDAO;

@WebServlet("/deleteMark")
public class DeleteMarkServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");

        try {
            String idStr = request.getParameter("id");

            int id;

            // Validate input
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                response.getWriter().println("<h3 style='color:red'>Wrong! Please enter number</h3>");
                return;
            }

            MarkDAO dao = new MarkDAO();
            dao.deleteMark(id);

            response.getWriter().println("<h3 style='color:green'>Record Deleted Successfully</h3>");
            response.getWriter().println("<br><a href='index.jsp'>Back to Home</a>");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3 style='color:red'>" + e.getMessage() + "</h3>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.getWriter().println("Use POST method");
    }
}