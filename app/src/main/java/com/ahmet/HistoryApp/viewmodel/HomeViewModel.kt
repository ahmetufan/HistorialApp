package com.ahmet.HistoryApp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.ahmet.HistoryApp.model.Bili
import com.ahmet.HistoryApp.preferences.CustomSharedPreferences
import com.ahmet.HistoryApp.service.HistoryAPIService
import com.ahmet.HistoryApp.service.UserDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : BaseViewModel(application) {

    private var customPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L  // 10 dakika

    private val bilimAPIService = HistoryAPIService()
    private val compositeDisposable = CompositeDisposable()

    val bilim = MutableLiveData<List<Bili>>()


    fun refreshData() {

        val updateTime = customPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {

            getDataFromSQLite()
            Toast.makeText(getApplication(), "sqlite veri alınd", Toast.LENGTH_LONG).show()

        } else {

            getDataAPI()

            Toast.makeText(getApplication(), "API veri alınd", Toast.LENGTH_LONG).show()
        }


    }

    private fun getDataAPI() {

        compositeDisposable.add(
            bilimAPIService.getAPIBilim()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Bili>>() {
                    override fun onSuccess(t: List<Bili>) {
                        storeInSQLite(t)

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun storeInSQLite(list: List<Bili>) {
        launch {
            val dao = UserDatabase(getApplication()).userDao()
            dao.deleteBilim()
            val listLong = dao.insertBilim(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].id3 = listLong[i].toInt()
                i++
            }
            showValue(list)
        }
        customPreferences.saveTime(System.nanoTime())
    }

    private fun getDataFromSQLite() {

        launch {

            val roomData = UserDatabase(getApplication()).userDao().getAllBilim()
            showValue(roomData)

        }
    }


    private fun showValue(bilimList: List<Bili>) {
        bilim.value = bilimList
    }


}