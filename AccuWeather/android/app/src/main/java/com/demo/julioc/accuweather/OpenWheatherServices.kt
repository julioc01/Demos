package com.demo.julioc.accuweather

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWheatherServices {

    @GET("wheather")
    fun currentWheather(@Query("q") q: String) : Observable<String>
}