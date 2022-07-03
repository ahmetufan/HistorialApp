package com.ahmet.HistoryApp.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.model.War
import com.ahmet.HistoryApp.view.WarFragmentDirections
import kotlinx.android.synthetic.main.war_row.view.*

class War_Adaptor(private val modelList: ArrayList<War>) :
    RecyclerView.Adapter<War_Adaptor.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): War_Adaptor.Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.war_row, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: War_Adaptor.Holder, position: Int) {

        holder.itemView.textwarKonum.text = modelList[position].konum
        holder.itemView.textwarTime.text = modelList[position].time
        holder.itemView.texwarName.text = modelList[position].name
        holder.itemView.imageWar.setImageResource(modelList[position].image)


        holder.itemView.btnWarDetay.setOnClickListener {

            val action =
                WarFragmentDirections.actionWarFragmentToWarDetailsActivity(modelList[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}