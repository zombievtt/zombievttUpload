package com.example.ex2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//Todo: 感知元件
class FruitViewModel() : ViewModel() {
    val originNum = MutableLiveData<String>()
    init {
        originNum.value = "0"
        //Todo: 建構子執行完後執行這裡
    }
}