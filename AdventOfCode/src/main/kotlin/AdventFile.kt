import java.io.File

fun formatTwoColumns(filename: String): Pair<List<Int>, List<Int>> {
    val left: MutableList<Int> = mutableListOf()
    val right: MutableList<Int> = mutableListOf()
    var split: List<String>
    File(filename).forEachLine {
        split = it.split("   ")
        left.add(split[0].toInt())
        right.add(split[1].toInt())
    }
    return Pair(left, right)
}
