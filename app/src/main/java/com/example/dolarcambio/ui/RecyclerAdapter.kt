package com.example.dolarcambio.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dolarcambio.data.Transaction
import com.example.dolarcambio.databinding.RowBuyBinding
import com.example.dolarcambio.databinding.RowEmptylistBinding
import com.example.dolarcambio.databinding.RowSellBinding


private const val SELL_ROW = 0
private const val BUY_ROW = 1
private const val EMPTY_ROW = 2

class RecyclerAdapter(private val onClick: OnClickRowListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var transList = mutableListOf<Transaction>()

    fun setList(list: MutableList<Transaction>){
        transList = list
    }

    interface OnClickRowListener{
        fun onClickRow(trans: Transaction)
    }

    inner class EmptyList(val binding: RowEmptylistBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindEmpty(){
            binding.textView3.text = "Esta vacio"
        }

    }

    inner class SellItem(val binding: RowSellBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(trans: Transaction) {
            binding.sellRowUsd.text = "U$" + trans.usd
            binding.sellRowArs.text = "$" + trans.ars
            binding.sellRowDate.text = trans.date
        }

        init {
            itemView.setOnClickListener {
                Log.d("click", "click")
            }
        }

    }

    inner class BuyItem(val binding: RowBuyBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(trans: Transaction) {
            binding.buyRowUsd.text = "U$" + trans.usd
            binding.buyRowArs.text = "$" + trans.ars
            binding.buyRowDate.text = trans.date
        }

    }


    override fun getItemViewType(position: Int): Int {

        when (transList[position].type) {
            0 -> return SELL_ROW
            1 -> return BUY_ROW
            else -> return EMPTY_ROW
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val sellInflater = RowSellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val buyInflater = RowBuyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val emptyInflater = RowEmptylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        when (viewType) {
            SELL_ROW -> return SellItem(sellInflater)
            BUY_ROW -> return BuyItem(buyInflater)
            else -> return EmptyList(emptyInflater)

        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentItem = transList[position]

        when (getItemViewType(position)) {
            0 -> (holder as SellItem).bind(currentItem)
            1 -> (holder as BuyItem).bind(currentItem)
            else -> (holder as EmptyList).bindEmpty()
        }

        holder.itemView.setOnClickListener {
            onClick.onClickRow(currentItem)
        }

    }

    override fun getItemCount(): Int {
        return transList.size
    }
}