package com.example.ex2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ex2.databinding.ActivitySetNameBinding

class SetNameActivity : AppCompatActivity() {
    private lateinit var bindingSetName : ActivitySetNameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSetName = ActivitySetNameBinding.inflate(layoutInflater)
        setContentView(bindingSetName.root)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_set_name)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }

    fun SaveName(view: View) {
        val aaaa = getSharedPreferences("1111", MODE_PRIVATE)
            .getString("AAAA", null)

        val temp = bindingSetName.etSaveText.text.toString()
        setResult(RESULT_OK, intent.putExtra("Name", temp))


        finish()

    }
}