package com.ahmet.HistoryApp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.viewmodel.HomeDetailsViewModel3
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_favori_details.*

class LiderDetailsActivity : AppCompatActivity() {
    private val post by navArgs<LiderDetailsActivityArgs>()
    private lateinit var literalsViewModel: HomeDetailsViewModel3


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favori_details)

        favori_img_back_war.setOnClickListener {
            onBackPressed()
        }
        literalsViewModel = ViewModelProvider(this)[HomeDetailsViewModel3::class.java]
        literalsViewModel.getDetailRoom3(post.post)
        observeDetailLiederRoom()





    }
    private fun observeDetailLiederRoom() {

        literalsViewModel.details3.observe(this, Observer { detay3 ->

            detay3?.let {

                favori_details_text_baslik.text = detay3.name
                favori_details_text_time.text = detay3.date
                favori_details_text_detay.text = detay3.details
                Glide.with(this).load(detay3.image).into(favori_image_details)
            }
        })
    }
}