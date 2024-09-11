package com.example.ex2

import kotlin.random.Random

fun main() {
    var db = Random.nextDouble(1.0, 11.0);
    println(db)
    println((1..11).random())
    for (i in 1..10)
    {
        println(i)
    }
}