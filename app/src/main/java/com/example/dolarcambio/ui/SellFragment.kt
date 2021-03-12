package com.example.dolarcambio.ui

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.navigation.fragment.findNavController
import com.example.dolarcambio.CalendarUtils
import com.example.dolarcambio.R
import com.example.dolarcambio.closeKeyboard
import com.example.dolarcambio.databinding.FragmentBuyBinding
import com.example.dolarcambio.databinding.FragmentSellBinding


class SellFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private var _binding: FragmentSellBinding? = null
    private val binding get() = _binding!!
    private val calendarUtils = CalendarUtils()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSellBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backArrowSell.setOnClickListener {
            closeKeyboard(it)
            findNavController().navigate(R.id.action_sellFragment_to_chooseFragment)
        }

        binding.sellDateBtn.text = calendarUtils.setBtnDate()
        binding.sellDateBtn.setOnClickListener {
            DatePickerDialog(requireContext(),this, calendarUtils.year, calendarUtils.month, calendarUtils.day).show()
        }
        binding.sellSaveBtn.setOnClickListener {
            findNavController().navigate(R.id.action_sellFragment_to_homeFragment)
        }
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

        binding.sellDateBtn.text = "$dayOfMonth/$realMonth/$year"
    }
}