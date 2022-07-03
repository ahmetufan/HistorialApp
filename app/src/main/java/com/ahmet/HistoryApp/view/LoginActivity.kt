package com.ahmet.HistoryApp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.navigation.findNavController
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.ap.view.HomeFragment
import com.ahmet.HistoryApp.ap.view.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private  var auth: FirebaseAuth? = null
    private  var firebaseUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        // Giriş yapan kişinin bir daha giriş yapmaması için
        val currentUser = auth!!.currentUser

        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        val gelen =intent






        btnLogin.setOnClickListener {
            val email = etMailLogin.text.toString()
            val passsword = etPasswordLogin.text.toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(passsword)) {
                Toast.makeText(applicationContext, "email ve şifreyi girin", Toast.LENGTH_SHORT)
                    .show()
            } else {

                auth!!.signInWithEmailAndPassword(email, passsword)
                    .addOnCompleteListener(this) {
                        if (it.isSuccessful) {
                            etMailLogin.setText("")
                            etPasswordLogin.setText("")
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "email ve şifre bulunamadı",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }

        btnSignUpLogin.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)

        }
    }
}