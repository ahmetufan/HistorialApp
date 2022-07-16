package com.ahmet.HistoryApp.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.ap.view.HomeFragmentDirections
import com.ahmet.HistoryApp.model.Bili
import com.ahmet.HistoryApp.view.Type
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.bilim_row.view.*

class AdaptorBilim(private val list1: ArrayList<Bili>): RecyclerView.Adapter<AdaptorBilim.Holder>() {

    class Holder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.bilim_row,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.itemView.rowlidername.text=list1[position].name

        holder.itemView.rowLiderCountry.text=list1[position].location

        Glide.with(holder.itemView.context).load(list1[position].image).into(holder.itemView.rowLiderImage)

        holder.itemView.setOnClickListener {


            val action= HomeFragmentDirections.actionHomeFragmentToHomeDetailsActivity(list1[position].id3, Type.Bilim.ordinal)
            Navigation.findNavController(it).navigate(action)

        }

    }

    override fun getItemCount(): Int {
        return list1.size
    }

    fun updateBilim(updateBilims:List<Bili>) {
        list1.clear()
        list1.addAll(updateBilims)
        notifyDataSetChanged()
    }
}