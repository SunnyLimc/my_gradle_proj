/** Multiple servlets for learning */

package me.limc.demo.servlet

import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.core.lookup.EnvironmentLookup

/**
 * 268/273 - Entry
 *
 * @constructor Create empty Entry
 */
@WebServlet(name = "HelloWorld", urlPatterns = ["/hello"])
class Entry : HttpServlet() {
  /** Logger */
  private val logger: Logger = LogManager.getLogger(this::class.simpleName)

  /** Env */
  lateinit var env: EnvironmentLookup

  /** Init */
  override fun init() {
    super.init()
    env = EnvironmentLookup()
    logger.info("init called: ${this::class.simpleName}")
  }

  /**
   * Service
   *
   * @param req
   * @param res
   */
  override fun service(req: ServletRequest?, res: ServletResponse?) {
    super.service(req, res)
    logger.info("service called: ${this::class.simpleName}")
  }

  /**
   * Do get
   *
   * @param req
   * @param resp
   */
  override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
    resp!!.writer.println("hello world!!")
    logger.info("doGet() called: ${this::class.simpleName}")
  }

  /** Destroy */
  override fun destroy() {
    super.destroy()
    logger.info("destory() called: ${this::class.simpleName}")
  }
}

/**
 * 269 - Add work servlet
 *
 * @constructor Create empty Prime work servlet
 */
@WebServlet(urlPatterns = ["/add"])
class AddWorkServlet : HttpServlet() {
  /** Logger */
  private val logger: Logger = LogManager.getLogger(this::class.simpleName)

  /**
   * Do get
   *
   * @param req
   * @param resp
   */
  override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
    val num1 = req!!.getParameter("num1").toInt()
    val num2 = req.getParameter("num2").toInt()

    logger.info(String.format("%d + %d = %d\n", num1, num2, num1 + num2))
    resp!!.writer.println("OK! Consult your console.")
  }
}

/**
 * 270
 *
 * @constructor Create empty Prime service
 */
interface PrimeService {
  /**
   * Find scoped prime
   *
   * @param begin
   * @param end
   * @return ret
   */
  fun findScopedPrime(begin: Int, end: Int): List<Int>

  /**
   * Is prime
   *
   * @param num
   * @return ret
   */
  fun isPrime(num: Int): Boolean
}

/**
 * 270 Prime service impl
 *
 * @constructor Create empty Prime service impl
 */
object PrimeServiceImpl : PrimeService {
  /**
   * Find scoped prime
   *
   * @param begin
   * @param end
   */
  override fun findScopedPrime(begin: Int, end: Int): List<Int> {
    var primeList: MutableList<Int> = mutableListOf()

    for (i in begin..end) {
      if (isPrime(i)) {
        primeList.add(i)
      }
    }
    return primeList
  }

  /**
   * Is prime
   *
   * @param num
   */
  override fun isPrime(num: Int): Boolean {
    if (num <= 1) {
      return false
    }
    if (num == 2) {
      return true
    }
    val top = Math.sqrt(num.toDouble()).toInt()
    for (i in 2..top) {
      if (num % i == 0) {
        return false
      }
    }
    return true
  }
}

/**
 * 270 - Prime work servlet
 *
 * @constructor Create empty Prime work servlet
 */
@WebServlet(urlPatterns = ["/prime"])
class PrimeWorkServlet : HttpServlet() {
  /** Logger */
  private val logger: Logger = LogManager.getLogger(this::class.qualifiedName)

  /**
   * Do get
   *
   * @param req
   * @param resp
   */
  override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
    val begin = req!!.getParameter("beginScope").toInt()
    val end = req.getParameter("endScope").toInt()
    val ps = PrimeServiceImpl
    val list: List<Int> = ps.findScopedPrime(begin, end)
    logger.info(list)
    resp!!.contentType = "text/html"
    var str = StringBuilder()
    str.append(
      """
      <html><head>
      <style>span{color:blue;display:inline-block;width:70px;text-align:center;font-size:24px;}</style>
      </head>
      <body>
      <h3>prime search scope: $begin-$end</h3>
      """.trimIndent()
    )
    for (i in list) {
      str.append("<span>$i</span>")
      if ((i + 1) % 10 == 0) {
        str.append("<br>")
      }
    }
    str.append("</body></html>")
    resp.writer.println(str)
    resp.writer.flush()
    resp.writer.close()
  }
}

/**
 * Car
 *
 * @property brand
 * @property price
 */
data class Car(
  var brand: String = "null",
  var price: Int = 0
)
