import java.awt.Color.{black, red}
import java.awt.{Color, Graphics}
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JPanel

import World.{FLOOR, WALL}

import scala.util.Random

class WorldRenderer(val world: World) {

  val tileSize = 64

  val baseTileUrl = "./resources/images/terrain.png"
  val imageX = ImageIO.read(new File(baseTileUrl))

  val void = sprite(0, 0)
  val waterA = sprite(4, 8)
  val waterB = sprite(5, 8)
  val wall = sprite(0, 1)
  val wtf = sprite(2, 0)

  var g: Graphics = _

  val wX = world.getX - 1
  val wY = world.getY - 1
  val sizeX = world.getX * tileSize
  val sizeY = ((world.getY+3) * tileSize * 0.5).toInt
  val baseX = sizeX /4
  val baseY = 0


  val screenHolder = new JPanel() {
    @Override
    override def paintComponent(graphics: Graphics): Unit = {
      super.paintComponent(graphics)
      renderThatShit(graphics)
    }
  }

  def renderThatShit(graphics: Graphics): Unit = {



    g = graphics
    graphics.setColor(black)
    graphics.fillRect(0, 0, sizeX, sizeY)

    graphics.setColor(red)

    (0 to wY).foreach(i =>
      (wY to 0 by -1).foreach(j => {
        val s = wordToSprite(world.get(i, j).last)

        val x = (i * tileSize/2) + (j*tileSize/2)  + baseY
        val y = -(j * tileSize/4) + (i*tileSize/4) + baseX

        graphics.drawImage(s, x, y, tileSize, tileSize, null)
//        graphics.drawString(s"$i,$j", x+tileSize/4, y+tileSize/4)
      })
    )

  }

  def sprite(x: Int, y: Int) = imageX.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize)

  def getScreen = screenHolder


  def wordToSprite(wordTile: Char) = wordTile match {
    case World.WATER => if (Random.nextBoolean()) waterA else waterB
    case World.FLOOR => wall
    case World.VOID => void
    case _ => wtf
  }

  def getSizeX = sizeX
  def getSizeY = sizeY


}
