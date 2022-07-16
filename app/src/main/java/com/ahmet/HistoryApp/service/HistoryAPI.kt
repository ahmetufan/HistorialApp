package com.ahmet.HistoryApp.service

import com.ahmet.HistoryApp.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface HistoryAPI {

    //  http://www.adddisyon.online/ history_bilim.php   ->  bilim
    //  http://www.adddisyon.online/ history_filozof.php  -> filozof
    //  http://www.adddisyon.online/ history_lider.php   ->  lider
    //  http://www.adddisyon.online/ history_onemli.php  ->  önemli
    //  http://www.adddisyon.online/ history_savas.php  ->  savaş

    @GET("history_bilim.php")
    fun getHomeBilim():Single<List<Bili>>

    @GET("history_filozof.php")
    fun getHomeFilozof():Single<List<Filo>>

    @GET("history_lider.php")
    fun getHomeLider():Single<List<Lieder>>

    @GET("history_savas.php")
    fun getHomeSavas():Single<List<Savvas>>
}