package com.example.ex2


fun main() {
    val num = 0..10

    for (i in 0..30 step 2)
    {
        println(i)
    }

    val array = intArrayOf(3,5,8,9,11)
    for (i in array) {
        println(i)
    }

    val s = arrayOf("you", "me", "it")
    println(s[1])
    println(s.size)

}