package com.example.blackjack21

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blackjack21.adaptor.DeckAdaptor
import com.example.blackjack21.adaptor.MainRecyclerviewAdaptor
import com.example.blackjack21.entity.Player
import com.example.blackjack21.model.AllPlayers
import com.example.blackjack21.model.DeckModel
import com.example.blackjack21.repository.PlayerViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.parent_recycler_view.*
import kotlinx.android.synthetic.main.parent_recycler_view.floatingActionButton2
import kotlinx.android.synthetic.main.players_row.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var deckAdaptor: DeckAdaptor
    val item = ArrayList<DeckModel>()
    lateinit var selectPlace: String

    //modification
    private var mainRecyclerviewAdaptor: MainRecyclerviewAdaptor? = null
    private var mainRecyclerview: RecyclerView? = null

    //Room
    private lateinit var playerViewModel: PlayerViewModel
    private lateinit var player: Player


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parent_recycler_view)


        playerViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)
        playerViewModel.deletePlayer()

        var dealerScore = 0
        var isClicked = 0
        val images = loadCard()

        floatingActionButton2.setOnClickListener {

            textViewDealerScore.visibility = View.VISIBLE
            textViewAppname.visibility = View.GONE
            floatingActionButton2.text = "Hit"

            //--------------------------Dealer
            images.shuffle()
            val dealerIndices1: Int = (images.indices).random()
            images.shuffle()
            val dealerIndices2: Int = (images.indices).random()

            val dealerValue1: Int = images[dealerIndices1]
            val dealerValue2: Int = images[dealerIndices2]

            //remove card from list
            images.removeAt(dealerIndices1)
            images.removeAt(dealerIndices1)
            ////////////////////////
            val dealer1 = DeckModel(dealerValue1)
            val dealerheadenValue = DeckModel(dealerValue2)
            val dealer2 = DeckModel(R.drawable.red_back)

            //set images
            val dealerFirstBet: List<DeckModel> = listOf(
                dealer1, dealer2
            )

            //-------------------------Player 1----------------------------------------
            images.shuffle()
            var player1Indices1: Int = (images.indices).random()
            val player1Indices2: Int = (images.indices).random()
            images.shuffle()
            var player1Value1: Int = images[player1Indices1]
            val player1Value2: Int = images[player1Indices2]


            ////////////////////////
            val playerOne_1 = DeckModel(player1Value1)
            val playerOne_2 = DeckModel(player1Value2)
            //remove card from list
            images.removeAt(player1Indices1)
            images.removeAt(player1Indices2)


            //set images
            val playerOneFirstBet: List<DeckModel> = mutableListOf(
                playerOne_1, playerOne_2

            )
            //-----------------------------------------------------------------

            var players: List<AllPlayers> = mutableListOf(
                AllPlayers(dealerFirstBet),
                AllPlayers(playerOneFirstBet)
            )

            //Delete players
            //playerViewModel.deletePlayer()
            //--------------------- Get Players

            var allList: List<AllPlayers> = mutableListOf()
            playerViewModel.getPlayer().observe(this, Observer<List<Player>> { players ->

                var playerOneFirstBet1: List<DeckModel> = mutableListOf()
                var dealerOneFirstBet1: List<DeckModel> = mutableListOf()
                var index = 0

                for (playerValue in players) {

                    if (index == 0) {
                        val values = players[index].cardValueOne
                        val values1 = players[index].cardValueTwo

                        val dealerOne_1 = DeckModel(values)
                        val dealerOne_2 = DeckModel(dealer2.card)//manually hide second card
                        var dealerOne_3 = DeckModel(values1)
                        dealerOneFirstBet1 = mutableListOf(
                            dealerOne_1, dealerOne_2
                        )

                        val DealerScore = computeScore(values, values1)
                        Log.d("Test", "DealerScore: $DealerScore")

                        index++
                    } else if (index == 1) {

                        val values = players[index].cardValueOne
                        val values1 = players[index].cardValueTwo
                        val values2 = players[index].cardValueThree
                        val values3 = players[index].cardValueFour
                        val values4 = players[index].cardValueFive
                        val values5 = players[index].cardValueSix

                        val PlayerScore = computeScore(values, values1)
                        Log.d("Test", "PlayScore: $PlayerScore")

                        val playerOne = DeckModel(values)
                        val playerTwo = DeckModel(values1)
                        val playerThree = DeckModel(values2)
                        val playerFour = DeckModel(values3)
                        val playerFive = DeckModel(values4)
                        val playerSix = DeckModel(values5)
                        playerOneFirstBet1 = mutableListOf(
                            playerOne, playerTwo, playerThree, playerFour, playerFive, playerSix
                        )
                    }


                    allList = mutableListOf(
                        AllPlayers(dealerOneFirstBet1), AllPlayers(playerOneFirstBet1)

                    )

                    setPlayers(allList)
                }

            })

            //---------------------------
            textViewDealerScore.text = ("$dealerScore")

            if (isClicked == 0) {

                val dataFavarite = Player("Dealer", dealer1.card, dealerheadenValue.card)
                playerViewModel.insertPlayer(dataFavarite)
                //Toast.makeText(this,"Favorite place successfully added",Toast.LENGTH_SHORT).show()

                //Add player
                val player1 = Player("Thulani", playerOne_1.card, playerOne_2.card)
                playerViewModel.insertPlayer(player1)

                //setPlayers(players)
                isClicked++
            } else if (isClicked == 1) {
                images.shuffle()
                player1Indices1 = (images.indices).random()
                player1Value1 = images[player1Indices1]

                playerViewModel.updateByPlayerNameFour(player1Value1, "Thulani")
                parent_rv.adapter?.notifyDataSetChanged()
                isClicked++
            } else if (isClicked == 2) {
                images.shuffle()
                player1Indices1 = (images.indices).random()
                player1Value1 = images[player1Indices1]

                playerViewModel.updateByPlayerNameFive(player1Value1, "Thulani")
                parent_rv.adapter?.notifyDataSetChanged()
                isClicked++
            } else if (isClicked == 3) {
                images.shuffle()
                player1Indices1 = (images.indices).random()
                player1Value1 = images[player1Indices1]

                playerViewModel.updateByPlayerNameSix(player1Value1, "Thulani")
                parent_rv.adapter?.notifyDataSetChanged()
                isClicked++
            }

        }

    }
private fun computeScore(firstValue:Int ,secondValue:Int):Int{

    //-------------------convert values to actual and do calculations

    val cardName = resources.getResourceName(firstValue)
        .replace("com.example.blackjack21:drawable/", "")
    val cardName2 = resources.getResourceName(secondValue)
        .replace("com.example.blackjack21:drawable/", "")
    val selectedCardValue2 = getCardValue(cardName)
    val selectedCardValue = getCardValue(cardName2)
    //dealerScore = selectedCardValue + selectedCardValue2
    var  playerScore =0
    //-----Winner Algorithm
    if (selectedCardValue2 == 0 || selectedCardValue == 0) {
        Log.d("Test", "Ace Detected")
        if (selectedCardValue2 > selectedCardValue) {
            playerScore = selectedCardValue2 + 11
            if ( playerScore  > 21) {
                playerScore = selectedCardValue2 + 1
            }
        } else {
            playerScore = selectedCardValue + 11
            if ( playerScore  > 21) {
                playerScore = selectedCardValue + 1
            }
        }
    } else {
        playerScore = selectedCardValue + selectedCardValue2
        if (( playerScore ) < 21) {
            //Log.d("Test", "save score  to db")
        } else {
            playerScore = 0
            //Dealer Lost delete data

        }
    }
    Log.d("Test", " $cardName2 $cardName  valu1 =$selectedCardValue2 value =$selectedCardValue Score= $playerScore ")

    return playerScore
}
    public fun setPlayers(allPlayers: List<AllPlayers>) {
        parent_rv.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        parent_rv.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        mainRecyclerviewAdaptor = MainRecyclerviewAdaptor(this, allPlayers)
        parent_rv.adapter = mainRecyclerviewAdaptor
        parent_rv.adapter?.notifyDataSetChanged()

    }

    private fun getCardValue(resourceName: String): Int = when {
        resourceName.contains("twoc") || resourceName.contains("twoh") || resourceName.contains("twos") ||
                resourceName.contains("twod") -> 2
        resourceName.contains("threec") || resourceName.contains("threeh") ||
                resourceName.contains("threes") || resourceName.contains("threed") -> 3
        resourceName.contains("fourc") || resourceName.contains("fourh")
                || resourceName.contains("fours") || resourceName.contains("fourd") -> 4
        resourceName.contains("fivec") || resourceName.contains("fiveh")
                || resourceName.contains("fives") || resourceName.contains("fived") -> 5
        resourceName.contains("sixc") || resourceName.contains("sixh")
                || resourceName.contains("sixs") || resourceName.contains("sixd") -> 6
        resourceName.contains("sevenc") || resourceName.contains("sevenh")
                || resourceName.contains("sevens") || resourceName.contains("sevend") -> 7
        resourceName.contains("eightc") || resourceName.contains("eighth")
                || resourceName.contains("eights") || resourceName.contains("eightd") -> 8
        resourceName.contains("ninec") || resourceName.contains("nineh")
                || resourceName.contains("nines") || resourceName.contains("nined") -> 9
        resourceName.contains("tenc") || resourceName.contains("tenh")
                || resourceName.contains("tens") || resourceName.contains("tend") -> 10
        resourceName.contains("jc") || resourceName.contains("jh")
                || resourceName.contains("js") || resourceName.contains("jd") -> 10
        resourceName.contains("kc") || resourceName.contains("kh")
                || resourceName.contains("ks") || resourceName.contains("kd") -> 10
        resourceName.contains("qc") || resourceName.contains("qh")
                || resourceName.contains("qs") || resourceName.contains("qd") -> 10
        else ->
            0
    }

    private fun loadCard(): MutableList<Int> {
        val images: MutableList<Int> = mutableListOf(
            R.drawable.ac,
            R.drawable.ad,
            R.drawable.ah,
            R.drawable.`as`,
            R.drawable.twoc,
            R.drawable.twod,
            R.drawable.twoh,
            R.drawable.twos,
            R.drawable.threec,
            R.drawable.threed,
            R.drawable.threeh,
            R.drawable.threes,
            R.drawable.fourc,
            R.drawable.fourd,
            R.drawable.fourh,
            R.drawable.fours,
            R.drawable.fivec,
            R.drawable.fived,
            R.drawable.fiveh,
            R.drawable.fives,
            R.drawable.sixc,
            R.drawable.sixd,
            R.drawable.sixh,
            R.drawable.sixs,
            R.drawable.sevenc,
            R.drawable.sevend,
            R.drawable.sevenh,
            R.drawable.sevens,
            R.drawable.eightc,
            R.drawable.eightd,
            R.drawable.eighth,
            R.drawable.eights,
            R.drawable.ninec,
            R.drawable.nined,
            R.drawable.nineh,
            R.drawable.nines,
            R.drawable.tenc,
            R.drawable.tend,
            R.drawable.tenh,
            R.drawable.tens,
            R.drawable.jc,
            R.drawable.jd,
            R.drawable.jh,
            R.drawable.js,
            R.drawable.kc,
            R.drawable.kd,
            R.drawable.kh,
            R.drawable.ks,
            R.drawable.qc,
            R.drawable.qd,
            R.drawable.qh,
            R.drawable.qs,

            )

        return images

    }
}