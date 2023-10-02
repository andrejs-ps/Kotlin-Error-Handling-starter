package m3_intermediate._3final

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

data class Stock(val name: String, val lastPrice: Double, val annualDividend: Double)

fun main() {

    val stocks = mutableListOf<Stock>()

    val content = readTextFile("src/main/resources/stocks.csv")

    if (content.isEmpty()) return

    content.trim().lines()
        .drop(1)
        .map { it.split(",") }
        .forEachIndexed { index, columns ->
            if (columns.size == 3) {
                val name = columns[0]
                val lastPrice = columns[1].toDoubleOrNull() ?: 0.0
                val annualDividend = columns[2].toDoubleOrNull() ?: 0.0
                val yield = (annualDividend / lastPrice) * 100
                stocks.add(Stock(name, lastPrice, yield))
            } else {
                println("Skipping line $index: Malformed data")
            }
        }

    stocks.forEach { stock ->
        println("${stock.name}: Dividend Yield = ${"%.2f".format(stock.annualDividend)}%")
    }

}

fun readTextFile(filename: String) = try {
    File(filename).readText()
} catch (e: FileNotFoundException) {
    println("The file was not found")
    ""
} catch (e: IOException) {
    println("An error occurred while reading the file: ${e.message}")
    ""
}

