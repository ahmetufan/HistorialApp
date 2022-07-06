package com.ahmet.HistoryApp.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.ap.view.HomeFragmentDirections
import com.ahmet.HistoryApp.model.Filo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.filozof_row.view.*

class Adaptor2(private val list1: ArrayList<Filo>): RecyclerView.Adapter<Adaptor2.Holder>() {

    class Holder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.filozof_row,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.itemView.nameRowFilo.text=list1[position].name

        Glide.with(holder.itemView.context).load(list1[position].image).into(holder.itemView.imageRowFilo)

        holder.itemView.setOnClickListener {

          /*  val intent=Intent(holder.itemView.context,HomeDetailsActivity::class.java)
            intent.putExtra("id1",list1[position].id2)
            intent.putExtra("info","new")
            holder.itemView.context.startActivity(intent)
                                                                   */

            val action=HomeFragmentDirections.actionHomeFragmentToHomeDetailsActivity(list1[position].id4)
            Navigation.findNavController(it).navigate(action)
        }


    }

    override fun getItemCount(): Int {
        return list1.size
    }

    fun updateFilozof(updatefilozofs:List<Filo>) {
        list1.clear()
        list1.addAll(updatefilozofs)
        notifyDataSetChanged()
    }
}