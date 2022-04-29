package com.example.potteryapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.potteryapp.model.Formula


@Database(entities = [Formula::class], version = 1 )
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun formulaDao(): FormulaDao

    companion object{
        var instance : AppDatabase?=null
        fun getAppDatabase(context: Context):AppDatabase?{
            if (instance==null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "PotteryDB"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }
    }
}