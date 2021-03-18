package com.example.dolarcambio.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dolarcambio.data.Transaction
import com.example.dolarcambio.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: Repository) : ViewModel() {

    val readAllData: LiveData<MutableList<Transaction>>

    init {
       readAllData = repo.getAllTransactions()
    }

    fun saveTransaction(transaction: Transaction){
        viewModelScope.launch(Dispatchers.IO) {
            repo.saveTransaction(transaction)
        }
    }

    fun deleteTransaction(transaction: Transaction){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteTransaction(transaction)
        }
    }


}