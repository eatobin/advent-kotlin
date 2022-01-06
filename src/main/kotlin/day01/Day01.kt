package day01

import java.io.BufferedReader
import java.io.File

val bufferedReader: BufferedReader = File("src/main/kotlin/day01/day01.txt").bufferedReader()
val inputString: String = bufferedReader.use { it.readText() }
val gasList: List<Int> = inputString.trim().lines().map { it.toInt() }

// part a
fun gas(m: Int): Int = (m / 3) - 2

val answer: Int = gasList.sumOf { gas(it) }

// part b
tailrec fun gasPlus(m: Int, accum: Int = 0): Int {
    val newGas: Int = gas(m)
    return if (newGas <= 0) {
        accum
    } else {
        gasPlus(newGas, accum + newGas)
    }
}

val answer2: Int = gasList.sumOf { gasPlus(it) }

fun main() {
    println("Answer Part A: $answer") // 3337766

    println("Answer Part B: $answer2") // 5003788
}
