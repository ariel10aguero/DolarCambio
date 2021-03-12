package com.example.dolarcambio.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.dolarcambio.R
import com.example.dolarcambio.closeKeyboard
import com.example.dolarcambio.databinding.FragmentBuyBinding

class BuyFragment : Fragment() {

    private var _binding: FragmentBuyBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBuyBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.backArrowBuy.setOnClickListener {
            closeKeyboard(it)
            findNavController().navigate(R.id.action_buyFragment_to_chooseFragment)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}