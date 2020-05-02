package com.example.myapplication

import androidx.room.*
import java.util.*


@Dao
public interface InterventionDAO {
    @Query("SELECT * FROM interventions")
    fun getIntervs(): MutableList<Intervention>

    @Query("SELECT * FROM interventions WHERE id = :num")
    fun getInterv(num: Int): List<Intervention>

    @Query("SELECT * FROM interventions WHERE date = :date")
    fun getByDate(date: Date): MutableList<Intervention>

    @Insert
    fun ajouter(intervention : Intervention)

    @Update
    fun modifier(intervention : Intervention)

    @Delete
    fun supprimer(intervention : Intervention)
}