package com.ahmet.HistoryApp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.ahmet.HistoryApp.model.Lieder
import com.ahmet.HistoryApp.preferences.CustomSharedPreferences
import com.ahmet.HistoryApp.service.LiderAPIService
import com.ahmet.HistoryApp.service.UserDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class LiderViewModel(application: Application) : BaseViewModel(application) {

    private var customPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    private val liderAPIService = LiderAPIService()
    private val compositeDisposable = CompositeDisposable()

    val lider = MutableLiveData<List<Lieder>>()


    fun refreshData() {

        getDataAPI()
        val updateTime = customPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {

            getDataFromSQLite()

        } else {

            getDataAPI()
        }


    }
    fun searchDatabase(search:String):LiveData<List<Lieder>>{

         val searchDao = UserDatabase(getApplication()).userDao()

        return searchDao.searchDatabase(search).asLiveData()
    }

    private fun getDataAPI() {

        compositeDisposable.add(
            liderAPIService.getAPILider()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Lieder>>() {
                    override fun onSuccess(t: List<Lieder>) {
                        storeInSQLite(t)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun storeInSQLite(list: List<Lieder>) {
        launch {
            val dao = UserDatabase(getApplication()).userDao()
            dao.deleteLider()
            val listLong = dao.insertLider(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].id5 = listLong[i].toInt()
                i++
            }
            showValue(list)
        }
        customPreferences.saveTime(System.nanoTime())
    }

    private fun getDataFromSQLite() {

        launch {

            val roomData = UserDatabase(getApplication()).userDao().getAllLider()
            showValue(roomData)

        }
    }

    private fun showValue(liderList: List<Lieder>) {
        lider.value = liderList
    }


}