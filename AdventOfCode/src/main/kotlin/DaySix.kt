import java.io.File

enum class Direction {
    UP, RIGHT, DOWN, LEFT
}

data class Position(val first: Int, val second: Int)

data class Guard(var position: Position, var direction: Direction) {
    val visitedSet : HashSet<Position> = hashSetOf(position)

    fun move(row:Int, col:Int) {
        //collect current position
        position = Position(row, col)
        System.out.println(position)
        visitedSet.add(position)
    }

    fun turn90Degrees() {
        direction = Direction.entries[(direction.ordinal + 1) % 4]
    }
}

/**
 * returns a list of strings/ matrix of characters
 */
fun getQuestionSixInput(filename: String): List<List<Char>> {
    val listOfStrings: MutableList<List<Char>> = mutableListOf()
    File(filename).forEachLine {
        listOfStrings.add(it.toList())
    }
    return listOfStrings
}

/**
 * give a list of chars and a start index we need to iterate through them
 * and add the coords to coordSet of each '.' character
 */
fun patrol(grid: List<List<Char>>, guard: Guard) {
    //loop should terminate when guard reaches the edge
    while(!onEdge(grid, guard)) {
        when (guard.direction) {
            Direction.UP -> patrolUp(grid, guard)
            Direction.RIGHT -> patrolRight(grid, guard)
            Direction.DOWN -> patrolDown(grid, guard)
            Direction.LEFT -> patrolLeft(grid, guard)
        }
    }
}

fun patrolUp(grid: List<List<Char>>, guard: Guard) {
    var row: Int = guard.position.first
    var col: Int = guard.position.second

    while(!onEdge(grid, guard)) {
        //collect this square
        guard.move(row, col)
        //move up
        row--
        if(!onEdge(grid, guard) && grid[row][col] == '#') {
            guard.turn90Degrees()
            return
        }
    }

}

fun patrolDown(grid: List<List<Char>>, guard: Guard) {
    var row: Int = guard.position.first
    var col: Int = guard.position.second

    while(!onEdge(grid, guard)) {
        //collect this square
        guard.move(row, col)
        //move down
        row++
        if(!onEdge(grid, guard) && grid[row][col] == '#') {
            guard.turn90Degrees()
            return
        }
    }
}

fun patrolLeft(grid: List<List<Char>>, guard: Guard) {
    var row: Int = guard.position.first
    var col: Int = guard.position.second

    while(!onEdge(grid, guard)) {
        //collect this square
        guard.move(row, col)
        //move left
        col--
        if (!onEdge(grid, guard) && grid[row][col] == '#') {
            guard.turn90Degrees()
            return
        }
    }
}

fun patrolRight(grid: List<List<Char>>, guard: Guard) {
    var row: Int = guard.position.first
    var col: Int = guard.position.second

    while(!onEdge(grid, guard)) {
        //collect this square
        guard.move(row, col)
        //move right
        col++
        if (!onEdge(grid, guard) && grid[row][col] == '#') {
            guard.turn90Degrees()
            return
        }
    }
}

fun onEdge(grid: List<List<Char>>, guard: Guard):Boolean =
    (guard.position.first == grid.lastIndex && guard.direction == Direction.DOWN) ||    //  facing down and on the bottom edge
    (guard.position.first == 0 && guard.direction == Direction.UP) ||                   //  facing up and on the top edge
    (guard.position.second == grid.first().lastIndex && guard.direction == Direction.RIGHT) ||  //  facing right and on the right edge
    (guard.position.second == 0 && guard.direction == Direction.LEFT)                   //  facing left and on the left edge


fun main() {
    val grid = getQuestionSixInput("src/main/resources/DaySix.txt")
    val guard = Guard(findGuard(grid)!!, Direction.UP)
    patrol(grid, guard)
    System.out.println(guard.visitedSet.size)
}

fun findGuard(grid:List<List<Char>>):Position? {
    for(i in grid.indices) {
        for(j in grid.first().indices) {
            if("^>v<".contains(grid[i][j])) {
                return Position(i, j)
            }
        }
    }
    return null
}