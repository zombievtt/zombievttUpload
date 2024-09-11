package com.example.ex2

fun main(){
    println("Please enter a number:(1-20)")
    val input = readLine()
    val num:Int = input?.toIntOrNull() ?: 0
    println("This is ${num}")
}
