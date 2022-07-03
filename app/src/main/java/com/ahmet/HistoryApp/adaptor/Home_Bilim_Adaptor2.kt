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
import kotlinx.android.synthetic.main.home_bilim_row_dikey.view.*

class Home_Bilim_Adaptor2(private val models:ArrayList<Post>,private val listener:Listener) : RecyclerView.Adapter<Home_Bilim_Adaptor2.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view2=LayoutInflater.from(parent.context).inflate(R.layout.home_bilim_row_dikey,parent,false)
        return Holder(view2)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.itemView.imageView3.setImageResource(models[position].image)
        holder.itemView.text1.text=models[position].name
        holder.itemView.text22.text=models[position].country
        holder.itemView.text3.text=models[position].age


        holder.itemView.favorite_checkbox2.setOnClickListener {
            listener.onItemClick(models.get(position))
        }

        holder.itemView.layoutDikey.setOnClickListener {

            val action2=HomeFragmentDirections.actionHomeFragmentToHomeDetailsActivity(models[position])
            Navigation.findNavController(it).navigate(action2)
        }

    }

    override fun getItemCount(): Int {
        return 5
    }

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}