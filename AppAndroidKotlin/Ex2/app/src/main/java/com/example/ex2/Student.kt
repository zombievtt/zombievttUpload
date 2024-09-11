package com.example.ex2

data class Student (val id:String, val name:String, val chinese:Int, val english:Int) {
    constructor(idA:String,  nameA:String) : this(idA, nameA, 0, 0)
    enum class Fruit {
        APPLE, ORANGE
    }
    fun show() {
        println("$id,$name,$chinese, $english, ${ave()}, ${grading()}")
    }
    fun ave() :Int
    {
        return (chinese + english)/2
    }

    fun grading() : String = when(ave()/10) {
        9, 10 -> "A"
        8 -> "B"
        7 -> "C"
        6 -> "D"
        else -> "F"
    }
}

fun Student.hello(msg : String) {
    println("Hello ${msg}")
}
fun main()
{
    val lFruit = Student.Fruit.APPLE
    val students = listOf(
        Student("000", "Amy", 80, 90),
        Student("001", "Mary")
    );
    for (i in 0..1)
    {
        students.get(i).show()
    }
    for (i in students)
    {
        i.show()
    }

    val s1 = Student("000", "John", 80, 90)
    val s2 = Student("001", "Joe")
    s1.show()
    s2.show()
    println("--------------------")

    var s3 = s1.copy(id = "002", name = "Rober")
    println(s3)
    s3.hello("Joe")
}