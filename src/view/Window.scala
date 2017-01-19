package view

import java.awt.{BorderLayout, Dimension}
import javax.swing.JFrame.EXIT_ON_CLOSE
import javax.swing.{ImageIcon, JFrame, JLabel}

import io.WordReader
import world.TerrainElement.{LAND, WATER}
import world.{TerrainElement, World, WorldRenderer}

import scala.util.Random

class Window extends JFrame {

  setTitle("Scala Star")
  setVisible(true)
  setDefaultCloseOperation(EXIT_ON_CLOSE)

  val draw = new JLabel(new ImageIcon("./resources/images/terrain.png"))
  val world = new WordReader().read()


  val screen = new WorldRenderer(world)


  setLayout(new BorderLayout)

  setPreferredSize(new Dimension(screen.getSizeX, screen.getSizeY))
  add(screen.getScreen, BorderLayout.CENTER)

  pack()


}
