package me.limc.demo.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 275
@WebServlet(urlPatterns = {"/sender"})
public class Sender extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String dinner = (String) request.getAttribute("dinner");

        System.out.println("Sender: 上菜 " + dinner);
    }
}
