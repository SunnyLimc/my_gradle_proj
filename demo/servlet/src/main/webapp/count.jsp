<%--
  Created by IntelliJ IDEA.
  User: limc
  Date: 11/11/22
  Time: 11:05 PM
  To change this template use File | Settings | File Templates.
  279
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HEHE</title>
</head>
<body>
<%
    String s = request.getParameter("start");
    String e = request.getParameter("end");
    try {
        start = Integer.parseInt(s);
        end = Integer.parseInt(e);
    } catch (Exception exp) {
        start = 0;
        end = 0;
    }
%>
<form action="count.jsp" method="post">
    please input:<br>
    <input type="text" name="start" size="5">
    <input type="text" name="end" size="5">
    <input type="submit" value="submit">
</form>
<p style="font-size: 30px; color: red">
    <%= calculate(start, end) %>
</p>
<%!
    int start = 0;
    int end = 0;

    String calculate(int start, int end) {
        System.out.println(start + " " + end);
        if (start > end) {
            return "pls input correct range of number";
        } else if (start == end) {
            return start + "=" + end;
        } else {
            int sum = 0;
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            return start + " ... " + end + " = " + sum;
        }
    }
%>
</body>
</html>
