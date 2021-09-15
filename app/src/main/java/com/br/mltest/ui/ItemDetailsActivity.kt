package com.br.mltest.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.br.mltest.R
import com.br.mltest.base.BaseActivity
import com.br.mltest.model.Item
import kotlinx.android.synthetic.main.activity_item_details.*
import kotlinx.android.synthetic.main.include_toolbar.*
import java.io.Serializable

class ItemDetailsActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)

        val title = intent.getStringExtra(EXTRA_TITLE)
        val item = intent.getSerializableExtra(EXTRA_ITEM) as Item

        setupToolbar(toolbarMain, R.string.title_search, title, showBackButton = true)

        setFields(item)

    }

    private fun setFields(item: Item){
        txTitle.text = item.title
        txSeller.text = item.seller.permalink
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
        private const val EXTRA_ITEM = "EXTRA_ITEM"

        fun getStartIntent(context: Context, title: String, item: Item) : Intent {
            return Intent(context, ItemDetailsActivity::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_ITEM, item as Serializable)
            }
        }
    }

}