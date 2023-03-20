package com.example.repositoryassignment.model.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.repositoryassignment.model.data.Retrofitdata
import com.example.repositoryassignment.model.utils.Retrofitservice

class Retrofitrepository(private val Retrofitservice:Retrofitservice) {

    val repositorylist = MutableLiveData<Retrofitdata>()
    val status = MutableLiveData<Boolean>()

    suspend fun getrepodata(name:String){
       val result = Retrofitservice.getrepo(name)
        if(result.body() != null){
            //Log.d(TAG, "getrepodata: "+result.body().toString())
            repositorylist.postValue(result.body())
            status.postValue(true)
        }else{
            status.postValue(false)
        }
    }
}