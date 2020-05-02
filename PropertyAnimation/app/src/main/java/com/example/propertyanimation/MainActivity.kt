package com.example.propertyanimation

import android.animation.*
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.animation.doOnRepeat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var star: ImageView

    lateinit var showerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        star = findViewById(R.id.star)
        flOuter.setOnClickListener(View.OnClickListener {

                shower()

        })
    }


    private fun shower(){

            val container = star.parent as ViewGroup
            val containerW = container.width
            val containerH = container.height
            var starW: Float = star.width.toFloat()
            var starH: Float = star.height.toFloat()

            val newStar = AppCompatImageView(this)
            newStar.setImageResource(R.drawable.ic_star)
            newStar.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            container.addView(newStar)

            newStar.scaleX = Math.random().toFloat() * 1.5f + .1f
            newStar.scaleY = newStar.scaleX
            starW *= newStar.scaleX
            starH *= newStar.scaleY

            newStar.translationX = Math.random().toFloat() * containerW - starW / 2

            val mover =
                ObjectAnimator.ofFloat(newStar, View.TRANSLATION_Y, -starH, containerH + starH)
            mover.interpolator = AccelerateInterpolator(1f)
            val rotator =
                ObjectAnimator.ofFloat(newStar, View.ROTATION, (Math.random() * 1080).toFloat())
            rotator.interpolator = LinearInterpolator()

            val set = AnimatorSet()
            set.playTogether(mover, rotator)
            set.duration = (Math.random() * 1500 + 500).toLong()


            set.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                        container.removeView(newStar)

                }
            })
            set.start()

    }
}
