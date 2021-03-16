package com.example.dolarcambio.ui

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dolarcambio.CalendarUtils
import com.example.dolarcambio.R
import com.example.dolarcambio.closeKeyboard
import com.example.dolarcambio.databinding.FragmentBuyBinding

class BuyFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private var _binding: FragmentBuyBinding? = null
    private val binding get() = _binding!!
    private val calendarUtils = CalendarUtils()
    private val args by navArgs<BuyFragmentArgs>()


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

        setButtonsAndCalendar()
        navArgsBinding()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendarUtils.year = year
        calendarUtils.month = month
        calendarUtils.day = dayOfMonth
        val realMonth = month + 1

        binding.buyDateInput.text = "$dayOfMonth/$realMonth/$year"

    }

    private fun setButtonsAndCalendar(){

        binding.backArrowBuy.setOnClickListener {
            closeKeyboard(it)
            findNavController().navigate(R.id.action_buyFragment_to_chooseFragment)
        }

        binding.buyDateInput.text = calendarUtils.setBtnDate()
        binding.buyDateInput.setOnClickListener {
            DatePickerDialog(requireContext(),this, calendarUtils.year, calendarUtils.month, calendarUtils.day).show()
        }
        binding.buySaveBtn.setOnClickListener {
            findNavController().navigate(R.id.action_buyFragment_to_homeFragment)
        }

    }

    private fun navArgsBinding() {
        if (args.buyArgs != null) {
            binding.buyUsdInput.setText(args.buyArgs?.usd)
            binding.buyArsInput.setText(args.buyArgs?.ars)
            binding.buyDateInput.text = args.buyArgs?.date
        }
    }


}