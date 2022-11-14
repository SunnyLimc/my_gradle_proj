package me.limc.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

// 285
@WebServlet(urlPatterns = {"/sub"})
public class Sub extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int sub1 = Integer.parseInt(request.getParameter("sub1"));

        HttpSession session = request.getSession();

        System.out.println("sub sesion is new? " + session.isNew());
        System.out.println("session timeout:" + session.getMaxInactiveInterval());
        System.out.println("sub session id:" + session.getId());
        System.out.println("sub session createtime:" + new Date(session.getCreationTime()));

        int add1 = (Integer) session.getAttribute("add1");
        int add2 = (Integer) session.getAttribute("add2");
        int sum = (Integer) session.getAttribute("sum");

        int result = sum - sub1;
        session.setAttribute("result", result);
        session.setAttribute("sub1", sub1);

        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Insert title here</title>");
        out.println("</head>");
        out.println("<body>");
        out.println(String.format("<h3>%d+%d-%d=%d</h3>", add1, add2, sub1, result));
        out.println("<hr>");
        out.println("<a href=\"add.html\">再来一次</a>");
        out.println("<a href=\"LogLog\">记录日志</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
