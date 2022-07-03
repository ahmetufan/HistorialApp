package com.ahmet.HistoryApp.view

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.model.User
import com.ahmet.HistoryApp.viewmodel.UserViewModel

class AddFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_add, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val editname = view.findViewById<TextView>(R.id.editName)
        val insertButton = view.findViewById<Button>(R.id.insertButton)


        insertButton.setOnClickListener {
            var edit1=editname.text.toString()

            if (TextUtils.isEmpty(edit1) ) {
                insertButton.isEnabled=true
                Toast.makeText(requireContext(), "Boş Bırakamazsın", Toast.LENGTH_SHORT).show()
            } else {

                val user = User(0, edit1)

                userViewModel.addUser(user)
                Toast.makeText(requireContext(), "Kullanıcı Eklendi", Toast.LENGTH_SHORT).show()

                val action8=AddFragmentDirections.actionAddFragmentToProfilFragment()
                Navigation.findNavController(it).navigate(action8)
            }

        }

        return view
    }


}