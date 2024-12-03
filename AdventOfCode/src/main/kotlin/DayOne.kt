fun main() {
    val pair: Pair<List<Int>, List<Int>> = formatTwoColumns("src/main/resources/DayOne.txt")
    System.out.println("Q1a: ${listDifference(pair)}")
    System.out.println("Q1b: ${similarityScore(pair)}")
}

/**
 * assume that lists are the same size
 * */
fun listDifference(lists: Pair<List<Int>, List<Int>>): Int {
    val leftCopy: MutableList<Int> = lists.first.toMutableList()
    val rightCopy: MutableList<Int> = lists.second.toMutableList()

    var totalDifference = 0
    var leftMin: Int
    var rightMin: Int

    while (leftCopy.isNotEmpty()) {
        leftMin = leftCopy.removeAt(leftCopy.indexOf(leftCopy.minOrNull()))
        rightMin = rightCopy.removeAt(rightCopy.indexOf(rightCopy.minOrNull()))
        totalDifference += if (leftMin > rightMin) leftMin - rightMin else rightMin - leftMin
    }
    return totalDifference
}

fun similarityScore(lists: Pair<List<Int>, List<Int>>): Int {
    // initialise occuranceMap
    val occurrenceMap = HashMap<Int, Int>()
    for (i in lists.second) {
        occurrenceMap[i] = occurrenceMap.getOrPut(i) { 0 } + 1

    }

    // calculate similarityScore
    var totalSimilarityScore = 0
    for (i in lists.first) {
        totalSimilarityScore += i * (occurrenceMap[i] ?: 0)
    }
    return totalSimilarityScore
}
