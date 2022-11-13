package me.limc.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/110")
public class Zerozeroone extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = (String) req.getAttribute("name");
        resp.getWriter().println("黍属表示不管并白嫖了你一餐饭，当然，吃得不是" + name);
    }
}
