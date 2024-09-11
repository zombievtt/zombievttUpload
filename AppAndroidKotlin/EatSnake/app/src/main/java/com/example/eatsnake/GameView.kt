package com.example.eatsnake

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class GameView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    var snakeBody: List<Position>? = null
    var apple: Position? = null
    var size : Int = 0
    val gap = 3
    private val paint = Paint().apply {
        color = Color.BLACK
    }
    private val paintApple = Paint().apply {
        color = Color.RED
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.run {

            apple?.run {
                val xOriApple : Float = (x*size + gap).toFloat()
                val yOriApple : Float  = (y*size + gap).toFloat()
                val xPlusApple : Float  = ((x+1)*size - gap).toFloat()
                val yPlusApple : Float  = ((y+1)*size - gap).toFloat()
                drawRect(xOriApple, yOriApple, xPlusApple, yPlusApple, paintApple)
            }

            snakeBody?.forEach {
                val xOri : Float = (it.x*size + gap).toFloat()
                val yOri : Float  = (it.y*size + gap).toFloat()
                val xPlus : Float  = ((it.x+1)*size - gap).toFloat()
                val yPlus : Float  = ((it.y+1)*size - gap).toFloat()
                drawRect(xOri, yOri, xPlus, yPlus, paint)
            }
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        size = width / 20
    }
}

