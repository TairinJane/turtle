interface Command {
    fun execute(): String
}

val client = RestClient()

class MoveCommand(private val steps: Int): Command {
    override fun execute(): String {
        println("-- command: move $steps")
        // /api/move?n=N
        return client.get("/move", Pair("n", steps))
    }
}

class AngleCommand(private val angle: Int): Command {
    override fun execute(): String {
        println("-- command: angle $angle")
        // /api/angle?a=N
        return client.get("/angle", Pair("a", angle))
    }
}

class PdCommand: Command {
    override fun execute(): String {
        println("-- command: pd")
        // /api/pd
        return client.get("/pd")
    }
}

class PuCommand: Command {
    override fun execute(): String {
        println("-- command: pu")
        // /api/pu
        return client.get("/pu")
    }
}

enum class Color {
    BLACK, GREEN
}

class ColorCommand(private val color: Color): Command {
    override fun execute(): String {
        println("-- command: color $color")
        // /api/color?c=C
        return client.get("/color", Pair("c", color))
    }
}

enum class ListType {
    STEPS, FIGURES
}

class ListCommand(private val type: ListType): Command {
    override fun execute(): String {
        println("-- command: list $type")
        // /api/list?type=T
        return client.get("/list", Pair("type", type))
    }
}

class ExitCommand: Command {
    override fun execute(): String {
        println("-- command: exit")
        // close everything sovsem
        return ""
    }
}