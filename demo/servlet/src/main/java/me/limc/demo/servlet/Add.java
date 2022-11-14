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
@WebServlet(urlPatterns = {"/add"})
public class Add extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int add1 = Integer.parseInt(request.getParameter("add1"));
        int add2 = Integer.parseInt(request.getParameter("add2"));

        int sum = add1 + add2;

        HttpSession session = request.getSession();
        session.setAttribute("add1", add1);
        session.setAttribute("add2", add2);
        session.setAttribute("sum", sum);

        System.out.println("add session is new? " + session.isNew());
        System.out.println("add session id:" + session.getId());
        System.out.println("add session createtime:" + new Date(session.getCreationTime()));

        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Insert title here</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>减法操作</h3>");
        out.println("<form action=\"sub\" method=\"get\">");
        out.println("<div>");
        out.println("<label>被减数：" + sum + "</label>");
        out.println("</div>");
        out.println("<div>");
        out.println("<label>减数：</label>");
        out.println("<input type=\"text\" name=\"sub1\"/>");
        out.println("</div>");
        out.println("<div>");
        out.println("<input type=\"submit\" value=\"计算\" /> ");
        out.println("</div>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
