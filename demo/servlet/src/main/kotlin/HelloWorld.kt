package me.limc.demo.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

/**
 * Hello world
 *
 * @constructor Create empty Hello world
 */
@WebServlet(name = "HelloWorld", urlPatterns = ["/"])
class HelloWorld : HttpServlet() {
  /**
   * Do post
   *
   * @param req
   * @param resp
   */
  override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
    resp!!.writer.println("hello world!!")
  }
}
