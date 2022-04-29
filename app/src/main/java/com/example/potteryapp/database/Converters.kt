package com.example.potteryapp.database


import androidx.room.TypeConverter
import com.example.potteryapp.model.Item

class Converters {
    @TypeConverter
    fun stringFromInfoList(items: List<Item>): String {
        var str = ""
        for (item in items) {
            str += item.id.toString() + "," + item.name + "," + item.amount + "," + item.description + "-"
        }
        return str
    }

    @TypeConverter
    fun stringToInfoList(itemsString: String): List<Item> {
        var list = arrayListOf<Item>()
        var strs = itemsString.split('-')
        for (str in strs) {
            if (str.isNullOrBlank())
                break
            var items = str.split(',')
            list.add(Item(items[0].toInt(), items[1], items[2].toInt(), items[3]))
        }
        return list
    }
}