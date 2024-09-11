import kotlin.random.Random

fun main()
{
    val v = setOf(5, 3, 9, 2, 3, 6, 8, 9)
    v.forEach{
        println(v)
    }
    val m = mapOf<Int, String>(
        1 to "Car",
        2 to "Motor",
        3 to "MRT",
        Pair(4, "Nothing")
    )
    println(m)
    println(m[1])

    var s = Random.nextInt(2, 22)
    println(s)
}