import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    val turtle = Turtle()

    embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                call.respondText("Hello, turtle!")
            }
            get("/api/move") {
                call.run {
                    try {
                        val steps = request.queryParameters["steps"]?.toInt()
                            ?: throw IllegalArgumentException("Provide 'steps' param")
                        val moveResult = turtle.move(steps)
                        val response = if (moveResult != null) moveResult + "\n" + turtle.state else turtle.state
                        respondText(response)
                    } catch (e: NumberFormatException) {
                        respondText("'steps' param is not a valid integer", status = HttpStatusCode.BadRequest)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        respondText(e.message ?: "", status = HttpStatusCode.BadRequest)
                    }
                }
            }
            get("/api/angle") {
                call.run {
                    try {
                        val angle = request.queryParameters["a"]?.toInt()
                            ?: throw IllegalArgumentException("Provide 'a' param")

                        turtle.angle = angle
                        respondText(turtle.state)
                    } catch (e: NumberFormatException) {
                        respondText("'a' param is not a valid integer", status = HttpStatusCode.BadRequest)
                    } catch (e: Exception) {
                        respondText(e.message ?: "", status = HttpStatusCode.BadRequest)
                    }
                }
            }
            get("/api/pd") {
                call.run {
                    turtle.penDown()
                    respondText(turtle.state)
                }
            }
            get("/api/pu") {
                call.run {
                    turtle.penUp()
                    respondText(turtle.state)
                }
            }
            get("/api/angle") {
                call.run {
                    try {
                        val color = request.queryParameters["c"]?.let { Color.valueOf(it.uppercase()) }
                            ?: throw IllegalArgumentException("Provide 'c' param")

                        turtle.color = color
                        respondText(turtle.state)
                    } catch (e: IllegalArgumentException) {
                        respondText("'c' param should be one of: 'green', 'black'", status = HttpStatusCode.BadRequest)
                    } catch (e: Exception) {
                        respondText(e.message ?: "", status = HttpStatusCode.BadRequest)
                    }
                }
            }
            get("/api/list") {
                call.run {
                    try {
                        val type = request.queryParameters["type"]?.let { ListType.valueOf(it.uppercase()) }
                            ?: throw IllegalArgumentException("Provide 'c' param")

                        val response = when(type) {
                            ListType.FIGURES -> turtle.listFigures()
                            ListType.STEPS -> turtle.listCommands()
                        }
                        respondText(response)
                    } catch (e: IllegalArgumentException) {
                        respondText("'type' param should be one of: 'steps', 'figures'", status = HttpStatusCode.BadRequest)
                    } catch (e: Exception) {
                        respondText(e.message ?: "", status = HttpStatusCode.BadRequest)
                    }
                }
            }
        }
    }.start(wait = true)
}