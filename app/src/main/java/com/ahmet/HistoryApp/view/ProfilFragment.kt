package com.ahmet.HistoryApp.view

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.adaptor.RecyclerviewAdapter
import com.ahmet.HistoryApp.model.UserModel
import com.ahmet.HistoryApp.viewmodel.UserViewModel
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.fragment_profil.*
import kotlinx.android.synthetic.main.fragment_profil.view.*
import kotlin.collections.HashMap

class ProfilFragment : Fragment() {
    private lateinit var recyclerviewAdapter: RecyclerviewAdapter
    private lateinit var userViewModel: UserViewModel

    var usersRefrence:DatabaseReference?= null
    var firebaseUser:FirebaseUser?=null
    private val RequestCode=438
    private var imageUri:Uri?=null
    private var storageRef:StorageReference?=null
    private var coverChecker:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profil, container, false)

        firebaseUser=FirebaseAuth.getInstance().currentUser
        usersRefrence=FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUser!!.uid)
        storageRef=FirebaseStorage.getInstance().reference.child("User Images")

        usersRefrence!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val user:UserModel?=snapshot.getValue(UserModel::class.java)

                    if (context != null) {

                        view.profilnametext.text=user!!.username

                        Glide.with(this@ProfilFragment).load(user.imageUrl).into(view.profilImage)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })




        //Not Defteri
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {

            val action7 = ProfilFragmentDirections.actionProfilFragmentToAddFragment()
            Navigation.findNavController(it).navigate(action7)
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerviewAdapter = RecyclerviewAdapter()
        recyclerView.adapter = recyclerviewAdapter

        userViewModel.readAllData.observe(viewLifecycleOwner, Observer { userList ->
            recyclerviewAdapter.setData(userList)

        })

        return view
    }

    private fun pickImage() {

        val intent=Intent()
        intent.type="image/*"
        intent.action=Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,RequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==RequestCode && resultCode == Activity.RESULT_OK && data!!.data != null )
        {
            imageUri=data.data
            Toast.makeText(context, "yükleniyor!!", Toast.LENGTH_SHORT).show()
            uploadImage()
        }
    }

    private fun uploadImage() {

        val progressBar=ProgressDialog(context)
        progressBar.setMessage("Fotoğraf Yükleniyor bekleyiniz lütfen")
        progressBar.show()

        if (imageUri != null) {

            val fileRef=storageRef!!.child(System.currentTimeMillis().toString()+ ".jpg")

            var uploadTask:StorageTask<*>
            uploadTask=fileRef.putFile(imageUri!!)

            uploadTask.continueWithTask<Uri?>(Continuation <UploadTask.TaskSnapshot, Task<Uri>> { task ->

                if (task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation fileRef.downloadUrl
            }).addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    val downloadUrl=task.result
                    val url=downloadUrl.toString()

                    if (coverChecker == "cover") {

                        val mapProfileImg=HashMap<String,Any>()
                        mapProfileImg["imageUrl"]=url
                        usersRefrence!!.updateChildren(mapProfileImg)
                        coverChecker=""
                    }
                    progressBar.dismiss()


                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profilImage.setOnClickListener {
            coverChecker="cover"
            pickImage()
        }


        logOut_imgprofil.setOnClickListener {

            val alert = AlertDialog.Builder(context)
            alert.setTitle("Çıkış Yap")
            alert.setMessage("Çıkış yapmak istiyor musun ?")

            alert.setPositiveButton("Evet") { dialog, which ->

                FirebaseAuth.getInstance().signOut()

                val action5 = ProfilFragmentDirections.actionProfilFragmentToLoginActivity()
                Navigation.findNavController(it).navigate(action5)

            }
            alert.setNegativeButton("Hayır") { dialog, which ->
            }
            alert.show()

        }
    }

}