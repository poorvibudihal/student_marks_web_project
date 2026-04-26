<%@ page import="java.util.*,com.model.StudentMark" %>

<table border="1">
<tr>
<th>ID</th><th>Name</th><th>Subject</th><th>Marks</th><th>Action</th>
</tr>

<%
List<StudentMark> list = (List<StudentMark>) request.getAttribute("list");
for(StudentMark m : list){
%>

<tr>
<td><%=m.getStudentId()%></td>
<td><%=m.getStudentName()%></td>
<td><%=m.getSubject()%></td>
<td><%=m.getMarks()%></td>
<td>
<a href="updateMark?id=<%=m.getStudentId()%>">Edit</a>
<a href="deleteMark?id=<%=m.getStudentId()%>">Delete</a>
</td>
</tr>

<% } %>
</table>