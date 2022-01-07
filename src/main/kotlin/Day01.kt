import java.io.BufferedReader
import java.io.File

val bufferedReader: BufferedReader = File("src/main/resources/day01.txt").bufferedReader()
val inputString: String = bufferedReader.use { it.readText() }
val gasList: List<Int> = inputString.trim().lines().map { it.toInt() }

// part a
fun fuel(m: Int): Int = (m / 3) - 2

val answer: Int = gasList.sumOf { fuel(it) }

// part b
tailrec fun fuelPlus(m: Int, accum: Int = 0): Int {
    val newGas: Int = fuel(m)
    return if (newGas <= 0) {
        accum
    } else {
        fuelPlus(newGas, accum + newGas)
    }
}

val answer2: Int = gasList.sumOf { fuelPlus(it) }

fun main() {
    println("Answer Part A: $answer") // 3337766

    println("Answer Part B: $answer2") // 5003788
}
