package com.example.blackjack21.entity

import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import android.content.Context
import androidx.room.Room

@Database(version = 1, entities = [Player::class], exportSchema = false)
abstract class PlayerDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao

    private class PlayerDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val playDao = database.playerDao()
                    //prePopulateDatabase(foresDao )//forecast
                    //addCurrentData(foresDao )//current
                    DeleteAll(playDao)
                }
            }
        }
        private suspend fun prePopulateDatabase(forecastDao: PlayerDao) {

            //val forecast = Forecast(0,"2020-08-10",30.0,"htulani",1,90)
            //forecastDao.insertAllForecast(forecast)
            //Log.d("works", "prePopulateDatabase: ")
        }

        private suspend fun addCurrentData(forecastDao: PlayerDao) {

            // val currentWeather = CurrentWeather(0,12.2,45.0,67.0,"cold","cold","hth")
            // forecastDao.insertCurrentWeather(currentWeather)
            // Log.d("works", "prePopulateDatabase: ")
        }
        private suspend fun DeleteAll(forecastDao: PlayerDao) {
            forecastDao.deleteAllPlayer()
        }



    }

    companion object {

        @Volatile
        private var INSTANCE: PlayerDatabase? = null

        fun getDatabase(context: Context, coroutineScope: CoroutineScope): PlayerDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    PlayerDatabase::class.java,
                    "player_database")
                    .addCallback(PlayerDatabaseCallback(coroutineScope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}