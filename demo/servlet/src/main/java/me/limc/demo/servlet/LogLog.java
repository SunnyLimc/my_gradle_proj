package me.limc.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

// 287
@WebServlet(urlPatterns = "/LogLog")
public class LogLog extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int add1 = (Integer) session.getAttribute("add1");
        int add2 = (Integer) session.getAttribute("add2");
        int sub1 = (Integer) session.getAttribute("sub1");
        int result = (Integer) session.getAttribute("result");
        CalcLog log = new CalcLog();

        log.setCalcTime(new Date());

        log.setContent(String.format("%d+%d-%d=%d", add1, add2, sub1, result));

        log.setIpAddr(request.getRemoteAddr());

        System.out.println(log);

        CalcLogDaoCtx logDao = new CalcLogDaoCtx(request.getServletContext());

        logDao.addLog(log);

        if (request.getParameter("showAll") != null) {
            List<CalcLog> list = logDao.loadAll();
            list.forEach(System.out::println);
        }
    }
}
