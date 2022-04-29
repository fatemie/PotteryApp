package com.example.potteryapp.repository

import android.content.Context
import com.example.potteryapp.database.AppDatabase
import com.example.potteryapp.database.FormulaDao
import com.example.potteryapp.model.Formula

object FormulasRepository {
    var db: AppDatabase?=null
    var dao: FormulaDao?=null

    fun initDB(context: Context){
        db= AppDatabase.getAppDatabase(context)
        dao=db?.formulaDao()
    }

    fun insertFormula( formula: Formula){
        dao?.insertAll(formula )
    }

    fun getAllFormulas() : List<Formula>? {
        return dao?.getAllFormulas()
    }
}