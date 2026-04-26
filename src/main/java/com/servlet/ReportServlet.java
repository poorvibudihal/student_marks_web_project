package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int value = Integer.parseInt(request.getParameter("marks"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Employee2", "root", "password");

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM StudentMarks WHERE Marks > ?");

            ps.setInt(1, value);

            ResultSet rs = ps.executeQuery();

            PrintWriter out = response.getWriter();
            out.println("<link rel='stylesheet' href='style.css'>");
            out.println("<h2>Students with Marks > " + value + "</h2>");

            while (rs.next()) {
                out.println(rs.getString("StudentName") + " - " + rs.getInt("Marks") + "<br>");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}