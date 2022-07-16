package com.ahmet.HistoryApp.ap.view

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
import com.ahmet.HistoryApp.adaptor.*
import com.ahmet.HistoryApp.viewmodel.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(){


    private lateinit var viewModel: BilimViewModel
    private lateinit var adapterbilim: AdaptorBilim

    private lateinit var filoViewModel: FilozofViewModel
    private lateinit var adapterfilozof: AdaptorFilozof


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        viewModel = ViewModelProvider(this)[BilimViewModel::class.java]
        viewModel.refreshData()

        recycler_bilim.layoutManager =
            LinearLayoutManager(context)
        adapterbilim =AdaptorBilim(arrayListOf())
        recycler_bilim.adapter = adapterbilim
        observeLiveData()


        filoViewModel = ViewModelProvider(this)[FilozofViewModel::class.java]
        filoViewModel.refreshData()

        recycler_lider.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapterfilozof = AdaptorFilozof(arrayListOf())
        recycler_lider.adapter = adapterfilozof
        observeFiloData()



    }

    private fun observeLiveData() {

        viewModel.bilim.observe(viewLifecycleOwner, Observer { bulimia ->

            bulimia?.let {
                adapterbilim.updateBilim(bulimia)
            }
        })
    }

    private fun observeFiloData() {

        filoViewModel.filozof.observe(viewLifecycleOwner, Observer { filo ->

            filo?.let {
                adapterfilozof.updateFilozof(filo)
            }
        })
    }


}