class Figure(private val points: List<Coordinates>) {

    val name = getFigureName()

    override fun toString(): String {
        val coordinates = points.joinToString(prefix = "{", postfix = "}") { "(${it.x}; ${it.y})" }
        return "$name with coordinates $coordinates"
    }

    private fun getFigureName(): String {
        return when (points.size) {
            3 -> "Triangle"
            4 -> {
                if (checkIfRectangle()) return "Rectangle"
                return "Quadrangle"
            }
            5 -> "Pentagon"
            else -> "Polygon"
        }
    }

    private fun checkIfRectangle(): Boolean {
        val line1 = Line(points[0], points[1])
        val line2 = Line(points[1], points[2])
        val line3 = Line(points[2], points[3])
        val line4 = Line(points[3], points[0])
        return (line1.checkIfParallel(line3) && line2.checkIfParallel(line4))
    }
}