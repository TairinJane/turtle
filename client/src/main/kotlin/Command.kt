import kotlin.system.exitProcess

interface Command {
    fun execute(): String
}

val client = RestClient()

class MoveCommand(private val steps: Int): Command {
    override fun execute(): String {
        return client.get("/move", Pair("steps", steps))
    }
}

class AngleCommand(private val angle: Int): Command {
    override fun execute(): String {
        return client.get("/angle", Pair("a", angle))
    }
}

class PdCommand: Command {
    override fun execute(): String {
        return client.get("/pd")
    }
}

class PuCommand: Command {
    override fun execute(): String {
        return client.get("/pu")
    }
}

enum class Color {
    BLACK, GREEN
}

class ColorCommand(private val color: Color): Command {
    override fun execute(): String {
        return client.get("/color", Pair("c", color))
    }
}

enum class ListType {
    STEPS, FIGURES
}

class ListCommand(private val type: ListType): Command {
    override fun execute(): String {
        return client.get("/list", Pair("type", type))
    }
}

class ExitCommand: Command {
    override fun execute(): String {
        exitProcess(0)
    }
}