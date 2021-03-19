package com.example.dolarcambio.utils

import android.view.View
import com.example.dolarcambio.databinding.FragmentHomeBinding

class ViewsVisibility(val binding: FragmentHomeBinding) {

    fun showViews(){
        binding.apply {
            buyBlueNum.visibility = View.VISIBLE
            buyOficialNum.visibility = View.VISIBLE
            sellBlueNum.visibility = View.VISIBLE
            sellOficialNum.visibility = View.VISIBLE
            compraBlue.visibility = View.VISIBLE
            compraOficial.visibility = View.VISIBLE
            ventaBlue.visibility = View.VISIBLE
            ventaOficial.visibility = View.VISIBLE
            dateLastUpdate.visibility = View.VISIBLE

        }
    }

    fun hideViews(){
        binding.apply {
            buyBlueNum.visibility = View.INVISIBLE
            buyOficialNum.visibility = View.INVISIBLE
            sellBlueNum.visibility = View.INVISIBLE
            sellOficialNum.visibility = View.INVISIBLE
            compraBlue.visibility = View.INVISIBLE
            compraOficial.visibility = View.INVISIBLE
            ventaBlue.visibility = View.INVISIBLE
            ventaOficial.visibility = View.INVISIBLE
            dateLastUpdate.visibility = View.INVISIBLE

        }
    }


}