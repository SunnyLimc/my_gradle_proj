<%--
  Created by IntelliJ IDEA.
  User: limc
  Date: 11/13/22
  Time: 12:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="me.limc.demo.servlet.Student" %>
<%
    Student[] stuArr = (Student[]) request.getAttribute("stuArr");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style>
        td {
            padding: 3px;
            color: blue;
        }
    </style>
    <script>
        function test(td) {
            alert(td.innerHTML);
        }
    </script>
</head>

<body>
<h3>志愿者列表</h3>
<table border="1" cellspacing="0">
    <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>成绩</th>
    </tr>
    <%for (Student stu : stuArr) {%>
    <tr>
        <td onclick="test(this)"><%=stu.getStuNo()%>
        </td>
        <td><%=stu.getStuName()%>
        </td>
        <td><%=stu.getStuMark()%>
        </td>
    </tr>
    <%} %>
</table>

</body>

</html>