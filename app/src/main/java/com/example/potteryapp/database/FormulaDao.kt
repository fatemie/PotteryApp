package com.example.potteryapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.potteryapp.model.Formula

@Dao
interface FormulaDao {

    @Insert
    fun insertAll(vararg formula: Formula)

    @Query("SELECT * FROM Formula")
    fun getAllFormulas(): List<Formula>
}