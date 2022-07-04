package com.ahmet.HistoryApp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.adaptor.War_Adaptor
import com.ahmet.HistoryApp.viewmodel.LikeViewModel
import com.ahmet.HistoryApp.viewmodel.WarViewModel
import kotlinx.android.synthetic.main.fragment_war.*

class WarFragment : Fragment() {
    private lateinit var adaptery:War_Adaptor
    private val homeViewModel: LikeViewModel by activityViewModels()
    private lateinit var warViewModel:WarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_war, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        warViewModel=ViewModelProvider(this).get(WarViewModel::class.java)

        warViewModel.refreshData()


        recycler_war.layoutManager=LinearLayoutManager(requireContext())
        adaptery= War_Adaptor(arrayListOf())
        recycler_war.adapter=adaptery

       observeWarData()

    }
    fun observeWarData() {

        warViewModel.savas.observe(viewLifecycleOwner, Observer { war ->

            war?.let {
                adaptery.updateSavas(war)
            }
        })
    }



}