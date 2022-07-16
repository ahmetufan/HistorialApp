package com.ahmet.HistoryApp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.viewmodel.WarDetailsViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_war_details.*

class WarDetailsActivity : AppCompatActivity() {
    private lateinit var warViewModel: WarDetailsViewModel

    private  val args by navArgs<WarDetailsActivityArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_war_details)

        warViewModel = ViewModelProvider(this)[WarDetailsViewModel::class.java]
        warViewModel.getDetailRoom3(args.uId)
        observeWarDetail()



        img_back_war.setOnClickListener {
            onBackPressed()
        }





    }
    private fun observeWarDetail() {

        warViewModel.details.observe(this, Observer { detay ->

            Glide.with(this).load(detay.image).into(war_image_details)
            war_details_text_baslik.text=detay.name
            war_details_text_time.text=detay.date
            war_details_text_detay.text=detay.details
        })
    }
}