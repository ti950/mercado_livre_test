package com.br.mltest.viewmodel

import android.content.Context
import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.br.mltest.R
import com.br.mltest.model.Item
import java.lang.IllegalArgumentException

class SearchViewModel(private val dataSource: SearchRepository) : ViewModel() {

    val itemLiveData: MutableLiveData<List<Item>> = MutableLiveData()
    val viewNetworkLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val viewFieldLiveData: MutableLiveData<Int> = MutableLiveData()
    val viewLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getSearchItem(query: String) {
        dataSource.searchItem(query) { result: ItemResult ->
            run {
                when (result) {
                    is ItemResult.Success -> {
                        itemLiveData.value = result.items
                    }
                    is ItemResult.ApiError -> {
                        if (result.statusCode == 401) {
                            viewFieldLiveData.value = R.string.error_401
                        } else {
                            viewFieldLiveData.value = R.string.error_400_generic
                        }
                    }
                    is ItemResult.ServerError -> {
                        viewFieldLiveData.value = R.string.error_500_generic
                    }
                }
            }
        }
    }

    fun getStatusNetwork(context: Context){
        val timer = object: CountDownTimer(999999, 2000) {
            override fun onTick(millisUntilFinished: Long) {
                viewNetworkLiveData.value = dataSource.network(context)
            }

            override fun onFinish() {
                getStatusNetwork(context)
            }
        }
        timer.start()
    }

    fun validateFields(query: String) {
        if(query.isEmpty()){
            viewFieldLiveData.value = R.string.query_empty
        } else {
            viewLoadingLiveData.value = true
            getSearchItem(query)
        }
    }

    class ViewModelFactory(val dataSource: SearchRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
                return SearchViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}