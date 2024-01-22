package com.msedith.helloworld

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KMutableProperty0

class MainActivity : AppCompatActivity() {

    // Flags to track button states
    private var isSchoolButtonClicked = false
    private var isMajorButtonClicked = false
    private var isHometownButtonClicked = false
    private var isFunFactButtonClicked = false

    // Define the colors
    private val defaultColor by lazy { resources.getColor(R.color.button_default, theme) }
    private val clickedColor by lazy { resources.getColor(R.color.button_clicked, theme) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.helloButton)
        button.setOnClickListener {
            button.visibility = View.INVISIBLE
            val toast = Toast.makeText(this, "Hi there! Here's a little more info about me", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
            toast.show()

            Handler(Looper.getMainLooper()).postDelayed({
                // Make the button visible again after the Toast message disappears
                button.visibility = View.VISIBLE
            }, 3000)
        }

        // Initialize buttons
        val schoolButton: Button = findViewById(R.id.schoolButton)
        val majorButton: Button = findViewById(R.id.majorButton)
        val hometownButton: Button = findViewById(R.id.hometownButton)
        val funFactButton: Button = findViewById(R.id.funFactButton)

        // Get references to the TextViews
        val schoolAnswer: TextView = findViewById(R.id.schoolAnswer)
        val majorAnswer: TextView = findViewById(R.id.majorAnswer)
        val hometownAnswer: TextView = findViewById(R.id.hometownAnswer)
        val funFactAnswer: TextView = findViewById(R.id.funFactAnswer)

        // Set up click listeners for each button
        findViewById<Button>(R.id.schoolButton).setOnClickListener {
            toggleAnswerVisibility(schoolAnswer)
            toggleButtonColor(it as Button, ::isSchoolButtonClicked)
        }
        findViewById<Button>(R.id.majorButton).setOnClickListener {
            toggleAnswerVisibility(majorAnswer)
            toggleButtonColor(it as Button, ::isMajorButtonClicked)
        }
        findViewById<Button>(R.id.hometownButton).setOnClickListener {
            toggleAnswerVisibility(hometownAnswer)
            toggleButtonColor(it as Button, ::isHometownButtonClicked)
        }
        findViewById<Button>(R.id.funFactButton).setOnClickListener {
            toggleAnswerVisibility(funFactAnswer)
            toggleButtonColor(it as Button, ::isFunFactButtonClicked)
        }
    }

    private fun toggleAnswerVisibility(answerTextView: TextView) {
        if (answerTextView.visibility == View.GONE) {
            // Start the animation to reveal the answer
            val anim = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
            answerTextView.visibility = View.VISIBLE
            answerTextView.startAnimation(anim)
        } else {
            // Start the animation to hide the answer
            val anim = AnimationUtils.loadAnimation(this, android.R.anim.fade_out)
            anim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    answerTextView.visibility = View.GONE
                }
                override fun onAnimationRepeat(animation: Animation) {}
            })
            answerTextView.startAnimation(anim)
        }
    }
    private fun toggleButtonColor(button: Button, flag: KMutableProperty0<Boolean>) {
        flag.set(!flag.get()) // Toggle the boolean flag
        button.setBackgroundColor(if (flag.get()) clickedColor else defaultColor)
    }
}