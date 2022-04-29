package com.example.potteryapp.views


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.potteryapp.R
import com.example.potteryapp.model.Formula
import com.example.potteryapp.model.Item

class ItemAdapter() :
    ListAdapter<Item, ItemAdapter.ViewHolder>(ItemsDiffCallback) {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var tvName = view.findViewById<TextView>(R.id.tvName)
        var tvAmount = view.findViewById<TextView>(R.id.tvAmount)
        var tvDescription = view.findViewById<TextView>(R.id.tvDescription)

        fun bind(item : Item){
            tvName.text = item.name
            tvAmount.text = item.amount.toString()
            tvDescription.text = item.description
        }
    }



    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_recycler, viewGroup , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}

object ItemsDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }
}