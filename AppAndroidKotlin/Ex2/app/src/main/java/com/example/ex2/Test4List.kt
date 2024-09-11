fun main()
{
    val a = listOf("aaa", "bbb", "ccc")
    println(a)

    val b = listOf<String>("ddd", "eee", "fff")
    println(b)

    println(b[0])
    println(b.get(0))
    println(b.size)

    val c = mutableListOf<Int>(1,2,3,4,5)
    println(c)

    c.add(6)
    c.add(1, 99)
    println(c)

    c.removeAt(0)
    println(c)

    c.add(5)
    println(c)

    c.remove(5)
    println(c)

    c.shuffle()
    println(c)

    val d = c.removeAt(0)
    println(d)
}