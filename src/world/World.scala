package world

import world.TerrainElement._

class World(x: Int, y: Int, z: Int) {

  def this(map: Array[Array[Array[CubicMeter]]]) {
    this(
      map.last.last.length,
      map.last.length,
      map.length
    )

    this.map = map
  }


  /** It's too expensive to recreate a new 'version' of the map every time anything changes */
  var map = Array.ofDim[CubicMeter](x, y, z)

  println(s"x=$x,y=$y,z=$z, map='$map'")

  /** FUCKING "BIG" DECISION ON DATA ORGANIZATION SHOULD BE MADE.
    * Organize like minecraft with chunks? (it works!, it's tested, but, why not try something new?)
    * Z->(X/Y->Y/X)? (X/Y->Y/X)->Z?
    */

  /**
    * BLA BLA BLA CODE IS NOT READY TO COMMIT, BUT I HAVE TO SLEEP. Work tomorrow.
    */

  for {
    i <- 0 until x
    j <- 0 until y
    k <- 0 until z
  } yield map(i)(j)(k) = CubicMeter(VOID)

  def set(x: Int, y: Int, z: Int, elements: TerrainElement*): Unit = set(x, y, z, elements.toList)

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
  val waterOverGrass = CubicMeter(LAND, WATER)
  val grassOverLand = CubicMeter(LAND, GRASS)

}


