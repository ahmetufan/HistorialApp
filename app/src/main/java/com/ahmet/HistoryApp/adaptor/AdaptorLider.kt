package com.ahmet.HistoryApp.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.model.Lieder
import com.ahmet.HistoryApp.view.LiderFragmentDirections
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.lider_row.view.*

class AdaptorLider(private val list1: ArrayList<Lieder>): RecyclerView.Adapter<AdaptorLider.Holder>() {

    class Holder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.lider_row,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.itemView.text1.text=list1[position].name
        holder.itemView.text22.text=list1[position].location

        Glide.with(holder.itemView.context).load(list1[position].image).into(holder.itemView.imageView3)

        holder.itemView.setOnClickListener {
            val action= LiderFragmentDirections.actionFavoriFragmentToFavoriDetailsActivity(list1[position].id5)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return list1.size
    }

    fun updateLider(updateliders:List<Lieder>) {
        list1.clear()
        list1.addAll(updateliders)
        notifyDataSetChanged()
    }
}