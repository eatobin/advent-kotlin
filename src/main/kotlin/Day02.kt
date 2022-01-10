import java.io.File

typealias Memory = Map<Int, Int>
typealias Instruction = Map<Char, Int>

const val fp = "src/main/resources/day02.csv"
const val offsetC = 1
const val offsetB = 2
const val offsetA = 3

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

data class IntCode(val pointer: Int, val memory: Memory)

fun aParam(instruction: Instruction, ic: IntCode): Int {
    return when (instruction['a']) {
        0 -> ic.memory[ic.pointer + offsetA]!! // a-p-w
        else -> 99
    }
}

fun bParam(instruction: Instruction, ic: IntCode): Int {
    return when (instruction['b']) {
        0 -> ic.memory[ic.memory[ic.pointer + offsetB]]!! // b-p-r
        else -> 99
    }
}

fun cParam(instruction: Instruction, ic: IntCode): Int {
    return when (instruction['c']) {
        0 -> ic.memory[ic.memory[ic.pointer + offsetC]]!! // c-p-r
        else -> 99
    }
}

fun updateMemory(memory: Memory, index: Int, value: Int): Memory {
    val mMemory = memory.toMutableMap()
    mMemory[index] = value
    return mMemory.toMap()
}

tailrec fun opCode(ic: IntCode): IntCode {
    val instruction: Instruction = pad5(ic.memory[ic.pointer]!!)
    if (instruction['d'] == 9) {
        return ic
    } else {
        return when (instruction['e']) {
            1 -> opCode(
                IntCode(
                    pointer = ic.pointer + 4,
                    memory = updateMemory(
                        ic.memory,
                        aParam(instruction, ic),
                        bParam(instruction, ic) + cParam(instruction, ic)
                    ).toMap()
                )
            )
            2 -> opCode(
                IntCode(
                    pointer = ic.pointer + 4,
                    memory = updateMemory(
                        ic.memory,
                        aParam(instruction, ic),
                        bParam(instruction, ic) * cParam(instruction, ic)
                    ).toMap()
                )
            )
            else -> ic
        }
    }
}

fun main() {
    println(makeMemory(fp))
    println(charToInt(57))
    println(pad5(101))
}
