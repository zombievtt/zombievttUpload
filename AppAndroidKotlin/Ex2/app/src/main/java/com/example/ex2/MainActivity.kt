package com.example.ex2

import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ex2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val reqName = 15
    private lateinit var fruitViewModel: FruitViewModel //Todo: 感知元件
    private lateinit var binding : ActivityMainBinding //Todo: viewBinding
    val requestName = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val name = result.data?.getStringExtra("Name")
            binding.showName.text = name
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)//Todo: viewBinding
        setContentView(binding.root)//Todo: viewBinding
        enableEdgeToEdge()
        //setContentView(R.layout.activity_main) //Todo: viewBinding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (!binding.number.text.toString().equals("")) {
            val num = binding.number.text.toString().toInt()
            Log.d("MainActivity", "get num.")
        }


        binding.number.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do something before text changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Do something on text changed
            }

            override fun afterTextChanged(s: Editable?) {
                // Do something after text changed
                fruitViewModel.originNum.value = s.toString();//Todo: 感知元件
            }
        })

        //Todo: 感知元件
        fruitViewModel = ViewModelProvider(this).get(FruitViewModel::class.java)
        fruitViewModel.originNum.observe(this, Observer { origin ->
            binding.textView.text = origin

        })
        //Todo: ViewModel Variable = new value -> UI.observe = ViewModel Variable,



        val s1 = getString(R.string.numInit)
        val okListener = object : OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                println("okListener 監聽到Click事件了")
            }

        }
        AlertDialog.Builder(this)
            .setTitle("title")
            .setMessage("運行到這裡了${s1}")
            .setPositiveButton("OK", okListener)
            .setNeutralButton("mid button", {
                dialog, which ->
                println("監聽到中性按鈕Click事件了")
            })
            .setNegativeButton("cancel", {dialog, which ->
                println("監聽到cancel按鈕Click事件了")
                println("------------------------")
            })
            .show()


    }

    fun SetName(viwe : View) {
        getSharedPreferences("1111", MODE_PRIVATE)
            .edit()
            .putString("AAAA", "BBBB")
            .apply()


        val intent = Intent(this, SetNameActivity::class.java)
        requestName.launch(intent)

    }
}