package com.ahmet.HistoryApp.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.model.User
import com.ahmet.HistoryApp.view.ProfilFragmentDirections


class RecyclerviewAdapter() : RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder>() {
    private var userList = emptyList<User>()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textname = view.findViewById<TextView>(R.id.txtName)
        fun bind(user: User) {
            textname.text = user.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.bind(currentUser)

        holder.itemView.setOnClickListener {
            val action=ProfilFragmentDirections.actionProfilFragmentToUpdateActivity2(currentUser)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }
}