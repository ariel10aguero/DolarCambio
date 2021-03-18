package com.example.dolarcambio.ui

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dolarcambio.R
import com.example.dolarcambio.Utils.SwipeDelete
import com.example.dolarcambio.data.Transaction
import com.example.dolarcambio.data.local.LocalDataSource
import com.example.dolarcambio.data.local.TransDatabase
import com.example.dolarcambio.databinding.FragmentHomeBinding
import com.example.dolarcambio.domain.RepoImplement
import com.example.dolarcambio.viewmodel.MainViewModel
import com.example.dolarcambio.viewmodel.ViewModelFactory


class HomeFragment : Fragment(), RecyclerAdapter.OnClickRowListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var recyclerAdapter: RecyclerAdapter
    private var dbAllList = mutableListOf<Transaction>()
    lateinit var dbSellList: MutableList<Transaction>
    lateinit var dbBuyList: MutableList<Transaction>

    private val viewModel by activityViewModels<MainViewModel>{ViewModelFactory(RepoImplement(
        LocalDataSource(TransDatabase.getInstance(requireContext().applicationContext))
    ))}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        recyclerAdapter = RecyclerAdapter(this)

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

        setUpSpinner()
        setUpButtons()
        setUpRecycerView()
        setUpDbObserver()

        ItemTouchHelper(SwipeDelete(recyclerAdapter,requireContext())).attachToRecyclerView(binding.recyclerView)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpSpinner() {
        val spinner = binding.spinnerHome
        val spinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.transaction_type,
            R.layout.spinner_custom
        )
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0 -> recyclerAdapter.setList(dbAllList)
                    1 -> recyclerAdapter.setList(dbBuyList)
                    2 -> recyclerAdapter.setList(dbSellList)
                }
                recyclerAdapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun setUpButtons(){

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_chooseFragment)
        }

        binding.bottomAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.convert_calculator -> {
                    findNavController().navigate(R.id.action_homeFragment_to_calculatorFragment)
                    true
                }
                else -> false
            }
        }

    }

    private fun setUpRecycerView(){

        binding.recyclerView.adapter = recyclerAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setUpDbObserver(){
        viewModel.readAllData.observe(viewLifecycleOwner, Observer {dbList ->
            dbAllList = dbList
            dbSellList = dbList.filter { trans:  Transaction -> trans.type == 0 } as MutableList<Transaction>
            dbBuyList = dbList.filter { trans:  Transaction -> trans.type == 1 } as MutableList<Transaction>

            recyclerAdapter.setList(dbList)
            recyclerAdapter.notifyDataSetChanged()


        })
    }


    override fun onClickRow(trans: Transaction) {

        val sellAction = HomeFragmentDirections.actionHomeFragmentToSellFragment(trans)
        val buyAction = HomeFragmentDirections.actionHomeFragmentToBuyFragment(trans)

      when(trans.type){
          0 -> findNavController().navigate(sellAction)
          1 -> findNavController().navigate(buyAction)
      }
    }


}
