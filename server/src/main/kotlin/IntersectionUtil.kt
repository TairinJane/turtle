fun checkForFigure(points: List<Coordinates>): Figure? {
    val lastLine = Line(points[points.size - 2], points[points.size - 1])
    for (i in 0..points.size - 3) {
        val line = Line(points[i], points[i + 1])
        val intersection = lastLine.getIntersection(line)
        if (intersection != null && line.ifPointLiesOnLine(intersection) && lastLine.ifPointLiesOnLine(intersection)) return Figure(
            points.drop(i + 1).dropLast(1) + intersection
        )
    }
    return null
}