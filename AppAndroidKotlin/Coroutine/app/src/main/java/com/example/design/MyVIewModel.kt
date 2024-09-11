package com.example.design

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

//Todo: 在ViewModel 使用協程
class MyViewModel : ViewModel() {
    val Tag = MyViewModel::class.java.simpleName
    val words = MutableLiveData<List<Word>>()
    fun readJson() {
        viewModelScope.launch(Dispatchers.IO) {
            val json = URL("https://api.jsonserve.com/pcLzBT").readText()
            Log.d(Tag, "onCreate: $json")
            //myJsonParse(json)
            val wordsCollection = Gson().fromJson(json, MyJsonClass::class.java)
            for (w in wordsCollection.words)
            {
                Log.d(Tag, "onCreate: ${w.name}, ${w.means}, ${w.star}, ${w.difficulty}")
            }
            words.postValue(wordsCollection.words) //Todo: 晚一點的時候有機會再把資料設進去
        }

    }
}