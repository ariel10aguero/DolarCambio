package com.example.dolarcambio.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dolarcambio.data.Transaction
import com.example.dolarcambio.databinding.RowBuyBinding
import com.example.dolarcambio.databinding.RowEmptylistBinding
import com.example.dolarcambio.databinding.RowSellBinding


private const val SELL_ROW = 0
private const val BUY_ROW = 1
private const val EMPTY_ROW = 2

class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var transList = mutableListOf<Transaction>()

    fun setList(list: MutableList<Transaction>){
        transList = list
    }


    inner class EmptyList(val binding: RowEmptylistBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    inner class SellItem(val binding: RowSellBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(trans: Transaction) {
            binding.sellRowUsd.text = "U$" + trans.usd
            binding.sellRowArs.text = "$" + trans.ars
            binding.sellRowDate.text = trans.date

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

        val sellInflater =
            RowSellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val buyInflater = RowBuyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val emptyInflater =
            RowEmptylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        when (viewType) {
            SELL_ROW -> return SellItem(sellInflater)
            BUY_ROW -> return BuyItem(buyInflater)
            else -> return EmptyList(emptyInflater)

        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            0 -> (holder as SellItem).bind(transList[position])
            1 -> (holder as BuyItem).bind(transList[position])
            else -> holder as EmptyList
        }

    }

    override fun getItemCount(): Int {
        return transList.size
    }
}