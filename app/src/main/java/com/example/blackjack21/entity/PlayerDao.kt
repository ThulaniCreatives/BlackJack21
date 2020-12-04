package com.example.blackjack21.entity

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PlayerDao {

    @Delete
    suspend fun deletePlayer(player: Player)

    @Update
    suspend fun updatePlayer(player: Player)

    @Query("SELECT * FROM player_table WHERE playerName = :playerName")
    fun getPlayer(playerName: String): LiveData<List<Player>>

    @Query("SELECT * FROM player_table ")
    fun getAllPlayer(): LiveData<List<Player>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(player: Player)

    @Query(" DELETE FROM player_table")
    suspend  fun deleteAllPlayer()

    @Query("update player_table set cardValueThree = :cardValueThree where playerName=:playerName")
    suspend  fun updateByPlayerName(cardValueThree: Int ,playerName:String)

    @Query("update player_table set cardValueFour = :cardValueFour where playerName=:playerName")
    suspend  fun updateByPlayerNameFour(cardValueFour: Int ,playerName:String)

    @Query("update player_table set cardValueFive = :cardValueFive where playerName=:playerName")
    suspend  fun updateByPlayerNameFive(cardValueFive: Int ,playerName:String)

    @Query("update player_table set cardValueSix = :cardValueSix where playerName=:playerName")
    suspend  fun updateByPlayerNameSix(cardValueSix: Int ,playerName:String)

}