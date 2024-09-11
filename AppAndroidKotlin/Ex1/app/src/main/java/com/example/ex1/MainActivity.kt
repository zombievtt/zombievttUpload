package com.example.ex1

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var mContentView : LinearLayout
    lateinit var mLoadingView : LinearLayout
    private var mShortAnimationDuration: Int= 5000
    lateinit var btnToTTSActivity : Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnToTTSActivity = findViewById(R.id.btnToTTSActivity)
        btnToTTSActivity.setOnClickListener{
            val intent: Intent = Intent(
                this,
                TTSActivity::class.java
            )
            startActivity(intent)
        }
        mContentView = findViewById(R.id.mContentView)
        mContentView.apply {
            alpha = 1f
            visibility = View.VISIBLE
            animate()
                .alpha(0f)
                .setDuration(mShortAnimationDuration.toLong())
                .setListener(null)
        }

        mLoadingView = findViewById(R.id.mLoadingView)
        mLoadingView.alpha = 0f
        mLoadingView.animate()
            .alpha(1f)
            .setDuration(mShortAnimationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
              override fun onAnimationEnd(anmation: Animator) {
                  //mLoadingView.visibility = View.GONE
              }
            })

        btnToTTSActivity = findViewById(R.id.btnToTTSActivity)
        btnToTTSActivity.setOnClickListener {
            startActivity(Intent(this, TTSActivity::class.java))

            // 設置換頁效果
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}