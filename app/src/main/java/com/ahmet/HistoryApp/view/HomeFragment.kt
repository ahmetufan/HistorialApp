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
import com.ahmet.HistoryApp.model.Post
import com.ahmet.HistoryApp.service.Listener
import com.ahmet.HistoryApp.viewmodel.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), Listener {
    private val homeViewModel: LikeViewModel by activityViewModels()


    private lateinit var viewModel: HomeViewModel
    private lateinit var adapterbilim: Adaptor1

    private lateinit var filoViewModel: FilozofViewModel
    private lateinit var adapterfilozof: Adaptor2

    private lateinit var liederViewModel: LiderViewModel
    private lateinit var adapterLieder: Adaptor3

    private lateinit var oneViewModel:OnemliViewModel
    private lateinit var adapterOne:Adaptor4

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

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.refreshData()

        recycler_bilim.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapterbilim = Adaptor1(arrayListOf())
        recycler_bilim.adapter = adapterbilim
        observeLiveData()


        filoViewModel = ViewModelProvider(this)[FilozofViewModel::class.java]
        filoViewModel.refreshData()

        recycler_lider.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapterfilozof = Adaptor2(arrayListOf())
        recycler_lider.adapter = adapterfilozof
        observeFiloData()



        liederViewModel = ViewModelProvider(this)[LiderViewModel::class.java]
        liederViewModel.refreshData()


        recycler_filozof.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapterLieder = Adaptor3(arrayListOf())
        recycler_filozof.adapter = adapterLieder
        observeLiederData()



        oneViewModel= ViewModelProvider(this)[OnemliViewModel::class.java]
        oneViewModel.refreshData()

        recycler_aktivist.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        adapterOne= Adaptor4(arrayListOf())
        recycler_aktivist.adapter=adapterOne
        observeOnealData()


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

    private fun observeLiederData() {

        liederViewModel.lider.observe(viewLifecycleOwner, Observer { lieder ->

            lieder?.let {
                adapterLieder.updateLider(lieder)
            }
        })
    }

    private fun observeOnealData() {

        oneViewModel.onemli.observe(viewLifecycleOwner, Observer { one ->

            one?.let {
                adapterOne.updateOnemli(one)
            }
        })
    }

    override fun onItemClick(product: Post) {
        //Favori Ekleme
        homeViewModel.addToBasket(product)
    }


}