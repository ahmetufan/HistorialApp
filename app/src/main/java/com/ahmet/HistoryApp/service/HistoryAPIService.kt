package com.ahmet.HistoryApp.service

import com.ahmet.HistoryApp.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HistoryAPIService {

    private val URL="http://www.adddisyon.online/"

    private val retrofit=Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build().create(HistoryAPI::class.java)

    fun getAPIBilim(): Single<List<Bili>> {
        return retrofit.getHomeBilim()
    }
}
class FilozofAPIService {

    private val URL="http://www.adddisyon.online/"

    private val retrofit=Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build().create(HistoryAPI::class.java)

    fun getAPIBilim(): Single<List<Filo>> {
        return retrofit.getHomeFilozof()
    }
}
class LiderAPIService {

    private val URL="http://www.adddisyon.online/"

    private val retrofit=Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build().create(HistoryAPI::class.java)

    fun getAPILider(): Single<List<Lieder>> {
        return retrofit.getHomeLider()
    }
}
class SavasAPIService {

    private val URL="http://www.adddisyon.online/"

    private val retrofit=Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build().create(HistoryAPI::class.java)

    fun getAPISavas(): Single<List<Savvas>> {
        return retrofit.getHomeSavas()
    }
}