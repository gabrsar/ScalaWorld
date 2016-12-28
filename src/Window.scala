import java.awt.Dimension
import javax.swing.JFrame.EXIT_ON_CLOSE
import javax.swing.{ImageIcon, JFrame, JLabel}

class Window extends JFrame{

  setTitle("Scala Star")
  setVisible(true)
  setDefaultCloseOperation(EXIT_ON_CLOSE)


  val draw = new JLabel(new ImageIcon("./resources/images/terrain.png"))

  val world = new World(20,20,1)

  world.fill(0,World.WATER)




  val screen = new WorldRenderer(world)

  setPreferredSize(new Dimension(screen.getSizeX,screen.getSizeY))
  add(screen.getScreen)

  pack()




}
