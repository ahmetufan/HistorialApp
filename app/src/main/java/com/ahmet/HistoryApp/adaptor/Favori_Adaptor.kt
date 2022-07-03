package com.ahmet.HistoryApp.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.model.Post
import com.ahmet.HistoryApp.view.FavoriFragmentDirections
import kotlinx.android.synthetic.main.favori_row.view.*


class Favori_Adaptor(val basketList:List<Post>): RecyclerView.Adapter<Favori_Adaptor.BasketHolder> (){

    class BasketHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.favori_row,parent,false)
        return BasketHolder(view)
    }

    override fun onBindViewHolder(holder: BasketHolder, position: Int) {
        holder.itemView.rowName_favori.text=basketList[position].name
        holder.itemView.rowCountry_favori.text=basketList[position].country
        holder.itemView.rowTime_favori.text=basketList[position].age

        holder.itemView.rowImage_favori.setImageResource(basketList[position].image)

        holder.itemView.rowBtn_favori.setOnClickListener {

            val actionfavori=FavoriFragmentDirections.actionFavoriFragmentToFavoriDetailsActivity(basketList[position])
            Navigation.findNavController(it).navigate(actionfavori)
        }
    }

    override fun getItemCount(): Int {
        return basketList.size
    }

}