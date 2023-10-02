package m3_intermediate._4runCatching

import java.io.File
import java.io.FileNotFoundException

fun main() {

    val result: Result<Int> = runCatching {
        val firstLine = File("src/main/resources/numbers.csv").useLines { it.first() }
        val nums = firstLine.split(",")

        val num1 = nums[0].toInt()
        val num2 = nums[1].toInt()

        num1 / num2
    }

    // this
    println("===== Success / Failure =====")
    result.onSuccess { println("Success"); println(it) }
    result.onFailure { println("Failure"); println(it) }

    // or this
    println("===== Get Or Else =====")
    val finalResult = result.getOrElse {
        when(it) {
            is FileNotFoundException -> println("Could not find the file: ${it.message}")
            is ArithmeticException -> println("Cannot divide by zero")
            is ArrayIndexOutOfBoundsException -> println("Expected at least two numbers")
            else -> println("No idea what happened. Have you tried turning it off and on again?")
        }
    }

    println(finalResult)
}