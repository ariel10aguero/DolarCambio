package com.example.dolarcambio.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.dolarcambio.R
import com.example.dolarcambio.databinding.FragmentBuyBinding
import com.example.dolarcambio.databinding.FragmentChooseBinding


class ChooseFragment : Fragment() {

    private var _binding: FragmentChooseBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buyChoose.setOnClickListener {
            findNavController().navigate(R.id.action_chooseFragment_to_buyFragment)
        }

        binding.sellChoose.setOnClickListener {
            findNavController().navigate(R.id.action_chooseFragment_to_sellFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}