import java.net.URL

fun main()
{
    //api.jsonserve.com/AXh1oX
    val s1 = URL("https://api.jsonserve.com/AXh1oX").readText()
    println(s1)

    val s2 = URL("https://api.jsonserve.com/AXh1oX").openStream()
    val s21 = s2.bufferedReader()
    var line = s21.readLine()
    while(line != null)
    {
        println(line)
        line = s21.readLine()
    }
}