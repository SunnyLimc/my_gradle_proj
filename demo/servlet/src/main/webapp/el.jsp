<%@ page import="java.io.PrintWriter" %><%--
` 287 / 288 / 289
  Created by IntelliJ IDEA.
  User: limc
  Date: 11/13/22
  Time: 11:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
1+2=<% out.print(1 + 2); %><br>
1+2=<%= 1 + 2 %>           <br>
1+2=${1+2} <br>
1+2*3=${1+2*3} <br>
(1+2)*3=${(1+2)*3} <br>
5/3=${5/3}
<hr>
5>3? ${5>3} <br>
5<3 or 10>9? ${5>3 or 10>9}<br>
<hr>
<%
    String pwd = request.getParameter("userpwd");
    if (pwd != null && !pwd.isEmpty()) {
        request.setAttribute("userpwd", pwd);
        session.setAttribute("userpwd", pwd);
        application.setAttribute("userpwd", pwd);
    }
%>
username = ${param.username}<br>
request userpwd = ${requestScope.userpwd}<br>
session userpwd = ${sessionScope.userpwd}<br>
application userpwd = ${applicationScope.userpwd}<br>

userpwd = ${userpwd}
<form action="el.jsp" method="post">
    <span>username: </span><input type="text" name="username"><br>
    <span>userpwd: </span><input type="text" name="userpwd">
    <button type="submit" value="submit">submit</button>
</form>

<jsp:useBean id="Stu" class="me.limc.demo.servlet.Student" scope="request">
    <jsp:setProperty name="Stu" property="stuAge" value="20"/>
    <jsp:setProperty name="Stu" property="stuName" value="Mary"/>
    <jsp:setProperty name="Stu" property="stuNo" value="10000"/>
    <jsp:setProperty name="Stu" property="stuMark" value="100"/>
</jsp:useBean>

stuNo = ${Stu.stuNo} <br>
stuName = ${Stu.stuName} <br>
stuAge = ${Stu.stuAge} <br>
stuMark = ${Stu.stuMark} <br>

</body>
</html>
