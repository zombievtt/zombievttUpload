package com.example.eatsnake

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.fixedRateTimer
import kotlin.random.Random

class SnakeViewModel : ViewModel() {
    val body = MutableLiveData<List<Position>>()
    val apple = MutableLiveData<Position>()
    var score = MutableLiveData<Int>()
    var point : Int = 0
    var gameStatus = MutableLiveData<GameStatus>()
    private val snakeBody = mutableListOf<Position>()
    private var direction = Direction.Left
    private lateinit var posApple : Position
    fun start() {
        score.postValue(point)
        snakeBody.apply {
            add(Position(10, 10))
            add(Position(11, 10))
            add(Position(12, 10))
            add(Position(13, 10))

        }.also{
            body.value = it
        }
        generateApple()
        fixedRateTimer("timer", true, 500, 500) {
            var pos = snakeBody.first().copy().apply {
                when (direction) {
                    Direction.Left -> x--
                    Direction.Right -> x++
                    Direction.Up -> y--
                    Direction.Down -> y++
                }

                //Todo 當撞到自己或撞牆時 this就是Pos
                if (snakeBody.contains(this) || x < 0 || y < 0 || x >= 20 || y >= 20) {
                    cancel() //Todo: 計時器取消
                    gameStatus.postValue(GameStatus.GAME_OVER)
                }
            }
            snakeBody.add(0, pos)
            if (pos != posApple) {
                snakeBody.removeLast()
            } else {
                point+=100
                score.postValue(point)
                generateApple()
            }

            body.postValue(snakeBody)
        }
    }

    fun generateApple() {
//        do {
//            posApple = Position(Random.nextInt(20), Random.nextInt(20))
//
//        } while(snakeBody.contains(posApple))
        val allPos = mutableListOf<Position>()
        for (i in 0..19) {
            for (j in  0.. 19) {
                allPos.add(Position(i, j))
            }
        }
        allPos.removeAll(snakeBody)
        allPos.shuffle() //Todo: 打亂
        posApple = allPos.get(0)
        apple.postValue(posApple)
    }

    fun reset() {

    }

    fun move(dir:Direction) {
        this.direction = dir
    }
}

data class Position(var x : Int, var y : Int)

enum class Direction {
    Up, Down, Left, Right
}

enum class GameStatus {
    ONGOING, GAME_OVER
}