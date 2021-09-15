package com.br.mltest.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.br.mltest.R
import com.br.mltest.base.BaseActivity
import com.br.mltest.utils.hideKeyboard
import com.br.mltest.viewmodel.ItemApiDataSource
import com.br.mltest.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_toolbar.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar(toolbarMain, R.string.title_search)

        val viewModel: SearchViewModel =
            SearchViewModel.ViewModelFactory(ItemApiDataSource()).create(SearchViewModel::class.java)

        viewModel.itemLiveData.observe(this, Observer {
            it?.let { items ->

                pbLoading.visibility = View.GONE
                txInfo.visibility = View.GONE
                btnSearch.visibility = View.VISIBLE

                val intent = SearchResultsActivity.getStartIntent(this, edQuery.text.toString(), items)
                this@MainActivity.startActivity(intent)
            }
        })

        viewModel.viewNetworkLiveData.observe(this, Observer {
            if (it){
                constraintLayout.visibility = View.GONE
                btnSearch.isEnabled = true
                edQuery.isEnabled = true
            } else {
                constraintLayout.visibility = View.VISIBLE
                btnSearch.isEnabled = false
                edQuery.isEnabled = false
            }
        })

        viewModel.viewFieldLiveData.observe(this, Observer {
            Toast.makeText(this, getString(it), Toast.LENGTH_LONG).show()
            edQuery.isFocusable = true
        })

        viewModel.viewLoadingLiveData.observe(this, Observer {
            if (it){
                pbLoading.visibility = View.VISIBLE
                txInfo.visibility = View.VISIBLE
                btnSearch.visibility = View.INVISIBLE
            } else {
                pbLoading.visibility = View.GONE
                txInfo.visibility = View.GONE
                btnSearch.visibility = View.VISIBLE
            }
        })

        viewModel.getStatusNetwork(this)

        btnSearch.setOnClickListener{
            viewModel.validateFields(edQuery.text.toString())
            hideKeyboard(currentFocus ?: View(this))
        }
    }
}