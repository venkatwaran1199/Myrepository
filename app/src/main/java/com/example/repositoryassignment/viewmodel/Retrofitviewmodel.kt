package com.example.repositoryassignment.viewmodel


import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repositoryassignment.model.data.Retrofitdata
import com.example.repositoryassignment.model.repository.Retrofitrepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Retrofitviewmodel(private val retrofitrepository: Retrofitrepository):ViewModel() {

    val reporesult:LiveData<Retrofitdata>
     get() = retrofitrepository.repositorylist

    val status:LiveData<Boolean> = retrofitrepository.status

    fun getRepoName(name:String){
        viewModelScope.launch(Dispatchers.IO){
            retrofitrepository.getrepodata(name)
        }
    }

    fun validateuser(user:String,url:String):Boolean{
        return !(TextUtils.isEmpty(user) || TextUtils.isEmpty(url))
    }

}