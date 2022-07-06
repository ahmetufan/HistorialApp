package com.ahmet.HistoryApp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ahmet.HistoryApp.model.Bili
import com.ahmet.HistoryApp.model.Filo
import com.ahmet.HistoryApp.model.Lieder
import com.ahmet.HistoryApp.service.UserDatabase
import kotlinx.coroutines.launch

class HomeDetailsViewModel  (application: Application): BaseViewModel(application)  {

    val details=MutableLiveData<Bili>()

    fun getDetailRoom(id:Int){

        launch {

            val dao=UserDatabase(getApplication()).userDao().getBilimID(id)

            details.value=dao
        }
    }
}
class HomeDetailsViewModel2  (application: Application): BaseViewModel(application)  {

    val details2=MutableLiveData<Filo>()

    fun getDetailRoom2(id2:Int){

        launch {

            val dao=UserDatabase(getApplication()).userDao().getFilozofID(id2)

            details2.value=dao
        }
    }
}
class HomeDetailsViewModel3  (application: Application): BaseViewModel(application)  {

    val details3=MutableLiveData<Lieder>()

    fun getDetailRoom3(id3:Int){

        launch {

            val dao=UserDatabase(getApplication()).userDao().getLiderID(id3)

            details3.value=dao
        }
    }
}