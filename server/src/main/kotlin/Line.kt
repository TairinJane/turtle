import kotlin.math.max
import kotlin.math.min

class Line(private val point1: Coordinates, private val point2: Coordinates) {
    private val a = point2.y - point1.y
    private val b = point1.x - point2.x
    private val c = a * point1.x + b * point1.y

    fun checkIfParallel(line: Line): Boolean {
        val det = det(line)
        return det == 0
    }

    fun getIntersection(line: Line): Coordinates? {
        val det = det(line)
        if (det != 0) {
            val x = (line.b * this.c - this.b * line.c) / det
            val y = (this.a * line.c - line.a * this.c) / det
            return Coordinates(x, y)
        }
        return null
    }

    fun ifPointLiesOnLine(point: Coordinates): Boolean {
        return min(point1.x, point2.x) <= point.x && point.x <= max(point1.x, point2.x) &&
                min(point1.y, point2.y) <= point.y && point.y <= max(point1.y, point2.y)
    }

    private fun det(line: Line): Int {
        return this.a * line.b - line.a * this.b
    }
}