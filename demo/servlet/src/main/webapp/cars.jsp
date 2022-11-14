<%--
  Created by IntelliJ IDEA.
  User: limc
  Date: 11/14/22
  Time: 8:19 PM
  To change this template use File | Settings | File Templates.
  299
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="me.limc.demo.servlet.CarService" %>
<%@ page import="me.limc.demo.servlet.Car" %>
<%@ page import="java.util.ArrayList" %>
<%
    ArrayList<Car> cars = new ArrayList<>();
    Car car;
    String query = request.getParameter("query");
    if (query != null)
        if (query.isEmpty())
            cars = CarService.INSTANCE.loadCars();
        else {
            car = CarService.INSTANCE.loadCarId(query);
            if (car != null)
                cars.add(car);
        }

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

<% if (query == null) {%>
<h3>Please do query first.</h3>
<% } else { %>
<body>
<h3>Cars</h3>
<table border="1" cellspacing="0">
    <tr>
        <th>Car_no</th>
        <th>Car_weight</th>
        <th>Car_brand</th>
        <th>Car_price</th>
    </tr>
    <%for (Car i : cars) {%>
    <tr>
        <td onclick="test(this)"><%=i.getNo()%>
        </td>
        <td><%=i.getWeight()%>
        </td>
        <td><%=i.getBrand()%>
        </td>
        <td><%=i.getPrice()%>
        </td>
    </tr>
    <%} %>
</table>
<% } %>


<form action="cars.jsp" method="post">
    <span>Passing your id to query:<br></span>
    <input type="text" name="query" placeholder="leave it empty to query all"/><br>
    <input type="submit" value="submit"/>
</form>

</body>

</html>
