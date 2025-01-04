import java.lang.NullPointerException
import java.util.PriorityQueue
import kotlin.math.abs
//
//data class GridNode(
//    val pos: Pair<Int, Int>,
//    val pathLength: Int,
//    val heuristic: Int,
//    val parent: GridNode?
//)
//
enum class BiDirection {
    UPDOWN, LEFTRIGHT
}
//
//val nodeComparator = compareBy<GridNode> { it.pathLength + it.heuristic }
//
///**
// * @return the distance between the coordinate and the goals
// */
//fun getShortestPath(grid:List<List<Char>>):List<Pair<Int, Int>>? {
//    val startPos = Pair(grid.size - 2, 1)
//    val endPos = Pair(1, grid.first().size - 2)
//    val closedList: MutableList<GridNode> = mutableListOf()
//    val openList: PriorityQueue<GridNode> = PriorityQueue(nodeComparator)
//
//    /* add the start node to the queue */
//    openList.add(GridNode(
//        startPos,
//        0,
//        0,
//        null
//    ))
//
//    while(openList.isNotEmpty()) {
//        val currentNode = openList.poll()
//        closedList.add(currentNode)
//
//        /* if we've found the end position, backtrack to the start */
//        if(currentNode.pos == endPos) {
//            val path: MutableList<Pair<Int, Int>> = mutableListOf()
//            var current = currentNode
//            while(current != null) {
//                path.add(current.pos)
//                current = current.parent
//            }
//            return path.reversed()
//        }
//
//        /* add all the edge nodes*/
//        for (childPos in getSurrounding(currentNode.pos.first, currentNode.pos.second, grid)) {
//            /* if it's a block */
//            if(grid[childPos.first][childPos.second] == '#') {
//                continue
//            }
//            /* if child is already on the closed list */
//            if(closedList.any { it.pos == childPos }) {
//                continue
//            }
//            /* if there's a cheaper way of getting here already */
//            if(openList.any {
//                childPos == it.pos &&
//                currentNode.pathLength + 1 > it.pathLength
//            }) {
//                continue
//            }
//            /* add to open list */
//            openList.add(GridNode(
//                childPos,
//                currentNode.pathLength + 1,
//                manhattan(childPos, endPos),
//                currentNode
//            ))
//        }
//    }
//
//    /* return null if the goal can't be reached */
//    return null
//}
//
//fun manhattan(a: Pair<Int, Int>, b: Pair<Int, Int>): Int =
//    abs(a.first - b.first) + abs(a.second - b.second)

fun count90turns(path:List<Pair<Int,Int>>): Int {
    var currentDirection = BiDirection.LEFTRIGHT
    var total = 0
    for(i in 0..<path.lastIndex) {
        val direction = when(path[i + 1] - path[i]) {
             0  to  1 -> BiDirection.LEFTRIGHT
             0  to -1 -> BiDirection.LEFTRIGHT
             1  to  0 -> BiDirection.UPDOWN
            -1  to  0 -> BiDirection.UPDOWN
            else -> { throw ArithmeticException() }
        }

        /* if we change direction then update it and increment */
        if(currentDirection != direction) {
            currentDirection = direction
            total++
        }
    }
    return total
}

fun calculateScore(grid: List<List<Char>>): Int {
    try {
        val path = getShortestPath(grid)!!
        return 1000 * count90turns(path) + path.size
    } catch (e: NullPointerException) {
        throw NoSuchElementException("No Path Found")
    }
}

fun main() {
    val grid = getQuestionSixInput("src/main/resources/Day16.txt")
    println(calculateScore(grid))
}
