package com.example.myapplication


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Intervention::class], version = 1)
@TypeConverters(Converters::class)
abstract class DataBase :RoomDatabase(){
    abstract fun interventionDAO(): InterventionDAO
    companion object {
        @Volatile private var instance: DataBase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDataBase(context).also{ instance = it}
        }
        private fun buildDataBase(context: Context ) =
            Room.databaseBuilder(context.applicationContext ,
                DataBase::class.java ,
                "interv.db").allowMainThreadQueries().build()

            }

        }







