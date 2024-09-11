package com.example.ex1

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import java.util.Locale

class TTSActivity : AppCompatActivity() {
    lateinit var btnMakeSpeech : Button
    lateinit var btnSpeechSettings : Button
    lateinit var TTSObj : TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tts)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //語音合成
        btnMakeSpeech = findViewById(R.id.btnMakeSpeech)
        initTTS()
        btnMakeSpeech.setOnClickListener {
            TTSSpeech("要朗讀的文字如下今天有一架直升機飛過來") //TTS 朗讀
        }

        //語音設置
        btnSpeechSettings = findViewById(R.id.btnSpeechSettings)
        btnSpeechSettings.setOnClickListener {
            TTSSettings()
        }

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    fun initTTS() {
        TTSObj = TextToSpeech(this, TextToSpeech.OnInitListener {
           if (it == TextToSpeech.SUCCESS) {
               val i = TTSObj.setLanguage(Locale.CHINESE)
               if (i == TextToSpeech.LANG_MISSING_DATA ||
                   i == TextToSpeech.LANG_NOT_SUPPORTED)
               {
                   TTSObj.setSpeechRate(1.0f)
                   val builder = AlertDialog.Builder(this)
                   builder.setMessage("設置中文語音失敗")
                   builder.show()
               } else {

               }
           } else {
               val builder = AlertDialog.Builder(this)
               builder.setMessage("TTS 初始化失敗")
               builder.show()
           }
        })
    }
    fun TTSSpeech(ReadyToSpeech : String)
    {
        if (ReadyToSpeech != "") {
            TTSObj.speak(ReadyToSpeech, TextToSpeech.QUEUE_ADD, null)
        }
    }

    fun TTSSettings() {
        var intent = Intent("com.android.settings.TTS_SETTINGS")
        startActivity(intent)
    }
}