package com.example.dolarcambio.utils

import android.view.View
import com.example.dolarcambio.databinding.FragmentHomeBinding

class ViewsVisibility(val binding: FragmentHomeBinding) {

    fun hideViews(){
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

}