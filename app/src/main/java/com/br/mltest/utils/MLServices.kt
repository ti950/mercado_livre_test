package com.br.mltest.utils

import com.br.mltest.response.ResultBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MLServices {

    //search?q=Motorola%20G6
    @GET("search")
    fun getItems(
        @Query("q") q: String
    ): Call<ResultBodyResponse>

}