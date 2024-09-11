package com.example.approom

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.approom.data.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Todo: Room
        val database = Room.databaseBuilder(this,
            GameDatabase::class.java, "game.db")
            .build()
        val record = Record("Mary", 8)
        Thread() {
            database.recordDao().insert(record)
            val list = database.recordDao().getAll()
            for (r in list)
            {
                println("${r.nickName}, ${r.counter}, ${r.id}")
            }
        }.start()

    }
}