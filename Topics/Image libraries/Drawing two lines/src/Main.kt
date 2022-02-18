import java.awt.Color
import java.awt.image.BufferedImage

fun drawLines(width: Int = 200, height: Int = 200) = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB).apply {
    with(createGraphics()) {
        color = Color.RED
        drawLine(0, 0, width, height)
        color = Color.GREEN
        drawLine(width, 0, 0, height)
    }
}