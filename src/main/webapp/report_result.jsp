<%@ page import="java.sql.*" %>
<link rel="stylesheet" href="style.css">

<h2>Report Result</h2>

<%
String val = request.getParameter("marks");

if(val != null && !val.isEmpty()){

int value = Integer.parseInt(val);

Class.forName("com.mysql.cj.jdbc.Driver");

Connection con = DriverManager.getConnection(
"jdbc:mysql://localhost:3306/Employee2","root","root");

PreparedStatement ps = con.prepareStatement(
"SELECT * FROM StudentMarks WHERE Marks > ?");

ps.setInt(1, value);

ResultSet rs = ps.executeQuery();
%>

<table>
<tr><th>Name</th><th>Marks</th></tr>

<%
boolean found = false;

while(rs.next()){
    found = true;
%>

<tr>
<td><%= rs.getString("StudentName") %></td>
<td><%= rs.getInt("Marks") %></td>
</tr>

<%
}

if(!found){
%>
<tr><td colspan="2">No Records Found</td></tr>
<%
}

con.close();
}
%>

</table>