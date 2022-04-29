package com.example.potteryapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.potteryapp.model.Formula
import com.example.potteryapp.repository.FormulasRepository

class MainViewModel(app: Application) : AndroidViewModel(app) {
    var formulas = listOf<Formula>()
    init{
        FormulasRepository.initDB(app.applicationContext)
        formulas = FormulasRepository.getAllFormulas()!!
    }
    fun updateLst(){
        formulas = FormulasRepository.getAllFormulas()!!
    }

    fun calculateTotalAmount(formula: Formula) : Int{
        var totalAmount = 0
        for(item in formula.Compounds){
            totalAmount+= item.amount
        }
        return totalAmount
    }
}