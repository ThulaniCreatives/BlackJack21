package com.example.blackjack21.repository

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.blackjack21.entity.Player
import com.example.blackjack21.entity.PlayerDao
import com.example.blackjack21.entity.PlayerDatabase
import kotlinx.coroutines.launch

open class  PlayerViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PlayerRepository

    init {
        val playerDao = PlayerDatabase
            .getDatabase(application, viewModelScope)
            .playerDao()
        repository = PlayerRepository(playerDao)
    }


    fun getPlayer(): LiveData<List<Player>> {
        return repository.getPlayer()
    }

    fun getPlayers(name:String): LiveData<List<Player>> {
        return repository.getPlayerByName(name)
    }

    fun insertPlayer(player: Player) = viewModelScope.launch {
        repository.insertPlayer(player)
    }
    fun updatePlayer(player: Player) = viewModelScope.launch {
        repository.updatePlayer(player)
    }

    fun deletePlayer() = viewModelScope.launch {
        repository.DeleteAll()
    }
    fun updateByPlayerName(image:Int, playerName:String) = viewModelScope.launch {
        repository.updateByPlayerName(image, playerName)
    }
    fun updateByPlayerNameFour(image:Int, playerName:String) = viewModelScope.launch {
        repository.updateByPlayerNameFour(image, playerName)
    }
    fun updateByPlayerNameFive(image:Int, playerName:String) = viewModelScope.launch {
        repository.updateByPlayerNameFive(image, playerName)
    }
    fun updateByPlayerNameSix(image:Int, playerName:String) = viewModelScope.launch {
        repository.updateByPlayerNameSix(image, playerName)
    }



}