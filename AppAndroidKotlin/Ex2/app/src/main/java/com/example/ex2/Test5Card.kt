package com.example.ex2

fun main() {
    val a = mutableListOf<Int>()
    for (i in 0 ..< 52)
    {
        a.add(i)
    }
    println(a)

    val b = 2;
    var c = if (1 == b) {"A"}
    else if (2 == b) {"B"}
    else {"C"}
    println(c)

    val d = when(b) {
        0 -> "AAA"
        1 -> "BBB"
        2 -> "CCC"
        else -> {
            println("eeeee")
            "DDD"
        }
    }
    println(d)

    val e = when {
        1 > 2 -> "11111"
        2 > 1 -> "22222"
        else -> "33333"
    }
    println(e)


}