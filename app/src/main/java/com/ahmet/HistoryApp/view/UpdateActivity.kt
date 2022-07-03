package com.ahmet.HistoryApp.view

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.model.User
import com.ahmet.HistoryApp.viewmodel.UserViewModel

class UpdateActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private val args by navArgs<UpdateActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val editname =findViewById<TextView>(R.id.editName2)

        editname.setText(args.currentUserr.name)

        val insertButton =findViewById<Button>(R.id.updateButton)

        insertButton.setOnClickListener {
            var edit3 = editname.text.toString()

            if (edit3.equals("")) {
                insertButton.isEnabled = true
                Toast.makeText(this@UpdateActivity, "Boş Bırakamazsın", Toast.LENGTH_SHORT).show()
            } else {

                val updateUser = User(args.currentUserr.id, edit3)
                userViewModel.updateUser(updateUser)
                Toast.makeText(this@UpdateActivity, "Veri Güncellendi", Toast.LENGTH_SHORT).show()

                val intent=Intent(this@UpdateActivity,ProfilFragment::class.java)
                startActivity(intent)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater=menuInflater
        menuInflater.inflate(R.menu.menu_delete,menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.menu_delete) {
            val builder= AlertDialog.Builder(this@UpdateActivity)

            builder.setTitle("${args.currentUserr.name} mi silinecek ?")
            builder.setMessage("${args.currentUserr.name} silmek istiyor musun ?")
            builder.setPositiveButton("Evet"){ _,_ ->
                userViewModel.deleteUser(args.currentUserr)
                Toast.makeText(this@UpdateActivity, "Silme İşlemi Başarılı", Toast.LENGTH_SHORT).show()

                val intent2=Intent(this@UpdateActivity,ProfilFragment::class.java)
                startActivity(intent2)
            }
            builder.setNegativeButton("Hayır"){_,_ -> }
            builder.create().show()
        }
        return super.onOptionsItemSelected(item)
    }
}