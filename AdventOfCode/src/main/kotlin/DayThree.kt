val mulMatcher = Regex("mul\\(\\d+,\\d+\\)")

fun main() {
    val corruptedMemory: String = readToRawString("src/main/resources/DayThree.txt")
    System.out.println(getSumOfMuls(corruptedMemory))
    System.out.println(parseDosAndDonts(corruptedMemory))
}

/* part one */
fun getSumOfMuls(target:String) =
    getMuls(target)
        .map { parseMul(it) }
        .fold(0, Int::plus)

fun getMuls(target:String):List<String> =
    mulMatcher.findAll(target).map { it.value }.toList()

fun parseMul(mulString:String):Int {
    assert(mulString.matches(mulMatcher))
    return "\\d+".toRegex().findAll(mulString).map {it.value.toInt()}.fold(1, Int::times)
}

val regexPartTwo = Regex("(mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\))")

/* part two */
fun getFunctions(target:String):List<String> =
    regexPartTwo.findAll(target).map { it.value }.toList()

fun parseDosAndDonts(target:String): Int {
    var total = 0
    var mulsEnabled = true
    getFunctions(target).forEach {
        when {
           "do\\(\\)".toRegex().matches(it) -> mulsEnabled = true
           "don't\\(\\)".toRegex().matches(it) -> mulsEnabled = false
           mulMatcher.matches(it) && mulsEnabled -> total += parseMul(it)
        }
    }
    return total
}
