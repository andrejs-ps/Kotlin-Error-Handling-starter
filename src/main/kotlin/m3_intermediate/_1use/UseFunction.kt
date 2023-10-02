package m3_intermediate._1use

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

fun main() {

    printWithUse("src/main/resources/stocks.csv")

    println()

    printFileWithUse2("src/main/resources/stocks.csv")
}

// 1 - Under the hood, there is a try…catch block being used
// where the close() function of the resource is invoked inside the "finally".
fun printWithUse(filename: String) {
    try {
        File(filename).bufferedReader().use { reader -> // no need: longer, and our resp to close the reader
            reader.forEachLine { println(it) }
        }
    } catch (e: FileNotFoundException) {
        println("The file was not found")
    } catch (e: IOException) {
        println("An error occurred while reading the file: ${e.message}")
    }
}

fun printFileWithUse2(filename: String) {
    File(filename)
        .inputStream()
        .use {
            it.readBytes().also { s -> println(String(s)) }
        }
}