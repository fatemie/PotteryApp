package com.example.potteryapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.potteryapp.model.Formula
import com.example.potteryapp.model.Item
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

    fun calculateTotalAmount(formula: Formula) : Double {
        var totalAmount = 0.0
        for(item in formula.Compounds){
            totalAmount += item.amount
        }
        return totalAmount
    }

    fun calculateNewAmount(newTotalAmount : Double, lastTotalAmount : Double, amount : Double ):Double{
        return amount/lastTotalAmount * newTotalAmount
    }
    fun createNewFormula(formula: Formula,newTotalAmount : Double ): Formula{
        val lastTotalAmount = calculateTotalAmount(formula)
        var newAmount = 0.0
        var newItemLst = arrayListOf<Item>()
        for (item in formula.Compounds){
            newAmount = calculateNewAmount(newTotalAmount,lastTotalAmount,item.amount)
            var newItem = Item(item.id, item.name, newAmount, item.description)
            newItemLst.add(newItem)
        }
        return Formula(formula.id, formula.name, newItemLst.size, newItemLst)

    }
}