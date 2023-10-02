package m5_advanced._2sealed_classes

import java.io.IOException
import kotlin.Result

fun getThing() = ""
fun main() {

    val output = try {
        val result = getThing()

        Result.success(result)
    } catch (e: IOException) {
        Result.failure(e)
    }

    when {
        output.isSuccess -> println("do this")
        output.isFailure -> println("do that")
    }

}