package cryptography

import java.io.File
import javax.imageio.ImageIO

class Crypto {
    private enum class Task(var message: String = "") {
        HIDE("Hiding message in image."),
        SHOW("Obtaining message from image."),
        EXIT("Bye!"),
        ERROR("Wrong task: error"),
        ;

        companion object {
            fun toTask(s: String) = values().firstOrNull { it.name == s.uppercase() } ?: ERROR.apply { message = "Wrong task: $s" }
        }
    }

    private fun String.encodeOrDecode(password: String = ""): String {
        if (password.isEmpty()) return this
        val s = StringBuilder()
        this.forEachIndexed { i, c -> s.append((c.code xor password[i % password.length].code).toChar()) }
        return s.toString()
    }

    private fun hide() {
        println("Input image file:")
        val fileNameInput = readln()
        runCatching {
            ImageIO.read(File(fileNameInput)).run {
                println("Output image file:")
                val fileNameOutput = readln()
                println("Message to hide:")
                val message = readln()
                println("Password:")
                val password = readln()
                val binaryMessage = message
                    .encodeOrDecode(password)
                    .plus(endOfMessage)
                    .map { it.code.toString(2).padStart(8, '0') }
                    .joinToString("")
                    .map { it - '0' }
                if (width * height < binaryMessage.size) throw Exception("The input image is not large enough to hold this message.")
                binaryMessage.forEachIndexed { index, b ->
                    val x = index % width
                    val y = index / width
                    setRGB(x, y, getRGB(x, y) and 0xFFFFFE or b)
                }
                ImageIO.write(this, "png", File(fileNameOutput))
                "Message saved in $fileNameOutput image."
            }
        }
            .onSuccess { println(it) }
            .onFailure { println(it.fillInStackTrace()) }
    }

    private fun show() {
        println("Input image file:")
        val fileInput = readln()
        println("Password:")
        val password = readln()
        runCatching {
            buildString {
                with(ImageIO.read(File(fileInput))) {
                    for (y in 0 until height)
                        for (x in 0 until width)
                            append((getRGB(x, y) and 1).digitToChar())
                }
            }
                .chunked(8)
                .map { it.toInt(2).toChar() }
                .joinToString("")
                .run { take(indexOf(endOfMessage)) }
                .encodeOrDecode(password)
        }
            .onSuccess { println("Message:\n$it") }
            .onFailure { println(it.localizedMessage) }
    }

    init {
        while (true) {
            println("Task (hide, show, exit):")
            when (Task.toTask(readln())) {
                Task.HIDE -> hide()
                Task.SHOW -> show()
                Task.EXIT -> { println(Task.EXIT.message); break }
                Task.ERROR -> println(Task.ERROR.message)
            }
        }
    }

    companion object {
        private val endOfMessage = byteArrayOf(0, 0, 3).toString(Charsets.UTF_8)
    }
}

fun main() {
    Crypto()
}