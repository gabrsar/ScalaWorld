import java.awt.Color._
import java.awt.{Color, Dimension, Graphics}
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JPanel

import scala.util.Random

class WorldRenderer(val world: World) {

  val tileSize = 64
  val baseTileUrl = "./resources/images/terrain.png"
  val baseSprite = ImageIO.read(new File(baseTileUrl))
  val baseSpriteSizeX = 10
  val baseSpriteSizeY = 11

  val void = sprite(0, 0)

  def multiSprite(range: Range) = {
    val sprites = range.map(sprite)
    sprites(Random.nextInt(sprites.size))
  }

  def grass = multiSprite(1 to 18)

  def wall = multiSprite(50 to 56)

  def water = multiSprite(90 to 100)

  val wtf = sprite(2, 0)

  var g: Graphics = _

  val wX = world.sizeX - 1
  val wY = world.sizeY - 1
  val wZ = world.sizeZ - 1
  val marginX = 2
  val marginY = 4
  val sizeX = (world.sizeX + marginX * 2) * tileSize
  val sizeY = (world.sizeY + marginY * 2) * tileSize / 2
  val baseX = marginX * tileSize
  val baseY = -(tileSize / 2) + sizeY / 2

  val screenHolder = new JPanel() {
    @Override
    override def paintComponent(graphics: Graphics): Unit = {
      super.paintComponent(graphics)
      renderWorld(graphics)

    }
  }

  screenHolder.setPreferredSize(new Dimension(getSizeX, getSizeY))

  def renderWorld(graphics: Graphics): Unit = {


    g = graphics
    graphics.setColor(black)
    graphics.fillRect(0, 0, sizeX, sizeY)

    graphics.setColor(red)

    (0 to wX).foreach(i =>
      (wY to 0 by -1).foreach(j => {
        (0 to wZ).foreach(k => {
          val s = wordToSprite(world.get(i, j,k))

          val x = (i * tileSize / 2) + (j * tileSize / 2) + baseX
          val y = -(j * tileSize / 4) + (i * tileSize / 4) + baseY
          val z = k * (tileSize/2)

          //        val colors = List(BLUE, RED, WHITE)
          //        graphics.setColor(colors((i + j) % colors.length))
          //        graphics.fillRect(x, y, tileSize, tileSize)
          graphics.drawImage(s, x, y-z, tileSize, tileSize, null)
          //graphics.drawString(s"$i,$j", x+tileSize/4, y+tileSize/4)
        })
      })

    )

  }

  def sprite(x: Int, y: Int) = baseSprite.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize)

  def sprite(index: Int) = {
    val x = index % baseSpriteSizeX
    val y = index / baseSpriteSizeX
    baseSprite.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize)
  }

  def getScreen = screenHolder


  def wordToSprite(wordTile: Char) = wordTile match {
    case World.WATER => water
    case World.WALL => wall
    case World.VOID => void
    case World.GRASS => grass
    case _ => wtf
  }

  def getSizeX = sizeX

  def getSizeY = sizeY


}
