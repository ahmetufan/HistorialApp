package com.ahmet.HistoryApp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.adaptor.Favori_Adaptor
import com.ahmet.HistoryApp.viewmodel.HomeViewModel
import com.ahmet.HistoryApp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_favori.*

class FavoriFragment : Fragment() {
    private val homeViewModel:HomeViewModel by activityViewModels()
    private var favori_adaptor:Favori_Adaptor?= null

    private lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favori, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        recycler_favori.layoutManager=LinearLayoutManager(activity?.baseContext)

        ItemTouchHelper(swipeCallBack).attachToRecyclerView(recycler_favori)

        homeViewModel.basket.observe(viewLifecycleOwner, Observer {

            favori_adaptor= Favori_Adaptor(it)
            recycler_favori.adapter=favori_adaptor

        })




    }
    private val swipeCallBack= object:
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView,viewHolder: RecyclerView.ViewHolder,target: RecyclerView.ViewHolder): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition=viewHolder.layoutPosition
            if (favori_adaptor != null) {
                val selectedProduct=favori_adaptor!!.basketList.get(layoutPosition)
                homeViewModel.deleteProductBasket(selectedProduct)
                favori_adaptor!!.notifyDataSetChanged()
            }
        }

    }

}