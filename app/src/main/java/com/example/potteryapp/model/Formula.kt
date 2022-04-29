package com.example.potteryapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Formula(@PrimaryKey val id: Int, val name : String, var itemCount : Int,
                   //var Compounds : List<Item>
     )