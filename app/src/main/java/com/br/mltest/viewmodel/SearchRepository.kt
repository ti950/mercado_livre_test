package com.br.mltest.viewmodel

import android.content.Context

interface SearchRepository {

    fun searchItem(query:String, itemResultsCallback: (result: ItemResult) -> Unit)
    fun network(context: Context): Boolean

}