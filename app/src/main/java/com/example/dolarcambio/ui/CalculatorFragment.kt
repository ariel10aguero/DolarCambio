package com.example.dolarcambio.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.example.dolarcambio.formatCalculatorCurrency
import com.example.dolarcambio.viewmodel.MainViewModel
import com.example.dolarcambio.viewmodel.ViewModelFactory
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


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
        formatUserInput()
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

    private fun formatUserInput() {
        binding.apply {
            var isBackspacePressed = false

            convertNumInput.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    isBackspacePressed = count > after
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    // Do nothing
                }

                override fun afterTextChanged(s: Editable) {
                    if (!isBackspacePressed) {
                        convertNumInput.removeTextChangedListener(this)

                        val formattedNumber = formatNumberWithDot(s.toString())
                        convertNumInput.setText(formattedNumber)
                        convertNumInput.setSelection(formattedNumber.length)

                        convertNumInput.addTextChangedListener(this)
                    }
                    isBackspacePressed = false
                }
            })
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

    fun formatNumberWithDot(numberString: String): String {
        val number = numberString.filter { it.isDigit() }.toLongOrNull()
        return if (number != null) {
            val spanishLocale = Locale("es", "ES")
            val numberFormat = NumberFormat.getNumberInstance(spanishLocale)
            numberFormat.isGroupingUsed = true
            numberFormat.maximumFractionDigits = 0
            numberFormat.format(number)
        } else {
            "Invalid input"
        }
    }

    fun currencyConverter(type: Int, apiBlueData: Float, apiOficialData: Float, userInput: String) : String {
        var result: Float = 0F
        val numInput = userInput.filter { it.isDigit() }.toFloat()
        when(type){
            0 -> result = apiBlueData *  numInput
            1 -> result = apiOficialData * numInput
            2 -> result = numInput / apiBlueData
            3 -> result = numInput / apiOficialData
        }
        val currencyResult = formatCalculatorCurrency(result)

        return currencyResult
    }

    private fun getDolarApi() {
        viewModel.apply {
            getDolarSi()

            dolarSi.observe(viewLifecycleOwner, Observer
            { response ->
                if (response.isSuccessful) {
                    val dolarBlue = response.body()?.find { it.casa.nombre == "Dolar Blue" }
                    val dolarOficial = response.body()?.find { it.casa.nombre == "Dolar Oficial" }
                    apiBlueData = dolarBlue?.casa?.venta?.replace(",", ".")?.toFloat() ?: 0F
                    apiOficialData = dolarOficial?.casa?.venta?.replace(",", ".")?.toFloat() ?: 0F
                }
            })
        }
    }
}
