package com.ahmet.HistoryApp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.navArgs
import com.ahmet.HistoryApp.R
import kotlinx.android.synthetic.main.activity_war_details.*

class WarDetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_war_details)



        like_checkbox_war.setOnCheckedChangeListener { checkBox, isChecked ->

            if (isChecked) {

                Toast.makeText(applicationContext, "ischecked", Toast.LENGTH_SHORT).show()

            } else {

                Toast.makeText(applicationContext, "not checked", Toast.LENGTH_SHORT).show()
            }
        }
        img_back_war.setOnClickListener {
            onBackPressed()
        }





    }
}