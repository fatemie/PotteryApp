package com.example.potteryapp.database

import androidx.room.*
import com.example.potteryapp.model.Formula

@Dao
interface FormulaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg formula: Formula)

    @Query("SELECT * FROM Formula")
    fun getAllFormulas(): List<Formula>

    @Query("SELECT * FROM Formula WHERE id IN (:id)")
    fun getFormulaWithID(id: Int): Formula

    @Delete
    fun delete(vararg formula: Formula)
}