package com.ahmet.HistoryApp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmet.HistoryApp.R
import com.ahmet.HistoryApp.adaptor.AdaptorLider
import com.ahmet.HistoryApp.viewmodel.LiderViewModel
import kotlinx.android.synthetic.main.fragment_favori.*

class LiderFragment : Fragment() {
    private lateinit var liederViewModel: LiderViewModel
    private lateinit var adapterLieder: AdaptorLider



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favori, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        liederViewModel = ViewModelProvider(this)[LiderViewModel::class.java]
        liederViewModel.refreshData()


        recycler_liderr.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapterLieder = AdaptorLider(arrayListOf())
        recycler_liderr.adapter = adapterLieder
        observeLiederData()

        searchLider.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(deger: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if (deger != null) {
                    searchDatabase(deger)
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })



    }
    private fun searchDatabase(query:CharSequence) {
        val searchQuery="%$query%"
        liederViewModel.searchDatabase(searchQuery).observe(this, Observer { list ->
            list.let {
                adapterLieder.updateLider(it)
            }
        })
    }


    private fun observeLiederData() {

        liederViewModel.lider.observe(viewLifecycleOwner, Observer { lieder ->

            lieder?.let {
                adapterLieder.updateLider(lieder)
            }
        })
    }


}