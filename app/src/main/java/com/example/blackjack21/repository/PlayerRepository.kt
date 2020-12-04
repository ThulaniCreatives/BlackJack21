package com.example.blackjack21.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.blackjack21.entity.Player
import com.example.blackjack21.entity.PlayerDao


class PlayerRepository (private val playerDao: PlayerDao) {


    fun getPlayer(): LiveData<List<Player>> {
        return playerDao.getAllPlayer()
    }
    suspend fun insertPlayer(player: Player){
        playerDao.insertPlayer(player)
    }
    suspend fun updatePlayer(player: Player){
        playerDao.updatePlayer(player)
    }

    suspend  fun DeleteAll() {

        playerDao.deleteAllPlayer()
        //playerDao.deleteAllCurrent()

        Log.d("DeletedAllData", "prePopulateDatabase: ")
    }
    fun  getPlayerByName(playerName:String): LiveData<List<Player>>  {
        return playerDao.getPlayer(playerName)
    }
    suspend fun  updateByPlayerName(image:Int, playerName:String){
        playerDao.updateByPlayerName(image,playerName)
    }
    suspend fun  updateByPlayerNameFour(image:Int, playerName:String){
        playerDao.updateByPlayerNameFour(image,playerName)
    }
    suspend fun  updateByPlayerNameFive(image:Int, playerName:String){
        playerDao.updateByPlayerNameFive(image,playerName)
    }
    suspend fun  updateByPlayerNameSix(image:Int, playerName:String){
        playerDao.updateByPlayerNameSix(image,playerName)
    }








}