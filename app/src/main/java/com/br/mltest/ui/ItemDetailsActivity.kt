package com.br.mltest.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.br.mltest.R
import com.br.mltest.base.BaseActivity
import com.br.mltest.model.Item
import com.br.mltest.utils.Utils
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
        txAvailable.text = getString(R.string.available, item.available_quantity)
        txSold.text = getString(R.string.sold, item.sold_quantity)
        txCondition.text = getString(R.string.condition, Utils.toPortuguese(item.condition))
        txAddress.text = getString(R.string.address, item.address.city_name, item.address.state_name)
        txPrice.text = getString(R.string.price_value, Utils.currencyFormat(item.price))
        txInstallments.text = getString(R.string.installments,
            item.installments.quantity.toString(),
            Utils.currencyFormat(item.installments.amount.toString()))
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