package com.example.blackjack21.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blackjack21.R
import com.example.blackjack21.model.DeckModel
import kotlinx.android.synthetic.main.dealer_cards.view.*


class DeckAdaptor(private val items: MutableList<DeckModel>) : RecyclerView.Adapter<DeckAdaptor.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dealer_cards, parent, false)
        return ViewHolder(view)
    }

    fun setListData(data: List<DeckModel>) {
        this.items.addAll(data)
        notifyDataSetChanged()
    }
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val card = items[position]
            holder.bind(card)
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(data: DeckModel) {
          itemView.card_icon.setBackgroundResource(data.card)
        }

    }




}