import java.awt.Color
import java.awt.image.BufferedImage

fun drawStrings(width: Int = 200, height: Int = 200, s: String = "Hello, images!") =
    BufferedImage(width, height, BufferedImage.TYPE_INT_RGB).apply {
        with(createGraphics()) {
            color = Color.RED
            drawString(s, width / 4, height / 4)
            color = Color.GREEN
            drawString(s, width / 4 + width / 200, height / 4 + width / 200)
            color = Color.BLUE
            drawString(s, width / 4 + width / 100, height / 4 + width / 100)
        }
    }