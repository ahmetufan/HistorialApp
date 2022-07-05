package com.ahmet.HistoryApp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    private lateinit var bilimdetailsViewModel: HomeDetailsViewModel
    private lateinit var filozofetailsViewModel: HomeDetailsViewModel2
    private lateinit var liderdailsViewModel: HomeDetailsViewModel3

    private val args by navArgs<HomeDetailsActivityArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_details)

        //val id2=intent.getIntExtra("id2",1)
        //val id1=intent.getIntExtra("id1",2)
        //val intent2 = intent
        //val info = intent2.getStringExtra("info")


        bilimdetailsViewModel = ViewModelProvider(this)[HomeDetailsViewModel::class.java]
        bilimdetailsViewModel.getDetailRoom(args.uID)
        observeDetailBiliRoom()

        filozofetailsViewModel = ViewModelProvider(this)[HomeDetailsViewModel2::class.java]
        filozofetailsViewModel.getDetailRoom2(args.uID)
        observeDetailFiloRoom()

        liderdailsViewModel = ViewModelProvider(this)[HomeDetailsViewModel3::class.java]
        liderdailsViewModel.getDetailRoom3(args.uID)
        observeDetailLiederRoom()






        img_back_home.setOnClickListener {
            onBackPressed()
        }

    }

    private fun observeDetailBiliRoom() {

        bilimdetailsViewModel.details.observe(this, Observer { detay ->

            detay?.let {

                details_text_baslik.text = detay.name
                details_text_age.text = detay.date
                details_text_detay.text = detay.details
                Glide.with(this).load(detay.image).into(image_details)

            }
        })
    }

    private fun observeDetailFiloRoom() {

        filozofetailsViewModel.details2.observe(this, Observer { detay2 ->

            detay2?.let {

                details_text_baslik.text = detay2.name
                details_text_age.text = detay2.date
                details_text_detay.text = detay2.details
                Glide.with(this).load(detay2.image).into(image_details)
            }
        })
    }

    private fun observeDetailLiederRoom() {

        liderdailsViewModel.details3.observe(this, Observer { detay3 ->

            detay3?.let {

                details_text_baslik.text = detay3.name
                details_text_age.text = detay3.date
                details_text_detay.text = detay3.details
                Glide.with(this).load(detay3.image).into(image_details)
            }
        })
    }


}