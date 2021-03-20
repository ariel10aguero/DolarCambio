package com.example.dolarcambio.ui

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.dolarcambio.R
import com.example.dolarcambio.closeKeyboard
import com.example.dolarcambio.data.local.LocalDataSource
import com.example.dolarcambio.data.local.TransDatabase
import com.example.dolarcambio.data.remote.RemoteDataSource
import com.example.dolarcambio.data.remote.RetrofitInstance
import com.example.dolarcambio.databinding.FragmentCalculatorBinding
import com.example.dolarcambio.domain.RepoImplement
import com.example.dolarcambio.viewmodel.MainViewModel
import com.example.dolarcambio.viewmodel.ViewModelFactory
import java.text.DecimalFormat


class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!
    var apiBlueData = 0F
    var apiOficialData = 0F
    var type: Int = 0
    private val viewModel by activityViewModels<MainViewModel> {
        ViewModelFactory(
            RepoImplement(
                LocalDataSource(TransDatabase.getInstance(requireContext().applicationContext)),
                RemoteDataSource(RetrofitInstance.webService)
            )
        )
    }

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
        setUpButtons()
        getDolarApi()
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
        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> type = 0
                    1 -> type = 1
                    2 -> type = 2
                    3 -> type = 3
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }

    private fun setUpButtons(){

        binding.convertBtn.setOnClickListener {
            val userInput = binding.convertNumInput.editableText.toString()
            if (userInput.isNotBlank()) {
                binding.convertResult.text = currencyConverter(type, apiBlueData, apiOficialData, userInput)
                binding.convertResult.visibility = View.VISIBLE
            } else Toast.makeText(requireContext(), "Completá el campo de importe", Toast.LENGTH_SHORT).show()

        }
        binding.backArrowConverter.setOnClickListener{
            closeKeyboard(it)
            findNavController().navigate(R.id.action_calculatorFragment_to_homeFragment)
        }
    }

    fun currencyConverter(type: Int, apiBlueData: Float, apiOficialData: Float, userInput: String) : String {
        var result: Float = 0F
        var userDecimal = DecimalFormat(".00").format(userInput.toFloat())

        when(type){
            0 -> result = apiBlueData * userDecimal.toFloat()
            1 -> result = apiOficialData * userDecimal.toFloat()
            2 -> result = userDecimal.toFloat() / apiBlueData
            3 -> result = userDecimal.toFloat() / apiOficialData
        }
        val currencyResult = DecimalFormat("$###,###.###").format(result)

        return "${currencyResult}"
    }

    private fun getDolarApi() {
        viewModel.apply {
            getDolarOficial()
            getDolarBlue()

            dolarOficial.observe(viewLifecycleOwner, Observer
            { response ->
                if (response.isSuccessful) {
                    apiOficialData = response.body()?.venta.toString().toFloat()
                }
            })

            dolarBlue.observe(viewLifecycleOwner, Observer { response ->
                if(response.isSuccessful){
                    apiBlueData = response.body()?.venta.toString().toFloat()

                }
            })
        }
    }


}