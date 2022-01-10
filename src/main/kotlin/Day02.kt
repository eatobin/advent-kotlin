import java.io.File

typealias Memory = Map<Int, Int>
typealias Instruction = Map<Char, Int>

const val fp = "src/main/resources/day02.csv"
const val offsetC = 1
const val offsetB = 2
const val offsetA = 3

data class IntCode(val pointer: Int, val memory: Memory)

fun makeMemory(file: String): Memory {
    val bufferedReader = File(file).bufferedReader()
    val intList =
        bufferedReader
            .use { it.readText() }
            .trim()
            .split(",")
            .map { it.toInt() }
    return (intList.indices).zip(intList).toMap()
}

fun main() {
    println(makeMemory(fp))
}
