package me.limc.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// 275

@WebServlet(urlPatterns = {"/coop"})
public class Waiter extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String menu = request.getParameter("menu");

        if (menu.equals("xiongzhang")) {
            response.sendRedirect("110");
            return;
        }

        System.out.println("waiter: 收到客户点菜，点的是" + menu);

        request.setAttribute("menu", menu);

        System.out.println("waiter: 保存好了菜单，通知厨师做菜去.");

        request.getRequestDispatcher("cooker").forward(request, response);
    }
}
