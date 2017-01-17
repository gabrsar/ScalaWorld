package world

case class TerrainElement(name: String)

object TerrainElement {
  val WATER = TerrainElement("water")
  val WALL = TerrainElement("wall")
  val FLOOR = TerrainElement("floor")
  val LAND = TerrainElement("land")
  val GRASS = TerrainElement("grass")
  val VOID = TerrainElement("void")
}