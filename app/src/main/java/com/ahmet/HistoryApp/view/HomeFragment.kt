package com.ahmet.HistoryApp.ap.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.adaptor.Home_Bilim_Adaptor
import com.ahmet.HistoryApp.adaptor.Home_Bilim_Adaptor2
import com.ahmet.HistoryApp.model.Post
import com.ahmet.HistoryApp.service.Listener
import com.ahmet.HistoryApp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() ,Listener{
    private val homeViewModel:HomeViewModel by activityViewModels()

    private lateinit var adaptery:Home_Bilim_Adaptor
    private lateinit var adaptery2:Home_Bilim_Adaptor2
    private lateinit var model:ArrayList<Post>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model=ArrayList<Post>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model2=Post("Fatih Sultan Mehmet","OSMANLI DEVLETİ","1449-1467",R.drawable.fsm,"Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet ")
        model.add(model2)

        val model3=Post("Madam Curie","ABD","1449-1467",R.drawable.madam,"Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet ")
        model.add(model3)

        val model4=Post("Sokrates","YUNANİSTAN","1449-1467",R.drawable.sokrates,"Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet ")
        model.add(model4)

        val model5=Post("Einstein","ABD","1449-1467",R.drawable.einstein,"Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet ")
        model.add(model5)

        val model6=Post("Cengiz Han","MOĞOLİSTAN","1449-1467",R.drawable.cengizhan,"Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet Fatih Sultan Mehmet ")
        model.add(model6)

        adaptery= Home_Bilim_Adaptor(model,this)
        adaptery2= Home_Bilim_Adaptor2(model,this)

        recycler_bilim.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recycler_bilim.adapter=adaptery

        recycler_lider.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recycler_lider.adapter=adaptery

        recycler_filozof.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        recycler_filozof.adapter=adaptery2

        recycler_aktivist.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recycler_aktivist.adapter=adaptery





    }

    override fun onItemClick(product: Post) {
        //Favori Ekleme
        homeViewModel.addToBasket(product)
    }


}