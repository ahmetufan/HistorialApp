package com.ahmet.HistoryApp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.viewmodel.HomeDetailsViewModel
import com.ahmet.HistoryApp.viewmodel.HomeDetailsViewModel2
import com.ahmet.HistoryApp.viewmodel.HomeDetailsViewModel3
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_home_details.*

class HomeDetailsActivity : AppCompatActivity() {
    private lateinit var billiardsViewModel: HomeDetailsViewModel
    private lateinit var flooziesViewModel: HomeDetailsViewModel2

    private val args by navArgs<HomeDetailsActivityArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_details)



        when (args.type) {
            Type.Bilim.ordinal -> {
                billiardsViewModel = ViewModelProvider(this)[HomeDetailsViewModel::class.java]
                billiardsViewModel.getDetailRoom(args.uID)
                observeDetailBiliRoom()
            }
            Type.Filo.ordinal -> {
                flooziesViewModel = ViewModelProvider(this)[HomeDetailsViewModel2::class.java]
                flooziesViewModel.getDetailRoom2(args.uID)
                observeDetailFiloRoom()
            }
        }


        img_back_home.setOnClickListener {
            onBackPressed()
        }

    }

    private fun observeDetailBiliRoom() {

        billiardsViewModel.details.observe(this, Observer { detay ->

            detay?.let {

                details_text_baslik.text = detay.name
                details_text_age.text = detay.date
                details_text_detay.text = detay.details
                Glide.with(this).load(detay.image).into(image_details)

            }
        })
    }

    private fun observeDetailFiloRoom() {

        flooziesViewModel.details2.observe(this, Observer { detay2 ->

            detay2?.let {

                details_text_baslik.text = detay2.name
                details_text_age.text = detay2.date
                details_text_detay.text = detay2.details
                Glide.with(this).load(detay2.image).into(image_details)
            }
        })
    }

}