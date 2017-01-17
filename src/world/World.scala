package world

import world.TerrainElement._

class World(x: Int, y: Int, z: Int) {

  var map = Array.ofDim[CubicMeter](x, y, z)

  for {
    i <- 0 until x
    j <- 0 until y
    k <- 0 until z
  } yield map(i)(j)(k) = CubicMeter(VOID)

  def set(x: Int, y: Int, z: Int, elements: TerrainElement*):Unit = set(x, y, z, elements.toList)

  def set(x: Int, y: Int, z: Int, elements: List[TerrainElement]) = {
    map(x)(y)(z) = new CubicMeter(elements)
  }

  def sizeX = x

  def sizeY = y

  def sizeZ = z

  def get(x: Int, y: Int) = map(x)(y).clone()

  def get(x: Int, y: Int, z: Int) = map(x)(y)(z)

  def fill(z: Int, elements: TerrainElement*) = {
    (0 until x).foreach(i =>
      (0 until y).foreach(j =>
        set(i, j, z, elements.toList)
      ))
  }
}


object World {

  val water = CubicMeter(WATER)
  val waterOverGrass = CubicMeter(LAND,WATER)
  val grassOverLand = CubicMeter(LAND,GRASS)

}


