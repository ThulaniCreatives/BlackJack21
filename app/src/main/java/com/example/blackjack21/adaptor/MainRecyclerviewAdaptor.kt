package com.example.blackjack21.adaptor

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blackjack21.R
import com.example.blackjack21.model.AllPlayers
import com.example.blackjack21.model.DeckModel
import kotlinx.android.synthetic.main.dealer_cards.view.*
import kotlinx.android.synthetic.main.players_row.view.*

class MainRecyclerviewAdaptor ( val context: Context,private val items: List<AllPlayers>) :
        RecyclerView.Adapter<MainRecyclerviewAdaptor.MainViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            MainRecyclerviewAdaptor.MainViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.players_row, parent, false)
        return MainRecyclerviewAdaptor.MainViewHolder(view)
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

     var playerTitle : TextView
        var mainRecyclerView:RecyclerView
        init {
            playerTitle = itemView.textViewPlayer
            mainRecyclerView = itemView.card_recyclerview
        }

    }

    override fun onBindViewHolder(holder: MainRecyclerviewAdaptor.MainViewHolder, position: Int) {

        var headingText = when(position){
            0-> "Dealer"
            1-> "Thulani"
            2-> "Mike"
            3-> "Sipho"
            4-> "Jane"
            else
            ->"Unkown Player"
        }


        holder.playerTitle.text = headingText
        setCategory(holder.mainRecyclerView , items[position].cardList)
    }

    override fun getItemCount()= items.size

    fun setCategory(recyclerView: RecyclerView , deckValues:List<DeckModel>){

        val cardAdaptor= CardAdaptor(context , deckValues)
        recyclerView.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
        recyclerView.addItemDecoration(ItemDecoration())
        recyclerView.adapter = cardAdaptor




    }
    class ItemDecoration : RecyclerView.ItemDecoration() {

        private val overlapValue = -100
        private val overlap = -100

        override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
        ) {
            outRect.set(0, 0, 0, overlapValue)  // args is : left,top,right,bottom

            val itemPosition = parent.getChildAdapterPosition(view);
            if (itemPosition == 0) {
                outRect.set(0, 0, 0, 0);
            } else {
                outRect.set(overlap, 0, 0, 0);
            }
        }
    }
}