package com.example.dolarcambio.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dolarcambio.CalendarUtils
import com.example.dolarcambio.R
import com.example.dolarcambio.closeKeyboard
import com.example.dolarcambio.data.Transaction
import com.example.dolarcambio.data.local.LocalDataSource
import com.example.dolarcambio.data.local.TransDatabase
import com.example.dolarcambio.data.remote.RemoteDataSource
import com.example.dolarcambio.data.remote.RetrofitInstance
import com.example.dolarcambio.databinding.FragmentBuyBinding
import com.example.dolarcambio.domain.RepoImplement
import com.example.dolarcambio.viewmodel.MainViewModel
import com.example.dolarcambio.viewmodel.ViewModelFactory

class BuyFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private var _binding: FragmentBuyBinding? = null
    private val binding get() = _binding!!
    private val calendarUtils = CalendarUtils()
    private val args by navArgs<BuyFragmentArgs>()

    private val viewModel by activityViewModels<MainViewModel>{ViewModelFactory(RepoImplement(
        LocalDataSource(TransDatabase.getInstance(requireContext().applicationContext)), RemoteDataSource(
            RetrofitInstance.webService)
    ))}

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
            if (args.buyArgs != null) {
                updateTransaction()
            } else
                insertTransaction()
        }

    }

    private fun navArgsBinding() {
        if (args.buyArgs != null) {
            binding.buyUsdInput.setText(args.buyArgs?.usd)
            binding.buyArsInput.setText(args.buyArgs?.ars)
            binding.buyDateInput.text = args.buyArgs?.date
        }
    }

    private fun insertTransaction(){
        val usd = binding.buyUsdInput.text
        val ars = binding.buyArsInput.text
        val date = binding.buyDateInput.text.toString()

        if(checkNull(usd,ars,date)){
            val buyTransaction = Transaction(0,1,usd.toString(),ars.toString(),date)
            viewModel.saveTransaction(buyTransaction)
            findNavController().navigate(R.id.action_buyFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "Completá todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateTransaction(){
        val usd = binding.buyUsdInput.text
        val ars = binding.buyArsInput.text
        val date = binding.buyDateInput.text.toString()

        if(checkNull(usd,ars,date)){
            val buyTransaction = args.buyArgs?.let { Transaction(it.id,1,usd.toString(),ars.toString(),date) }
            viewModel.saveTransaction(buyTransaction!!)
            findNavController().navigate(R.id.action_buyFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "Completá todos los campos", Toast.LENGTH_SHORT).show()
        }

    }

    private fun checkNull(usd: Editable, ars: Editable, date: String): Boolean{
        return !(usd.isBlank() || ars.isBlank() || date.isBlank())
    }

}