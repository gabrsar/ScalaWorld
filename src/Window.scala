import java.awt.{BorderLayout, Dimension}
import javax.swing.JFrame.EXIT_ON_CLOSE
import javax.swing.{ImageIcon, JFrame, JLabel}

import World._

import scala.util.Random

class Window extends JFrame {

  setTitle("Scala Star")
  setVisible(true)
  setDefaultCloseOperation(EXIT_ON_CLOSE)

  val draw = new JLabel(new ImageIcon("./resources/images/terrain.png"))
  val world = new World(15, 15, 5)

  world.fill(0, GRASS)
  world.fill(1, r)


  def r = {
    val w = List(WALL,VOID,VOID,VOID,VOID,VOID,VOID,VOID,VOID,VOID,VOID,VOID,VOID,VOID)
    w(Random.nextInt(w.size))
  }


  val screen = new WorldRenderer(world)


  setLayout(new BorderLayout)

  setPreferredSize(new Dimension(screen.getSizeX, screen.getSizeY))
  add(screen.getScreen,BorderLayout.CENTER)

  pack()


}
