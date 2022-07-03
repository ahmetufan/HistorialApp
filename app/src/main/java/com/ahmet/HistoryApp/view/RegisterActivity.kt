package com.ahmet.HistoryApp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.ap.view.HomeFragment
import com.ahmet.HistoryApp.ap.view.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var mAuth:FirebaseAuth
    private lateinit var refUsers:DatabaseReference
    private var firebaseUserId:String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        /*   val toolbar:Toolbar=findViewById(R.id.toolbar_register)
           supportActionBar!!.title="Register"
           supportActionBar!!.setDisplayHomeAsUpEnabled(true)
           toolbar.setNavigationOnClickListener {

               val intent= Intent (this@RegisterActivity,WelcomeActivity::class.java)
               startActivity(intent)
               finish()
           }
         */
        mAuth= FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener {
            registerUser()
        }

        img_back.setOnClickListener {
            onBackPressed()
        }


    }

    private fun registerUser() {
        val username:String=etUserName.text.toString()
        val email:String=etMail.text.toString()
        val password:String=etPassword.text.toString()

        if (username == "" || email == "" || password == "") {
            Toast.makeText(this@RegisterActivity, "Please write", Toast.LENGTH_SHORT).show()
        } else {

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    firebaseUserId=mAuth.currentUser!!.uid
                    refUsers=FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserId)

                    val userHashMap= HashMap<String,Any>()
                    userHashMap["uid"]=firebaseUserId
                    userHashMap["username"]=username

                    refUsers.updateChildren(userHashMap).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent=Intent(this@RegisterActivity,MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                        }
                    }

                } else {
                    Toast.makeText(this@RegisterActivity, "Error "+task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}