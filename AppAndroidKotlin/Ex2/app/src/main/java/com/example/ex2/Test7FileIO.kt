package com.example.ex2

import java.io.File

open class Meet(val name:String, val price:Int)
{

}

fun main()
{
    //Todo: 匿名類別
    object : Meet("chicken", 90) {
        fun show()
        {
            println("show")
        }
    }
    val pork = Meet("Pork", 100)
    val beef = Meet("beef", 110)
    val sea = Meet("Sea", 120)
    val meetData = listOf<Meet> (pork, beef, sea)
    val pW = File("meet.txt").printWriter()
    for (meet in meetData)
    {
        pW.println("${meet.name},${meet.price}")
    }
    pW.flush()

    val pBW = File("meetBuffer.txt").bufferedWriter()
    for (meet in meetData)
    {
        pBW.write("${meet.name},${meet.price}\n")
    }
    pBW.flush()

    val meetDataBuf = mutableListOf<Meet>()
    val pInput = File("meetBuffer.txt").bufferedReader()
    var line = pInput.readLine()
    while(line!=null)
    {
        println(line)
        val tokens = line.split(",")
        val m = Meet(tokens[0], tokens[1].toInt())
        meetDataBuf.add(m)
        line = pInput.readLine()
    }
    println(meetDataBuf)

    val v :Int? = null
    println(v)
    println("after v")
}