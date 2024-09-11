package com.example.design

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.design.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext
import kotlin.math.log

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val TAG = MainActivity::class.java.simpleName
    private val getCache: Intent
        get() {
        val cache = Intent(this, CacheService::class.java)
        return cache
    }
    private lateinit var viewModel: MyViewModel //Todo: ViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    val Tag = MainActivity::class.java.simpleName
    val job = Job() + Dispatchers.IO//Todo: Coroutine.Default, IO, Main, onConfined
    val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { success ->
        if (success) {
            takePhoto()
        } else {
            Snackbar.make(binding.root,"沒有相機權限", Snackbar.LENGTH_LONG).show()

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }

//        //Todo: Json, Gson
//        launch {//Todo: Coroutine.
//            val json = URL("https://api.jsonserve.com/pcLzBT").readText()
//            Log.d(Tag, "onCreate: $json")
//            //myJsonParse(json)
//            val wordsCollection = Gson().fromJson(json, MyJsonClass::class.java)
//            for (w in wordsCollection.words)
//            {
//                Log.d(Tag, "onCreate: ${w.name}, ${w.means}, ${w.star}, ${w.difficulty}")
//            }
//        }

        //Todo: ViewModel
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel.readJson()

        val names = listOf<String>("AAA", "BBB", "CCC", "DDD", "EEE", "FFF",
            "GGG", "HHH", "III", "JJJ", "KKK", "LLL", "MMM", "NNN", "OOO",
            "PPP", "QQQ", "RRR", "SSS", "TTT", "UUU", "VVV", "WWW", "XXX",
            "YYY", "ZZZ")
        var recycler = binding.contentView.recycler
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this)
        //recycler.adapter = NameAdapter(names)
        viewModel.words.observe(this){ words ->
            recycler.adapter = WordAdapter(words)
        }

        //Todo: spinner
        val nameAdapter = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, names)
        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.contentView.spinner.adapter = nameAdapter
        binding.contentView.spinner.prompt = "choose name"




    }

    //Todo: Json
    private fun myJsonParse(json:String) {
        val jsonObject = JSONObject(json)
        val array = jsonObject.getJSONArray("words")
        for (i in 0 .. array.length() - 1)
        {
            val w = array.getJSONObject(i).getString("name")
            val mean = array.getJSONObject(i).getString("means")
            Log.d(Tag, "onCreate: $w : $mean")

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true //Todo: true 已經把事件完成了
            R.id.action_camera -> {

                //Todo: 相機權限取得過了
                if ( checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED ) {
                    takePhoto()
                } else {
                    requestPermission.launch(Manifest.permission.CAMERA)
                }

                true
            }
            R.id.action_test -> {
                Intent(this, NewsActivity::class.java).also {
                    startActivity(it)
                }
                true
            }
            R.id.action_service -> {
                val cache = getCache
                startService(cache)
                true
            }
            R.id.action_service_stop -> {
                val cache = getCache
                stopService(cache)
                Log.d(TAG, "onOptionsItemSelected: stopService()")

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStop() {
        super.onStop()
        stopService(getCache)
    }

    private fun takePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(intent)
    }
    //Todo: CoroutineScope.
    override val coroutineContext: CoroutineContext
        get() = job


}