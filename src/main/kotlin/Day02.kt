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

fun charToInt(aChar: Byte): Int {
    if (aChar < 48 || aChar > 57) {
        throw Exception("Char is not an integer")
    } else {
        return (aChar - 48)
    }
}

fun pad5(op: Int): Instruction {
    val inInts = "%05d".format(op).toByteArray().map { charToInt(it) }
    return listOf('a', 'b', 'c', 'd', 'e').zip(inInts).toMap()
}

fun main() {
    println(makeMemory(fp))
    println(charToInt(57))
    println(pad5(101))
}
