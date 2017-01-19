package io

import world.{CubicMeter, TerrainElement, World}
import world.TerrainElement._

import scala.util.parsing.json.JSON

class WordReader {

  def read(): World = {

    val rawJson = """{"name":"First Map", "map":[[["W","W"],["W","W"]],[[" "," "],[" "," "]]]}"""

    val json = JSON.parseFull(rawJson).get.asInstanceOf[Map[String, Any]]

    val name = json("name")
    val world = json("map").asInstanceOf[List[List[List[String]]]]
    println(name)
    println(world)

    val lala = world.map(layer => {
      println(s"reading layer=$layer")
      layer.map(row => {
        println(s"reading row=$row")
        row.map(col => {
          println(s"reading col=$col")
          val elements = col.map(e => map.get(e)).toList
          CubicMeter(elements.map(_.get))
        }).toArray
      }).toArray
    }).toArray

    new World(lala)

  }

  val map: Map[Char, TerrainElement] = Map(
    'W' -> WATER,
    'X' -> GRASS,
    ' ' -> VOID
  )

}
