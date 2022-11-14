<%@ page import="me.limc.demo.servlet.Car" %><%--
  Created by IntelliJ IDEA.
  User: limc
  Date: 11/13/22
  Time: 9:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:useBean id="myCar" class="me.limc.demo.servlet.Car" scope="request">
    <jsp:setProperty property="brand" name="myCar" value="奔驰"/>
    <jsp:setProperty property="price" name="myCar" value="430000"/>
</jsp:useBean>

<%=myCar%><br>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>
