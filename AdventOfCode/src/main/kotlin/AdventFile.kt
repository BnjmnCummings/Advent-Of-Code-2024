import java.io.File

fun formatTwoColumns(filename: String): Pair<List<Int>, List<Int>> {
    val left: MutableList<Int> = mutableListOf()
    val right: MutableList<Int> = mutableListOf()
    var currentLine: List<String>
    File(filename).forEachLine {
        currentLine = it.split("   ")
        left.add(currentLine[0].toInt())
        right.add(currentLine[1].toInt())
    }
    return Pair(left, right)
}

fun formatListOfLines(filename: String): List<List<Int>> {
    val listOfLines: MutableList<List<Int>> = mutableListOf()
    var currentLine: List<Int>
    File(filename).forEachLine {
        currentLine = it.split(" ").map { num -> num.toInt() }
        listOfLines.add(currentLine)
    }
    System.out.println(listOfLines)
    return listOfLines
}
