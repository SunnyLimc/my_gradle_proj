/** Multiple servlets for learning */

package me.limc.demo.servlet

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import jakarta.servlet.ServletContext
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.core.lookup.EnvironmentLookup
import java.io.IOException
import java.sql.Connection
import java.sql.DriverManager

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
    resp!!.writer.println("hello world!!!")
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
@WebServlet(urlPatterns = ["/legacyadd"])
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
 * @property no
 * @property weight
 */
data class Car(
  var brand: String = "null",
  var price: Int = 0,
  var no: String = "null",
  var weight: Double = 0.0
)

/**
 * Car service
 *
 * @constructor Create empty Car service
 *
 * 299
 */
object CarService {
  /**
   * Log
   */
  private val log = LogManager.getLogger(this::class.simpleName)

  /**
   * Sql Query All
   */
  private val sqlQueryAll: String = "SELECT * FROM tbl_car"

  /**
   * Sql query id
   */
  private val sqlQueryId: String = "SELECT * FROM tbl_car WHERE car_no = ?"

  /**
   * Load cars
   *
   * @return an array of cars
   */
  fun loadCars(): ArrayList<Car> {
    val conn = Dbutils().getConn()
    val rset = conn.use { conn ->
      conn!!.prepareStatement(sqlQueryAll).executeQuery()
    }
    var cars: ArrayList<Car> = arrayListOf()
    try {
      while (rset.next()) {
        var car = Car().apply {
          brand = rset.getString("car_brand")
          price = rset.getInt("car_price")
          no = rset.getString("car_no")
          weight = rset.getDouble("car_weight")
        }
        cars.add(car)
      }
    } catch (e: Exception) {
      e.printStackTrace()
      log.error("Can't retrieve val from DBRSet")
    }
    conn!!.close()
    return cars
  }

  /**
   * Load cars id
   *
   * @param id id to query
   * @return an array of cars
   */
  fun loadCarId(id: String): Car? {
    val conn = Dbutils().getConn()
    val rset = conn.use { conn ->
      val stmt = conn!!.prepareStatement(sqlQueryId)
      stmt.setString(1, id)
      stmt.executeQuery()
    }
    val car: Car? = try {
      if (rset.next()) {
        Car().apply {
          brand = rset.getString("car_brand")
          price = rset.getInt("car_price")
          no = rset.getString("car_no")
          weight = rset.getDouble("car_weight")
        }
      } else {
        Car()
      }
    } catch (e: Exception) {
      e.printStackTrace()
      log.error("Can't retrieve val from DBRSet")
      null
    }
    conn!!.close()
    return car
  }
}

/**
 * 298 / 299
 *
 * DB utils
 *
 * @constructor Create empty Db utils
 */
class Dbutils {
  /**
   * Init
   *
   * @throws Exception pool can not init
   */
  private val conn: HikariDataSource? = try {
    Class.forName("org.mariadb.jdbc.Driver")
    val hc = HikariConfig().apply {
      jdbcUrl = "$CONN_URL?user=$USER&password=$PW"
    }
    hc.addDataSourceProperty("cachePrepStmts", true)
    HikariDataSource(hc)
  } catch (e: IOException) {
    log.error(e.printStackTrace().toString())
    throw Exception("The data source pool can not be initialized.")
  }

  /**
   * Get conn
   *
   * @return db conn
   */
  fun legacyGetConn(): Connection {
    Class.forName("org.mariadb.jdbc.Driver")
    return DriverManager.getConnection(CONN_URL, USER, PW)
  }

  /**
   * Get conn
   *
   * @return conn
   * @throws Exception can not init conn
   */
  fun getConn(): Connection? {
    conn ?: run {
      log.debug("The data source can not be initialized.")
      throw Exception("The data source can not be initialized.")
    }
    return try {
      conn.getConnection()
    } catch (e: Exception) {
      log.error(e.printStackTrace())
      throw e
      null
    }
  }

  companion object {
    /**
     * Log
     */
    private val log = LogManager.getLogger(this::class.simpleName)

    /** Conn Url */
    private const val CONN_URL = "jdbc:mariadb://localhost:3306/limc"

    /**
     * User
     */
    private const val USER = "java"

    /**
     * Pw
     */
    private const val PW = "thisisajavatestuser"

    /**
     * Context
     */
    private var context: ServletContext? = null
  }
}

/**
 * Studb
 *
 * @constructor Create empty Studb
 */
@WebServlet(urlPatterns = ["/studb"])
class StuDb : HttpServlet() {
  /**
   * Do get
   *
   * @param req
   * @param resp
   */
  override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
    addStu()
  }

  /** Add stu */
  fun addStu() {
    val conn = Dbutils().legacyGetConn()
    val stus = StudentServiceImpl.defaultRandomStu(RDM_CNT)
    for (i in stus) {
      val tmt = conn.prepareStatement(SQL_ADD)
      tmt.setInt(1, i.stuNo)
      tmt.setString(2, i.stuName)
      tmt.setInt(3, i.stuAge)
      tmt.setDouble(4, i.stuAge.toDouble())
      tmt.executeUpdate()
    }
    conn.close()
  }

  companion object {
    /** Sql Add */
    private const val SQL_ADD: String = "insert into tbl_student values(?,?,?,?)"
    private const val RDM_CNT = 3
  }
}


/**
 * Last edit d b
 *
 * @constructor Create empty Last edit d b
 */
@WebServlet(urlPatterns = ["/editdb"])
class lastEditDB : HttpServlet() {
  //! DO NOT USE THIS SQL IN PRODUCTION ENVIRONMENT

  /**
   * S update
   */
  private val sUpdate = "update tbl_car set car_weight=?, car_price=?, car_brand=? where car_no=?"

  /**
   * Do get
   *
   * @param req
   * @param resp
   */
  override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
    var price = req!!.getParameter("price")
    price = if (price.isEmpty() || price == null) "car_price" else price
    var brand = req!!.getParameter("brand")
    brand = if (brand.isEmpty() || brand == null) "car_brand" else brand
    var weight = req!!.getParameter("weight")
    weight = if (weight.isEmpty() || weight == null) "car_weight" else weight
    var queryno = req!!.getParameter("queryno")
    queryno = if (queryno.isEmpty() || queryno == null) "car_no" else queryno
    val conn = Dbutils().getConn()
    conn.use { conn ->
      val stmt = conn!!.prepareStatement(sUpdate)
      stmt.setString(1, weight)
      stmt.setString(2, price)
      stmt.setString(3, brand)
      stmt.setString(4, queryno)
      stmt.execute()
    }
    conn!!.close()
  }
}
