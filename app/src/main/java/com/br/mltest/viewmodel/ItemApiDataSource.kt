package com.br.mltest.viewmodel

import android.content.Context
import android.util.Log
import com.br.mltest.model.Item
import com.br.mltest.response.ResultBodyResponse
import com.br.mltest.utils.ApiService
import com.br.mltest.utils.Connectivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemApiDataSource : SearchRepository {

    override fun searchItem(query: String, itemResultsCallback: (ItemResult) -> Unit) {
        ApiService.service.getItems(query).enqueue(object : Callback<ResultBodyResponse>{
            override fun onResponse(
                call: Call<ResultBodyResponse>,
                response: Response<ResultBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        //Log.d("TAG", response.body().toString())
                        val items: MutableList<Item> = mutableListOf()

                        response.body()?.let { itemBodyResponse ->
                            for (result in itemBodyResponse.itemResults) {
                                // essa linha substitui as linhas comentadas
                                val item = result.getItemModel()
                                items.add(item)
                            }
                        }

                        itemResultsCallback(ItemResult.Success(items))
                    }
                    else -> itemResultsCallback(ItemResult.ApiError(response.code()))
                }
            }

            override fun onFailure(call: Call<ResultBodyResponse>, t: Throwable) {
                itemResultsCallback(ItemResult.ServerError)
            }
        })
    }

    override fun network(context: Context): Boolean {
        return Connectivity().isConnected(context)
    }

}