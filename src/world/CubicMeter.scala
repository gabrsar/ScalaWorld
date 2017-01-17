package world

case class CubicMeter(elements: List[TerrainElement]) {}

object CubicMeter {
  def apply(elements: TerrainElement*) = new CubicMeter(elements.toList)
}
