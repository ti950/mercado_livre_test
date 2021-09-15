package com.br.mltest.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.mltest.R
import com.br.mltest.base.BaseActivity
import com.br.mltest.model.Item
import kotlinx.android.synthetic.main.activity_search_results.*
import kotlinx.android.synthetic.main.include_toolbar.*
import java.io.Serializable

class SearchResultsActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)

        val title = intent.getStringExtra(EXTRA_TITLE)
        val items = intent.getSerializableExtra(EXTRA_ITEMS) as List<Item>

        setupToolbar(toolbarMain, R.string.title_search, title, showBackButton = true)

        with(recyclerItems) {
            layoutManager = LinearLayoutManager(this@SearchResultsActivity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = ResultAdapter(items) { i ->
                val intent = ItemDetailsActivity.getStartIntent(this@SearchResultsActivity, i.id, i)
                this@SearchResultsActivity.startActivity(intent)
            }
        }

    }

    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                this.finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_ITEMS = "EXTRA_ITEMS"

        fun getStartIntent(context: Context, title: String, items: List<Item>) : Intent {
            return Intent(context, SearchResultsActivity::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_ITEMS, items as Serializable)
            }
        }
    }
}