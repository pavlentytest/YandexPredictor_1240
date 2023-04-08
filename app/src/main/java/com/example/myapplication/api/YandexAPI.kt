package com.example.myapplication.api

import com.example.myapplication.model.Answer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YandexAPI {
    /*
    https://predictor.yandex.net
    /api/v1/predict.json/complete
    ?
    key=pdct.1.1.20230408T095716Z.a59fae57c5a7b804.24520269af377e887590b937941df66acc887d6a
    &
    q=H
    &
    lang=en
    &
    limit=5
     */

    @GET("/api/v1/predict.json/complete")
    fun getComplete(@Query("key") key222: String,
                    @Query("q") q222: String,
                    @Query("lang") lang333: String,
                    @Query("limit") limit444: Int
    ): Call<Answer>
}