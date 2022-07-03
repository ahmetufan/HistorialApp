package com.ahmet.HistoryApp.ap.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.view.ProfilFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)






        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController

        bottom_nav_bar.setOnItemSelectedListener {

            when (it) {

                R.id.homeFragment -> navController.navigate(R.id.homeFragment)

                R.id.warFragment -> navController.navigate(R.id.warFragment)

                R.id.favoriFragment -> navController.navigate(R.id.favoriFragment)

                R.id.profilFragment -> navController.navigate(R.id.profilFragment)
            }
        }

    }

}