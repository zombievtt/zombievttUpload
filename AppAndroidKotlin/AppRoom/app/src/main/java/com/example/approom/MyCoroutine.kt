package com.example.approom

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    launch {
        fn001()
    }
    println("fn000()")

}
fun fn000() {
    println("fn000()")
}

suspend fun fn001() {
    println("fn001()")
}