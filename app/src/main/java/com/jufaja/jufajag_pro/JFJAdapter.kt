package com.jufaja.jufajag_pro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jufaja.jufajag_pro.colls.Pils
import kotlinx.android.synthetic.main.activity_addtile.view.*
import kotlinx.android.synthetic.main.item_jfj.view.*


class JFJAdapter (val context: Context, val kils: List<Pils>) :
    RecyclerView.Adapter<JFJAdapter.Viewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_jfj, parent, false)
        return Viewholder(view)
    }

    override fun getItemCount() = kils.size

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bind(kils[position])


    }

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tils: Pils) {
            //itemView.txNick.text = tils.user?.nickname
            itemView.tvImageJFJ.text = tils.code
            //itemView.txId.text = tils.id
            //itemView.txValue.text = tils.value.toString()
            //itemView.txNo.text = tils.number.toString()
            if (tils.color == "amber")
                itemView.tvImageJFJ.setTextColor(context.getResources().getColor(R.color.amber))
            if (tils.color == "blue")
                itemView.tvImageJFJ.setTextColor(context.getResources().getColor(R.color.blue))
            if (tils.color == "red")
                itemView.tvImageJFJ.setTextColor(context.getResources().getColor(R.color.red))
            if (tils.color == "grey")
                itemView.tvImageJFJ.setTextColor(context.getResources().getColor(R.color.grey))
            if (tils.color == "green")
                itemView.tvImageJFJ.setTextColor(context.getResources().getColor(R.color.green))
            if (tils.color == "yelow")
                itemView.tvImageJFJ.setTextColor(context.getResources().getColor(R.color.yelow))
            Glide.with(context).load(tils.imageUrl).into(itemView.btnImageJFJ)

        }
    }

}