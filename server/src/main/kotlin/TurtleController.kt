import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                call.respondText("Hello, turtle!")
            }
            get("/api/move") {
                call.run {
                    val steps = request.queryParameters["n"]?.toInt()
                    println("Move request")
                    respondText("Move n = $steps")
                }
            }
            get("/api/angle") {
                call.run {
                    val angle = request.queryParameters["a"]?.toInt()
                    println("Angle request")
                    respondText("Angle a = $angle")
                }
            }
            get("/api/pd") {
                call.run {
                    println("Pd request")
                    respondText("Pd")
                }
            }
            get("/api/pu") {
                call.run {
                    println("Pu request")
                    respondText("Pu")
                }
            }
            get("/api/angle") {
                call.run {
                    val color = request.queryParameters["c"]
                    println("Color request")
                    respondText("Color c = $color")
                }
            }
            get("/api/list") {
                call.run {
                    val type = request.queryParameters["type"]
                    println("List request")
                    respondText("List type = $type")
                }
            }
        }
    }.start(wait = true)
}