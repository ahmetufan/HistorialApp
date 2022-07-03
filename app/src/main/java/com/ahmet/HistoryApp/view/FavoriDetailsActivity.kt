package com.ahmet.HistoryApp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.navArgs
import com.ahmet.HistoryApp.R
import kotlinx.android.synthetic.main.activity_favori_details.*

class FavoriDetailsActivity : AppCompatActivity() {
    private val post by navArgs<FavoriDetailsActivityArgs>()
    private val war by navArgs<FavoriDetailsActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favori_details)

        favori_img_back_war.setOnClickListener {
            onBackPressed()
        }

        //Birinci Recyclerview Home
        favori_image_details.setImageResource(post.post1.image)
        favori_details_text_baslik.text=post.post1.name
        favori_details_text_time.text=post.post1.age
        favori_details_text_detay.text=post.post1.history




    }
}