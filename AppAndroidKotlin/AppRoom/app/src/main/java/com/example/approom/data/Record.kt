package com.example.approom.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//Todo Room
@Entity
class Record(
    @NonNull
    @ColumnInfo(name = "Nick")
    var nickName:String,
    var counter:Int,
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0) {
}