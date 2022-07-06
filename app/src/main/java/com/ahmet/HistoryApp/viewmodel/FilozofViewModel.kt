package com.ahmet.HistoryApp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ahmet.HistoryApp.model.Filo
import com.ahmet.HistoryApp.preferences.CustomSharedPreferences
import com.ahmet.HistoryApp.service.FilozofAPIService
import com.ahmet.HistoryApp.service.UserDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class FilozofViewModel(application: Application) : BaseViewModel(application) {

    private var customPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    private val filozofAPIService = FilozofAPIService()
    private val compositeDisposable = CompositeDisposable()

    val filozof = MutableLiveData<List<Filo>>()


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

        compositeDisposable.add(filozofAPIService.getAPIBilim()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Filo>>() {
                override fun onSuccess(t: List<Filo>) {
                    storeInSQLite(t)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
        )
    }

    private fun storeInSQLite(list: List<Filo>) {
        launch {
            val dao = UserDatabase(getApplication()).userDao()
            dao.deleteFilozof()
            val listLong = dao.insertFilozof(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].id4 = listLong[i].toInt()
                i++
            }
            showValue(list)
        }
        customPreferences.saveTime(System.nanoTime())
    }

    private fun getDataFromSQLite() {

        launch {

            val roomData= UserDatabase(getApplication()).userDao().getAllFilozof()
            showValue(roomData)

        }
    }

    private fun showValue(filozofList: List<Filo>) {
        filozof.value = filozofList
    }


}