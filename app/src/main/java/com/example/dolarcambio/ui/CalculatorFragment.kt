package com.example.dolarcambio.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import com.example.dolarcambio.R
import com.example.dolarcambio.databinding.FragmentCalculatorBinding


class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSpinner()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setUpSpinner() {
        val spinner = binding.spinnerConverter
        val spinnerConverterAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.converter_type,
            R.layout.spinner_calculator
        )
        spinnerConverterAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = spinnerConverterAdapter
    }


}