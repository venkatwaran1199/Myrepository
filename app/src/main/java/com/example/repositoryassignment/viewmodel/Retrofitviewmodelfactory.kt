package com.example.repositoryassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.repositoryassignment.model.repository.Retrofitrepository

class Retrofitviewmodelfactory(private val Retrofitrepository:Retrofitrepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
         return if (modelClass.isAssignableFrom(Retrofitviewmodel::class.java)) {
            Retrofitviewmodel(this.Retrofitrepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}