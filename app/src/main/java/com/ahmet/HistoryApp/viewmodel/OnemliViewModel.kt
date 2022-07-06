package com.ahmet.HistoryApp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ahmet.HistoryApp.model.One
import com.ahmet.HistoryApp.preferences.CustomSharedPreferences
import com.ahmet.HistoryApp.service.OnemliAPIService
import com.ahmet.HistoryApp.service.UserDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class OnemliViewModel(application: Application) : BaseViewModel(application) {

    private var customPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    private val onemliAPIService = OnemliAPIService()
    private val compositeDisposable = CompositeDisposable()

    val onemli = MutableLiveData<List<One>>()


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
            onemliAPIService.getAPILider()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<One>>() {
                    override fun onSuccess(t: List<One>) {
                        storeInSQLite(t)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun storeInSQLite(list: List<One>) {
        launch {
            val dao = UserDatabase(getApplication()).userDao()
            dao.deleteOnemli()
            val listLong = dao.insertOnemli(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].id6 = listLong[i].toInt()
                i++
            }
            showValue(list)
        }
        customPreferences.saveTime(System.nanoTime())
    }

    private fun getDataFromSQLite() {

        launch {

            val roomData = UserDatabase(getApplication()).userDao().getAllOnemli()
            showValue(roomData)

        }
    }

    private fun showValue(onemliList: List<One>) {
        onemli.value = onemliList
    }


}