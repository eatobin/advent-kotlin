import java.io.BufferedReader
import java.io.File

typealias Memory = Map<Int, Int>
typealias Instruction = Map<Char, Int>

const val fp = "src/main/resources/day02.csv"
const val offsetC = 1
const val offsetB = 2
const val offsetA = 3

data class IntCode(val pointer: Int, val memory: Memory)

fun makeMemory(file: String): Memory {
    val bufferedReader: BufferedReader = File(fp).bufferedReader()
    val intList: List<Int> =
        bufferedReader
            .use { it.readText() }
            .trim().split(",")
            .map { it.toInt() }
    return intList.zip(0..intList.size).toMap()
}
