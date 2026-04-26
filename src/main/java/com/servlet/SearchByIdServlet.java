package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/searchById")
public class SearchByIdServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Employee2", "root", "password");

            // Query
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM StudentMarks WHERE StudentID = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            // CSS
            out.println("<link rel='stylesheet' href='style.css'>");
            out.println("<h2>Search Result</h2>");

            if (rs.next()) {
                out.println("<table>");
                out.println("<tr><th>ID</th><th>Name</th><th>Subject</th><th>Marks</th><th>Date</th></tr>");

                out.println("<tr>");
                out.println("<td>" + rs.getInt("StudentID") + "</td>");
                out.println("<td>" + rs.getString("StudentName") + "</td>");
                out.println("<td>" + rs.getString("Subject") + "</td>");
                out.println("<td>" + rs.getInt("Marks") + "</td>");
                out.println("<td>" + rs.getDate("ExamDate") + "</td>");
                out.println("</tr>");

                out.println("</table>");
            } else {
                out.println("<h3>No record found for ID: " + id + "</h3>");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error occurred while searching.");
        }
    }

    // Prevent 405 error
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.getWriter().println("Use POST method to search data");
    }
}