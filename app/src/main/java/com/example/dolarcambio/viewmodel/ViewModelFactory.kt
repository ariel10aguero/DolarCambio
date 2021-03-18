package com.example.dolarcambio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dolarcambio.domain.Repository

class ViewModelFactory(private val repo: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repository::class.java).newInstance(repo)
    }
}