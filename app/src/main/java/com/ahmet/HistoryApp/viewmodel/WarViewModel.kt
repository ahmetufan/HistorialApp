package com.ahmet.HistoryApp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ahmet.HistoryApp.model.Savvas
import com.ahmet.HistoryApp.preferences.CustomSharedPreferences
import com.ahmet.HistoryApp.service.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class WarViewModel(application: Application) : BaseViewModel(application) {

    private var customPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    private val warAPIService = SavasAPIService()
    private val compositeDisposable = CompositeDisposable()

    val savas = MutableLiveData<List<Savvas>>()


    fun refreshData() {

        getDataAPI()
        val updateTime = customPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {

            getDataFromSQLite()

        } else {

            getDataAPI()
        }

    }

    private fun getDataAPI() {

        compositeDisposable.add(
            warAPIService.getAPISavas()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Savvas>>() {
                    override fun onSuccess(t: List<Savvas>) {
                        storeInSQLite(t)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun storeInSQLite(list: List<Savvas>) {
        launch {
            val dao = UserDatabase(getApplication()).userDao()
            dao.deleteSavas()
            val listLong = dao.insertSavas(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].id7 = listLong[i].toInt()
                i++
            }
            showValue(list)
        }
        customPreferences.saveTime(System.nanoTime())
    }

    private fun getDataFromSQLite() {

        launch {

            val roomData = UserDatabase(getApplication()).userDao().getAllSavas()
            showValue(roomData)

        }
    }

    private fun showValue(savasList: List<Savvas>) {
        savas.value = savasList
    }


}