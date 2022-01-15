import java.io.File

typealias Memory = MutableMap<Int, Int>
typealias Instruction = Map<Char, Int>

const val fp = "src/main/resources/day02.csv"
const val offsetC = 1
const val offsetB = 2
const val offsetA = 3

fun makeMemory(file: String): Memory {
    val bufferedReader = File(file).bufferedReader()
    val intList = bufferedReader.use { it.readText() }.trim().split(",").map { it.toInt() }
    return (intList.indices).zip(intList).toMap().toMutableMap()
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

data class IntCode(var pointer: Int, var memory: Memory)

fun aParam(instruction: Instruction, ic: IntCode): Int {
    var param = 0
    when (instruction['a']) {
        0 -> param = ic.memory[ic.pointer + offsetA]!! // a-p-w
    }
    return param
}

fun bParam(instruction: Instruction, ic: IntCode): Int {
    var param = 0
    when (instruction['b']) {
        0 -> param = ic.memory[ic.memory[ic.pointer + offsetB]]!! // b-p-r
    }
    return param
}

fun cParam(instruction: Instruction, ic: IntCode): Int {
    var param = 0
    when (instruction['c']) {
        0 -> param = ic.memory[ic.memory[ic.pointer + offsetC]]!! // c-p-r
    }
    return param
}

fun opCode(ic: IntCode): Int {
    val instruction: Instruction = pad5(ic.memory[ic.pointer]!!)
    if (instruction['d'] == 9) {
        return 0
    } else {
        return when (instruction['e']) {
            1 -> {
                ic.memory[aParam(instruction, ic)] = bParam(instruction, ic) + cParam(instruction, ic)
                ic.pointer += 4
                1
            }
            2 -> {
                ic.memory[aParam(instruction, ic)] = bParam(instruction, ic) * cParam(instruction, ic)
                ic.pointer += 4
                1
            }
            else -> throw Exception("opcode is not valid")
        }
    }
}

fun updatedMemory(memory: Memory, noun: Int, verb: Int): Memory {
    memory[1] = noun
    memory[2] = verb
    return memory
}

fun nounVerb(): Int {
    var noun = 0
    var verb = 0

    out@ while (noun < 101) {
        while (verb < 101) {
            val tv = makeMemory(fp)
            val ic = IntCode(pointer = 0, memory = updatedMemory(memory = tv, noun = noun, verb = verb))
            var icReturn = 1
            while (icReturn == 1) {
                icReturn = opCode(ic)
            }
            if (ic.memory[0] == 19690720) {
                break@out
            }
            verb++
        }
        noun++
        verb = 0
    }
    return (100 * noun) + verb
}

fun main() {
    // part A
    val memory = makeMemory(fp)
    val ic = IntCode(pointer = 0, memory = updatedMemory(memory = memory, noun = 12, verb = 2))
    var icReturn = 1
    while (icReturn == 1) {
        icReturn = opCode(ic)
    }
    val answer: Int = ic.memory[0]!!
    println("Answer Part A: $answer") // 2890696

    // part B
    val answer2: Int = nounVerb()
    println("Answer Part B: $answer2") // 8226
}
