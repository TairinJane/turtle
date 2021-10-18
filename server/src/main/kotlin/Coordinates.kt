class Coordinates(x: Int, y: Int) {

    private var coordinates = Pair(x, y)

    val x
        get() = coordinates.first

    val y
        get() = coordinates.second

    fun change(steps: Int, direction: Direction): Coordinates {
        return when (direction) {
            Direction.UP -> Coordinates(coordinates.first, coordinates.second + steps)
            Direction.RIGHT -> Coordinates(coordinates.first + steps, coordinates.second)
            Direction.DOWN -> Coordinates(coordinates.first, coordinates.second - steps)
            Direction.LEFT -> Coordinates(coordinates.first - steps, coordinates.second)
        }
    }

    override fun toString(): String {
        return "($x; $y)"
    }
}

enum class Direction {
    UP, RIGHT, DOWN, LEFT
}