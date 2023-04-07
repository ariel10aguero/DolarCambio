package com.example.dolarcambio.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dolarcambio.data.DolarApi
import com.example.dolarcambio.data.DolarSi
import com.example.dolarcambio.data.Transaction
import com.example.dolarcambio.domain.Repository
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repo: Repository) : ViewModel() {

    val readAllData: LiveData<MutableList<Transaction>>
    val dolarOficial: MutableLiveData<Response<DolarApi>> = MutableLiveData()
    val dolarBlue: MutableLiveData<Response<DolarApi>> = MutableLiveData()
    val dolarSi: MutableLiveData<Response<List<DolarSi>>> = MutableLiveData()
    val status: MutableLiveData<Boolean> = MutableLiveData()

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

    fun getDolarSi(){
        viewModelScope.launch {
            try {
                dolarSi.value = repo.getDolarSi()
                status.value = true
            }
            catch (e: Exception){
                status.value = false
            }

        }
    }

//    fun getDolarOficial(){
//        viewModelScope.launch {
//            try {
//                dolarOficial.value = repo.getDolarOficial()
//                status.value = true
//            }
//            catch (e: Exception){
//                status.value = false
//            }
//
//        }
//    }

//    fun getDolarBlue(){
//        viewModelScope.launch {
//            try {
//               dolarBlue.value = repo.getDolarBlue()
//            }
//            catch (e: Exception){
//            }
//
//        }
//    }

    suspend fun delayYop(){
        viewModelScope.launch {
            delay(5000)
        }
    }

}