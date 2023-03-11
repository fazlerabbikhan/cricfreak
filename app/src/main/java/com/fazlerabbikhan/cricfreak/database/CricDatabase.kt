package com.fazlerabbikhan.cricfreak.database

import android.content.Context
import android.util.Log
import androidx.room.*
import com.fazlerabbikhan.cricfreak.global.Constant
import com.fazlerabbikhan.cricfreak.model.leagues.LeagueData
import com.fazlerabbikhan.cricfreak.model.teams.TeamData

@Database(entities = [LeagueData::class, TeamData::class], version = 14, exportSchema = false)
abstract class CricDatabase : RoomDatabase() {
    abstract fun getCricDao(): CricDao

    companion object {
        @Volatile
        private var INSTANCE: CricDatabase? = null
        fun getDatabase(context: Context): CricDatabase {
            val tempInstance= INSTANCE

            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CricDatabase::class.java,
                    Constant.databaseName,
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                Log.d("database", "getDatabase: Created!")
                return instance
            }
        }
    }
}