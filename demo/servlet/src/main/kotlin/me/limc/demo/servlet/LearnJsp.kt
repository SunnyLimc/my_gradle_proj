/** LearnJsp */

package me.limc.demo.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

typealias EmptyToObj = () -> Any

/**
 * Student service
 *
 * @constructor Create empty Student service
 */
interface StudentService {
  /**
   * Random
   *
   * @param count
   * @param ranId
   * @param ranNm
   * @param ranGrd
   * @return Map
   */
  fun randomStu(
    count: UInt,
    ranId: EmptyToObj,
    ranNm: EmptyToObj,
    ranGrd: EmptyToObj,
  ): Array<Student>

  /**
   * Default random stu
   *
   * @param count
   * @return use default strategy for random
   */
  fun defaultRandomStu(count: Int): Array<Student>
}

/**
 * @property stuNo
 * @property stuName
 * @property stuMark
 * @property stuAge
 */
data class Student(
  var stuNo: Int = 0,
  var stuName: String = "",
  var stuMark: Int = 0,
  var stuAge: Int = 0
)

object StudentServiceImpl : StudentService {
  const val RAND_ID_MAX = 99_999
  const val RAND_STR_LEN = 8
  const val RAND_GRADE_MIN = 60
  const val RAND_GRADE_MAX = 100
  const val COUNT = 100

  /**
   * Random
   *
   * @param count
   * @param ranId
   * @param ranNm
   * @param ranGrd
   * @return Map
   */
  override fun randomStu(
    count: UInt,
    ranId: EmptyToObj,
    ranNm: EmptyToObj,
    ranGrd: EmptyToObj,
  ): Array<Student> {
    val cnt: Int = count.toInt()
    var sub = 0
    var stu: ArrayList<Student> = arrayListOf()
    var sid: ArrayList<Int> = arrayListOf()
    while (stu.size != cnt) {
      sid.dropLast(sub)
      stu.dropLast(sub)
      for (i in stu.size until cnt) {
        val id = ranId() as Int
        stu.add(Student(id, ranNm() as String, ranGrd() as Int))
        sid.add(id)
      }
      sid.distinct()
      sub = cnt - sid.size
    }
    return stu.toTypedArray()
  }

  /**
   * Default random stu
   *
   * @param count
   * @return use default strategy for random
   */
  override fun defaultRandomStu(count: Int): Array<Student> = randomStu(
    COUNT.toUInt(),
    { (0..RAND_ID_MAX).random() },
    { (1..RAND_STR_LEN).map { (('0'..'9') + ('a'..'z') + ('A'..'Z')).random() }.joinToString("") },
    { (RAND_GRADE_MIN..RAND_GRADE_MAX).random() }
  )
}

/**
 * Student mgr servlet
 *
 * @constructor Create empty Student mgr servlet
 */
@WebServlet(urlPatterns = ["/stuMgr"])
class StudentMgrServlet : HttpServlet() {
  /** Logger */
  private val logger: Logger = LogManager.getLogger(this::class.simpleName)

  /**
   * Do get
   *
   * @param req
   * @param resp
   */
  override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
    val stuImpl = StudentServiceImpl
    val spc = StudentServiceImpl
    val cnt = spc.COUNT.toUInt()
    val stuArr = stuImpl.randomStu(
      cnt,
      { (0..spc.RAND_ID_MAX).random() },
      { (1..spc.RAND_STR_LEN).map { (('0'..'9') + ('a'..'z') + ('A'..'Z')).random() }.joinToString("") },
      { (spc.RAND_GRADE_MIN..spc.RAND_GRADE_MAX).random() }
    )
    for (i in stuArr) {
      logger.info("${i.stuNo} ${i.stuName} ${i.stuMark}")
    }
    req!!.setAttribute("stuArr", stuArr)
    req.getRequestDispatcher("student.jsp").forward(req, resp)
  }
}
