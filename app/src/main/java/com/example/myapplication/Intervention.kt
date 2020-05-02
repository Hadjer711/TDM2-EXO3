package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity(tableName = "interventions")
data class Intervention (
    @PrimaryKey (autoGenerate = true) var id: Int,
    @ColumnInfo(name="numero") var num:String,
    @ColumnInfo(name="plombier") var  nom:String,
    @ColumnInfo(name="type") var type:String,
    @ColumnInfo(name="date")  var date :Date){


    constructor():this(0,"","","",Date()){

    }




}