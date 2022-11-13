package me.limc.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// 275 / 276 / 277
@WebServlet(urlPatterns = {"/cooker"})
public class Cooker extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = (String) request.getAttribute("menu");
        request.setCharacterEncoding("utf-8");

        request.removeAttribute("menu");

        System.out.println("cooker: 哎，才休息一会，又要做菜了，这次做的是" + menu);

        request.setAttribute("dinner", "做好的" + menu);

        System.out.println("cooker: 嗯，终于做好了，通知送菜员送菜去。");

        request.getRequestDispatcher("sender").forward(request, response);
    }
}
