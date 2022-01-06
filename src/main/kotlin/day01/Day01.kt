package day01

import java.io.BufferedReader
import java.io.File

val bufferedReader: BufferedReader = File("src/main/kotlin/day01/day01.txt").bufferedReader()
val inputString: String = bufferedReader.use { it.readText() }
val gasList: List<Int> = inputString.trim().lines().map { it.toInt() }


// part a
fun gas(m: Int): Int = (m / 3) - 2

val answer: Int = gasList.sumOf { gas(it) }

fun main() {
    println("Answer Part A: $answer") // 3337766
}
