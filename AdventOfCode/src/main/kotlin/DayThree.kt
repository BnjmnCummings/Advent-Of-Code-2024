val regex = Regex("mul\\(\\d+,\\d+\\)")

fun main() {
    val corruptedMemory: String = readToRawString("src/main/resources/DayThree.txt")
    System.out.println(getSumOfMuls(corruptedMemory))
}

fun getSumOfMuls(target:String) =
    getMuls(target)
        .map { parseMul(it) }
        .fold(0, Int::plus)

fun getMuls(target:String):List<String> =
    regex.findAll(target).map { it.value }.toList()

fun parseMul(mulString:String):Int {
    assert(mulString.matches(regex))
    return "\\d+".toRegex().findAll(mulString).map {it.value.toInt()}.fold(1, Int::times)
}

