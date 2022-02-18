import java.awt.Color
import java.awt.image.BufferedImage

fun drawSquare(width: Int = 500, height: Int = 500) = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB).apply {
    with(createGraphics()) {
        color = Color.RED
        drawRect(width / 5, height / 5, width * 3 / 5, height * 3 / 5)
    }
}