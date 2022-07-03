package com.ahmet.HistoryApp.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.ap.view.HomeFragmentDirections
import com.ahmet.HistoryApp.model.Post
import com.ahmet.HistoryApp.service.Listener
import kotlinx.android.synthetic.main.home_bilim_row.view.*

class Home_Bilim_Adaptor(private val modelList:ArrayList<Post>,private val listener: Listener) : RecyclerView.Adapter<Home_Bilim_Adaptor.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): Home_Bilim_Adaptor.MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.home_bilim_row,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: Home_Bilim_Adaptor.MyViewHolder, position: Int) {

        holder.itemView.imageRowBilim.setImageResource(modelList[position].image)
        holder.itemView.textRowBilim.text=modelList[position].name
        holder.itemView.textRowBilimTarih.text=modelList[position].age

        holder.itemView.favorite_checkbox.setOnClickListener {

            listener.onItemClick(modelList.get(position))
        }

        holder.itemView.layoutBilim.setOnClickListener {

            val action=HomeFragmentDirections.actionHomeFragmentToHomeDetailsActivity(modelList[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return  modelList.size
    }

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

    }
}