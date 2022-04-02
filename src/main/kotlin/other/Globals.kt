package other

import java.text.SimpleDateFormat
import java.util.*

fun commonAnnotation() = """
/**
 * @author dingdong
 * @date ${SimpleDateFormat("yyyy/MM/dd HH:mm").format(Date(System.currentTimeMillis()))}
 *     description:
 */
""".trimIndent()

fun layoutToDataBinding(layout: String): String {
    val words = layout.split("_")
    val sb = StringBuilder()
    for (w in words) {
        try {
            sb.append(w[0].toUpperCase())
            sb.append(w.substring(1))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return sb.toString()
}