val numKeypadMap: HashMap<Char, Pair<Int, Int>> = hashMapOf(
    'A' to Pair(3,2),
    '0' to Pair(3,1),
    '1' to Pair(2,0),
    '2' to Pair(2,1),
    '3' to Pair(2,2),
    '4' to Pair(1,0),
    '5' to Pair(1,1),
    '6' to Pair(1,2),
    '7' to Pair(0,0),
    '8' to Pair(0,1),
    '9' to Pair(0,2),
)

val dirKeypadMap: HashMap<Char, Pair<Int, Int>> = hashMapOf(
    'A' to Pair(0,2),
    '^' to Pair(0,1),
    '<' to Pair(1,0),
    'v' to Pair(1,1),
    '>' to Pair(1,2),
)

fun calculateComplexity(code:String):Int {
    var sequence = getSequence(code, numKeypadMap)
    sequence = getSequence(getSequence(sequence, dirKeypadMap), dirKeypadMap)
    return code.take(3).toInt() * sequence.length
}

/**
 *
 */
fun getSequence(buttons:String, map:Map<Char, Pair<Int, Int>>):String =
    getMinSequence(
        map['A']!!, buttons.map { map[it]!! }
    )

/**
 * Function that takes in a list of coordinates
 * and returns instructions to press the button at each one
 */
fun getMinSequence(start:Pair<Int, Int>, buttons: List<Pair<Int, Int>>):String {
    var current: Pair<Int, Int> = start
    var result = ""
    for(button in buttons) {
        /* get the difference */

        val (dRow, dCol) = button - current
        val updo = if(dRow >= 0) {
            "v".repeat(dRow)
        } else {
            "^".repeat(-dRow)
        }
        val leri = if(dCol >= 0) {
            ">".repeat(dCol)
        } else {
            "<".repeat(-dCol)
        }

        /* < before v before ^ before > */
        /* if left, then left before up and down */
        result += if(dCol < 0) {
            leri + updo + "A"
        } else {
            updo + leri + "A"
        }

        current = button
    }
    return result
}

fun main() {
    println(
        listOf(
            "836A",
            "540A",
            "965A",
            "480A",
            "789A"
        ).sumOf { calculateComplexity(it) }
    )
}