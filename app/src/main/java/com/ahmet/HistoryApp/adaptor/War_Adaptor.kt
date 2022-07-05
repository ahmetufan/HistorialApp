package com.ahmet.HistoryApp.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.model.Savvas
import com.ahmet.HistoryApp.view.WarFragmentDirections
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.war_row.view.*

class War_Adaptor(private val modelList: ArrayList<Savvas>) :
    RecyclerView.Adapter<War_Adaptor.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): War_Adaptor.Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.war_row, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: War_Adaptor.Holder, position: Int) {

        holder.itemView.texwarName.text = modelList[position].name
        holder.itemView.textwarKonum.text=modelList[position].location
        holder.itemView.textwarTime.text = modelList[position].date

        Glide.with(holder.itemView.context).load(modelList[position].image).into(holder.itemView.imageWar)

        holder.itemView.setOnClickListener {
            val action=WarFragmentDirections.actionWarFragmentToWarDetailsActivity(modelList[position].id7)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
    fun updateSavas(updatesavass:List<Savvas>) {
        modelList.clear()
        modelList.addAll(updatesavass)
        notifyDataSetChanged()
    }
}