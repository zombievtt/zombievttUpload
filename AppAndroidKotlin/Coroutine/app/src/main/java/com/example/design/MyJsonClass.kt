package com.example.design

data class MyJsonClass(
    val words: List<Word>
)

data class Word(
    val difficulty: Int,
    val means: String,
    val name: String,
    val star: Int
)