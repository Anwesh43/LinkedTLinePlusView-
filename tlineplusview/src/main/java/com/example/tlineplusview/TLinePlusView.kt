package com.example.tlineplusview

import android.view.View
import android.view.MotionEvent
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.app.Activity
import android.content.Context

val parts : Int = 4
val scGap : Float = 0.02f / parts
val strokeFactor : Int = 90
val sizeFactor : Float = 3.9f
val delay : Long = 20
val colors : Array<Int> = arrayOf(
    "#F44336",
    "#009688",
    "#4CAF50",
    "#FF9800",
    "#2196F3"
).map {
    Color.parseColor(it)
}.toTypedArray()
val backColor : Int = Color.parseColor("#BDBDBD")
val deg : Float = 180f
val gap : Float = 90f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawTLinePlus(scale : Float, w : Float, h : Float, paint : Paint) {
    val sf : Float = scale.sinify()
    val size : Float = Math.min(w, h) / sizeFactor
    save()
    translate(w / 2, h / 2)
    rotate(deg * sf.divideScale(2, parts))
    for (j in 0..1) {
        save()
        scale(1f, 1f - 2 * j)
        translate(0f, (h / 2 - paint.strokeWidth) * sf.divideScale(1, parts))
        rotate(deg * sf.divideScale(1, parts))
        for (k in 0..2) {
            save()
            rotate(90f * k)
            drawLine(0f, 0f, -size * sf.divideScale(0, parts), 0f, paint)
            restore()
        }
        restore()
    }
    restore()
}

fun Canvas.drawTLPNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = colors[i]
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    drawTLinePlus(scale, w, h, paint)
}

class TLinePlusView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}