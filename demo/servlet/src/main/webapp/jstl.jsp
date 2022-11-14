<%@ page import="me.limc.demo.servlet.Student" %>
<%@ page import="me.limc.demo.servlet.StudentServiceImpl" %><%--
  291 / 292 / 293
  Created by IntelliJ IDEA.
  User: limc
  Date: 11/13/22
  Time: 1:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="username" value="mary" scope="request"/>
username = ${username} <br>
<c:set var="result" value="${1+2}" scope="request"/>
result = ${result} <br>
<hr>
username = <%= request.getAttribute("username")%> <br>
username = ${username} <br>
username = <c:out value="${username}" default="---"/> <br>
username = <c:out value="${username}">n/a</c:out> <br>
<c:out value="<hr>"/>
<hr>
${requestScope.mark = 60}
</body>
<c:if test="${mark>=60}">及格</c:if>
<c:if test="${mark<60}">不及格</c:if>
<hr>
<c:choose>
    <c:when test="${mark>=0 && mark<60}">不及格</c:when>
    <c:when test="${mark>=60 && mark<80}">及格</c:when>
    <c:when test="${mark>=80 && mark<90}">良好</c:when>
    <c:when test="${mark>=90 && mark<=100}">优秀</c:when>
    <c:otherwise>无效成绩</c:otherwise>
</c:choose>
<%
    Student[] stus = StudentServiceImpl.INSTANCE.defaultRandomStu(20);
    pageContext.setAttribute("stus", stus);
%>
<style>
    .odd {
        color: oldlace;
        background-color: seagreen;
    }

    .even {
        color: saddlebrown;
        background-color: skyblue;
    }

    th, td {
        text-align: center;
    }
</style>
<table border="1" cellspacing="0" width="90%">
    <tr>
        <th>Index</th>
        <th>No</th>
        <th>Name</th>
        <th>Mark</th>
    </tr>
    <c:forEach var="stu" items="${stus}" varStatus="i">
        <tr class="<c:choose><c:when test="${(i.index + 1) %2 == 0}">even</c:when>
                             <c:otherwise>odd</c:otherwise></c:choose>">
            <td>${i.index + 1}</td>
            <td>${stu.stuNo}</td>
            <td>${stu.stuName}</td>
            <td>${stu.stuMark}</td>
        </tr>
    </c:forEach>
</table>
</html>
