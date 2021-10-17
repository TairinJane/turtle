import java.util.function.Function

fun main(args: Array<String>) {
    println("Hello from Turtle!")
    printInstruction()

    while (true) {
        println("Enter command:")
        val input = readLine()

        if (!input.isNullOrEmpty()) {
            val list = input.split(" ").take(2)
            println(list)

            val commandText = list[0]
            try {
                val command: Command = when (commandText) {
                    "move" ->
                        MoveCommand(
                            withArg(
                                list,
                                { it.toInt() },
                                "Provide amount of steps (move {steps})",
                                "Steps value should be a valid integer"
                            )
                        )
                    "angle" -> AngleCommand(
                        withArg(
                            list,
                            { it.toInt() },
                            "Provide angle (angle {value})",
                            "Angle value should be a valid integer"
                        )
                    )
                    "pd" -> PdCommand()
                    "pu" -> PuCommand()
                    "color" -> ColorCommand(
                        withArg(
                            list,
                            { Color.valueOf(it.uppercase()) },
                            "Provide color (color {green | black})",
                            "Color can be one of: 'green', 'black'"
                        )
                    )
                    "list" -> ListCommand(
                        withArg(
                            list,
                            { ListType.valueOf(it.uppercase()) },
                            "Provide list type (list {steps | figures})",
                            "List type can be one of: 'steps', 'figures'"
                        )
                    )
                    "exit" -> ExitCommand()
                    else -> throw IllegalArgumentException("No such command exists. Did you mean 'exit'?")
                }
                command.execute()
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

}

private fun <T> withArg(
    list: List<String>,
    argParser: Function<String, T>,
    noArgMessage: String? = "No argument was provided",
    argErrorMessage: String? = "Wrong arg type"
): T {
    val arg = if (list.size < 2) throw IllegalArgumentException(noArgMessage) else list[1]
    try {
        return argParser.apply(arg)
    } catch (e: Exception) {
        throw IllegalArgumentException(argErrorMessage)
    }
}

private fun printInstruction() {
    println(
        "Commands:\n" +
                "move N: command to change turtle’s position on N steps.\n" +
                "angle N: command to change turtle’s angle of direction to N degrees.\n" +
                "pd: command to put down the pen.\n" +
                "pu: command to put up the pen.\n" +
                "color {colorName}: command to change turtle’s color of the pen to {colorName} color. Possible values: black, green.\n" +
                "list steps: command to show all executed steps.\n" +
                "list figures: command to show all properties of completed figures.\n" +
                "exit: command to exit the program.\n"
    )
}