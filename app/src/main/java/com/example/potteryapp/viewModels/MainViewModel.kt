package com.example.potteryapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.potteryapp.model.Formula

class MainViewModel(app: Application) : AndroidViewModel(app) {
    var formulas = listOf<Formula>()
}