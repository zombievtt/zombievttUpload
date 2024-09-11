package com.example.approom.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecordDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insert(record: Record)

    @Query("select * from Record")
    fun getAll() : List<Record>
}