package com.br.mltest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.mltest.R
import com.br.mltest.model.Item
import com.br.mltest.utils.Utils
import kotlinx.android.synthetic.main.item_search.view.*

class ResultAdapter(private val items: List<Item>,
                    val onItemClickListener: ((item: Item) -> Unit)
) : RecyclerView.Adapter<ResultAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return ItemViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        viewHolder.bindView(items[position])
    }

    class ItemViewHolder(
        itemView: View,
        private val onItemClickListener: ((item: Item) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.title
        private val author = itemView.price

        fun bindView(item: Item){
            title.text = item.title
            author.text = Utils.currencyFormat(item.price)

            //itemclickListener
            itemView.setOnClickListener {
                onItemClickListener.invoke(item)
            }
        }
    }
}