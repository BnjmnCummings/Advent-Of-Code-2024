/**
 * takes input string of digits and produces the fragmented disk string
 */
fun parseDenseFormat(denseFormat: String):List<String> {
    val result:MutableList<String> = mutableListOf()
    for(i in denseFormat.indices) {
        // every even index, denseFormat[i] represents a file
        val current = if (i % 2 == 0) (i / 2).toString() else "."
        for (j in 0..< denseFormat[i].digitToInt()) {
            result.add(current)
        }
    }
    return result
}

/**
 * takes input string of a fragmented disk
 * returns un fragmented
 */
fun defragmentDisk(disk:List<String>):List<String> {
    val defragDisk = disk.toMutableList()
    var (left, right) = updatePointers(0, disk.lastIndex, defragDisk)

    while (left < right) {
        // by now, the right pointer points to a digit and the left, to an empty location
        defragDisk[left] = defragDisk[right]
        defragDisk[right] = "."
        left++
        right--

        // go to the next . character
        val (newLeft, newRight) = updatePointers(left, right, defragDisk)
        left = newLeft
        right = newRight
    }
    return defragDisk
}

/**
 * helper function that updates the pointers
 * @param left goes to the next empty space from the left
 * @param right goes to the next file id from the right
 */
fun updatePointers(left: Int, right:Int, disk: List<String>):Pair<Int,Int> {
    var newLeft = left
    var newRight = right

    while(disk[newRight] == ".") {
        newRight--
    }
    while(disk[newLeft] != ".") {
        newLeft++
    }
    return Pair(newLeft, newRight)
}

/**
 * multiplies each fileId with the new corresponding index in the disk
 */
fun calculateCheckSum(disk:List<String>):Long {
    var total:Long = 0
    for(i in disk.indices) {
        if(disk[i] == ".") {
            break
        }
        total += (i * disk[i].toInt()).toLong()
    }
    return total
}

fun main() {
    val denseDisk = readToRawString("src/main/resources/DayNine.txt")
    val list = defragmentDisk(parseDenseFormat(denseDisk))
    System.out.println(calculateCheckSum(list))
}
