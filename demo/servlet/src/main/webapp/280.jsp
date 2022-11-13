<%--
  Created by IntelliJ IDEA.
  User: limc
  Date: 11/11/22
  Time: 11:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PASSWD</title>
</head>
<body>
<form action="280.jsp" method="post">
    please input:<br>
    <input type="text" name="username" size="5">
    <input type="text" name="userpwd" size="5">
    <input type="submit" value="submit">
</form>
username=<%=request.getParameter("username")%><br>
userpwd=<%=request.getParameter("userpwd")%><br>
</body>
</html>
