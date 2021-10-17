interface Command {
    fun execute()
}

class MoveCommand(val steps: Int): Command {
    override fun execute() {
        println("-- command: move $steps")
        // /api/move?n=N
    }
}

class AngleCommand(val angle: Int): Command {
    override fun execute() {
        println("-- command: angle $angle")
        // /api/angle?value=N
    }
}

class PdCommand: Command {
    override fun execute() {
        println("-- command: pd")
        // /api/pd
    }
}

class PuCommand: Command {
    override fun execute() {
        println("-- command: pu")
        // /api/pu
    }
}

enum class Color {
    BLACK, GREEN
}

class ColorCommand(val color: Color): Command {
    override fun execute() {
        println("-- command: color $color")
        // /api/color?color=C
    }
}

enum class ListType {
    STEPS, FIGURES
}

class ListCommand(val type: ListType): Command {
    override fun execute() {
        println("-- command: list $type")
        // /api/list?type=T
    }
}

class ExitCommand: Command {
    override fun execute() {
        println("-- command: exit")
        // close everything sovsem
    }
}