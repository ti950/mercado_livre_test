package com.br.mltest.utils

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


// Object faz a classe ser um singleton
object ApiService {

    private fun initRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/sites/MLB/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val service: MLServices = initRetrofit().create(MLServices::class.java)
}