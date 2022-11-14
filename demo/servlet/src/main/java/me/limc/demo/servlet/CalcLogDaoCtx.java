package me.limc.demo.servlet;

import jakarta.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

public class CalcLogDaoCtx {
    private ServletContext ctx;

    public CalcLogDaoCtx(ServletContext ctx) {
        this.ctx = ctx;
    }

    /* (non-Javadoc)
     * @see com.abc.dao.CalcLogDao#addLog(com.abc.domain.CalcLog)
     */
    public void addLog(CalcLog log) {
        Object obj = this.ctx.getAttribute("loglist");
        List<CalcLog> logList = null;
        if (obj == null) {
            logList = new ArrayList<>();
            this.ctx.setAttribute("loglist", logList);
        } else logList = (List<CalcLog>) obj;
        logList.add(log);
    }

    /* (non-Javadoc)
     * @see com.abc.dao.CalcLogDao#loadAll()
     */
    public List<CalcLog> loadAll() {
        Object obj = this.ctx.getAttribute("loglist");
        if (obj != null) return (List<CalcLog>) obj;
        return null;
    }
}
