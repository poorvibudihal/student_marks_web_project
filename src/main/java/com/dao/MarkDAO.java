package com.dao;

import java.sql.*;
import java.util.*;
import com.model.StudentMark;

public class MarkDAO {

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Employee2","root","root");
    }

    // ADD
    public void addMark(StudentMark m) throws Exception {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(
        "INSERT INTO StudentMarks VALUES(?,?,?,?,?)");

        ps.setInt(1, m.getStudentId());
        ps.setString(2, m.getStudentName());
        ps.setString(3, m.getSubject());
        ps.setInt(4, m.getMarks());
        ps.setDate(5, m.getExamDate());

        ps.executeUpdate();
        con.close();
    }

    // GET ALL
    public List<StudentMark> getAllMarks() throws Exception {
        List<StudentMark> list = new ArrayList<>();
        Connection con = getConnection();

        ResultSet rs = con.createStatement()
                .executeQuery("SELECT * FROM StudentMarks");

        while(rs.next()){
            StudentMark m = new StudentMark();
            m.setStudentId(rs.getInt(1));
            m.setStudentName(rs.getString(2));
            m.setSubject(rs.getString(3));
            m.setMarks(rs.getInt(4));
            m.setExamDate(rs.getDate(5));
            list.add(m);
        }
        con.close();
        return list;
    }

    // DELETE
    public void deleteMark(int id) throws Exception {

        Connection con = getConnection();

        PreparedStatement ps = con.prepareStatement(
            "DELETE FROM StudentMarks WHERE StudentID=?"
        );

        ps.setInt(1, id);

        int rows = ps.executeUpdate();

        if(rows == 0){
            System.out.println("No record found with ID: " + id);
        } else {
            System.out.println("Record deleted successfully");
        }

        con.close();
    }

    // UPDATE
    public void updateMark(StudentMark m) throws Exception {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(
        "UPDATE StudentMarks SET StudentName=?,Subject=?,Marks=?,ExamDate=? WHERE StudentID=?");

        ps.setString(1, m.getStudentName());
        ps.setString(2, m.getSubject());
        ps.setInt(3, m.getMarks());
        ps.setDate(4, m.getExamDate());
        ps.setInt(5, m.getStudentId());

        ps.executeUpdate();
        con.close();
    }
}