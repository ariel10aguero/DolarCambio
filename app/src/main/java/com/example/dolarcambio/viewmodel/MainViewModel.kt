package com.example.dolarcambio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dolarcambio.data.Transaction
import com.example.dolarcambio.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: Repository) : ViewModel() {

    fun saveTransaction(transaction: Transaction){
        viewModelScope.launch(Dispatchers.IO) {
            repo.saveTransaction(transaction)
        }
    }
}