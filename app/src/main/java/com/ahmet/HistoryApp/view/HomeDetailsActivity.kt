package com.ahmet.HistoryApp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.navArgs
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.ap.view.HomeFragment
import kotlinx.android.synthetic.main.activity_home_details.*

class HomeDetailsActivity : AppCompatActivity() {

    private val args by navArgs<HomeDetailsActivityArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_details)


        like_checkbox.setOnCheckedChangeListener { checkBox, isChecked ->

            if (isChecked) {

                Toast.makeText(applicationContext, "ischecked", Toast.LENGTH_SHORT).show()

            } else {

                Toast.makeText(applicationContext, "not checked", Toast.LENGTH_SHORT).show()
            }
        }
        img_back_home.setOnClickListener {
            onBackPressed()
        }

        image_details.setImageResource(args.user.image)

        details_text_baslik.text=args.user.name
        details_text_detay.text=args.user.history
        details_text_age.text=args.user.age









    }
}