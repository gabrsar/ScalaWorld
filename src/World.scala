class World(x: Int, y: Int, z: Int) {



  var map = Array.ofDim[Char](x, y, z)

  for {
    i <- 0 until x
    j <- 0 until y
    k <- 0 until z
  } yield map(i)(j)(k) = (World.VOID)

    def set(x: Int, y: Int, z: Int, element: Char) = {
    map(x)(y)(z) = element
  }

  def getX = x
  def getY = y
  def getZ = z

  def get(x:Int, y:Int) = map(x)(y).clone()

  def fill(z:Int, elemtn:Char) = {
    (0 until  x).foreach(i =>
      (0 until y).foreach(j =>
        map(i)(j)(z) = elemtn
    ))
  }

  def pop(x: Int, y: Int) = {
    val xy: Array[Char] = map(x)(y)

    val poped = xy.last

    poped
  }
}

object World {

  val VOID = 'X'
  val FLOOR = '_'
  val WATER = '~'
  val WALL = '#'

}
