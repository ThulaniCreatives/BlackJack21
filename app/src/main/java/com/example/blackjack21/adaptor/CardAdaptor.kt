package com.example.blackjack21.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blackjack21.R
import com.example.blackjack21.model.AllPlayers
import com.example.blackjack21.model.DeckModel
import kotlinx.android.synthetic.main.dealer_cards.view.*
import kotlinx.android.synthetic.main.players_row.view.*

class CardAdaptor  (val context: Context, private val items: List<DeckModel>) :
        RecyclerView.Adapter<CardAdaptor .MainViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAdaptor.MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dealer_cards, parent, false)
        return CardAdaptor.MainViewHolder(view)
    }
    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

       var cardImage :ImageView

       init {
           cardImage = itemView.findViewById(R.id.card_icon) //change it
       }


    }

    override fun onBindViewHolder(holder: CardAdaptor.MainViewHolder, position: Int) {

      holder.cardImage.setBackgroundResource(items[position].card)


    }

    override fun getItemCount()= items.size


}