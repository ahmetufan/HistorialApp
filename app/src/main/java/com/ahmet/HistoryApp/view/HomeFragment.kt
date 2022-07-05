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
    private lateinit var adapterbilim: Home_Adaptor

    private lateinit var filozofViewModel: FilozofViewModel
    private lateinit var adapterfilozof: Adaptor_Filozof

    private lateinit var liderViewModel: LiderViewModel
    private lateinit var adapterLider: Adaptor_Lider

    private lateinit var onemliViewModel:OnemliViewModel
    private lateinit var adapterOnemli:Adaptor_Onemli

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

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.refreshData()

        recycler_bilim.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapterbilim = Home_Adaptor(arrayListOf(), arrayListOf())
        recycler_bilim.adapter = adapterbilim

        observeLiveData()

        filozofViewModel = ViewModelProvider(this).get(FilozofViewModel::class.java)

        filozofViewModel.refreshData()

        recycler_lider.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapterfilozof = Adaptor_Filozof(arrayListOf())
        recycler_lider.adapter = adapterfilozof

        observeFilozofData()

        liderViewModel = ViewModelProvider(this).get(LiderViewModel::class.java)

        liderViewModel.refreshData()

        recycler_filozof.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapterLider = Adaptor_Lider(arrayListOf())
        recycler_filozof.adapter = adapterLider

        observeLiderData()

        onemliViewModel=ViewModelProvider(this).get(OnemliViewModel::class.java)

        onemliViewModel.refreshData()

        recycler_aktivist.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        adapterOnemli= Adaptor_Onemli(arrayListOf())
        recycler_aktivist.adapter=adapterOnemli

        observeOnemliData()


    }

    fun observeLiveData() {

        viewModel.bilim.observe(viewLifecycleOwner, Observer { bilim ->

            bilim?.let {
                adapterbilim.updateBilim(bilim)
            }
        })
    }

    fun observeFilozofData() {

        filozofViewModel.filozof.observe(viewLifecycleOwner, Observer { filozof ->

            filozof?.let {
                adapterfilozof.updateFilozof(filozof)
            }
        })
    }

    fun observeLiderData() {

        liderViewModel.lider.observe(viewLifecycleOwner, Observer { lider ->

            lider?.let {
                adapterLider.updateLider(lider)
            }
        })
    }

    fun observeOnemliData() {

        onemliViewModel.onemli.observe(viewLifecycleOwner, Observer { onemli ->

            onemli?.let {
                adapterOnemli.updateOnemli(onemli)
            }
        })
    }

    override fun onItemClick(product: Post) {
        //Favori Ekleme
        homeViewModel.addToBasket(product)
    }


}