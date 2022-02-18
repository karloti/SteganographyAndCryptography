import java.awt.Color
import java.awt.image.BufferedImage

fun drawCircles(width: Int = 200, height: Int = 200) = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB).apply {
    with(createGraphics()) {
        color = Color.RED
        drawOval(width / 4, height / 4, width / 2, height / 2)
        color = Color.YELLOW
        drawOval(width / 4, height * 3 / 8, width / 2, height / 2)
        color = Color.GREEN
        drawOval(width * 3 / 8, height / 4, width / 2, height / 2)
        color = Color.BLUE
        drawOval(width * 3 / 8, height * 3 / 8, width / 2, height / 2)
    }
}
