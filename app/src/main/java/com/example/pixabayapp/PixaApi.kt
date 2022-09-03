package com.example.pixabayapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {
    @GET("api/")
    fun getImages(
        @Query("key") key: String = "29410218-d721e8ad3a860ad41ed56383b",
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 10
    ): Call<PixabayModel>
}