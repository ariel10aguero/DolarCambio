package com.example.dolarcambio.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.dolarcambio.R
import com.example.dolarcambio.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_chooseFragment)
        }

        binding.bottomAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.convert_calculator -> {
                    Log.d("anda", "sisi")
                    findNavController().navigate(R.id.action_homeFragment_to_calculatorFragment)
                    true
                }
                else -> false
            }


        }

    }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

        fun setUpSpinner() {
            val spinner = binding.spinnerHome
            val spinnerAdapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.transaction_type,
                R.layout.spinner_custom
            )
            spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            spinner.adapter = spinnerAdapter
        }

        override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
            inflater.inflate(R.menu.home_menu, menu)
            super.onCreateOptionsMenu(menu, inflater)

        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {

            if (item.itemId == R.id.convert_calculator) {

                Log.d("onda", "mala")
                Toast.makeText(requireContext(), "yoop", Toast.LENGTH_SHORT).show()

            }
            return super.onOptionsItemSelected(item)
        }
}
