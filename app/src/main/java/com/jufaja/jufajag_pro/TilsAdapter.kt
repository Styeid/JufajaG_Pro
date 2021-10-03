package com.jufaja.jufajag_pro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jufaja.jufajag_pro.colls.Tils
import kotlinx.android.synthetic.main.item_tils.view.*

class TilsAdapter( val context: Context, val tils: List<Tils>) :
    RecyclerView.Adapter<TilsAdapter.Viewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_tils, parent, false)
        return Viewholder(view)
    }

    override fun getItemCount() = tils.size

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bind(tils[position])


    }

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tils: Tils) {
            itemView.txNick.text = tils.user?.nickname
            itemView.txCode.text = tils.code
            itemView.txId.text = tils.id
            itemView.txValue.text = tils.value.toString()
            itemView.txNo.text = tils.number.toString()
            Glide.with(context).load(tils.imageUrl).into(itemView.tileImage)

        }
    }

}