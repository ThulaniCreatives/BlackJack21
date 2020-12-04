package com.example.blackjack21.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_table")
data class Player(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "playerName") var playerName: String,
    @ColumnInfo(name = "cardValueOne") val cardValueOne: Int=0,
    @ColumnInfo(name = "cardValueTwo") val cardValueTwo: Int=0,
    @ColumnInfo(name = "cardValueThree") var cardValueThree: Int=0,
    @ColumnInfo(name = "cardValueFour") val cardValueFour: Int=0,
    @ColumnInfo(name = "cardValueFive") val cardValueFive: Int=0,
    @ColumnInfo(name = "cardValueSix") val cardValueSix: Int=0,
    @ColumnInfo(name = "Score") val Score: Int=0

)
