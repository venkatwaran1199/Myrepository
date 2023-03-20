package com.example.repositoryassignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.repositoryassignment.model.Database.RoomDB
import com.example.repositoryassignment.model.data.RoomData
import com.example.repositoryassignment.model.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Responseviewmodel(application: Application):AndroidViewModel(application) {

private val responseDao = RoomDB.getDatabase(application.applicationContext).ResponseDao()

    val emptydatabase = MutableLiveData(false)

    private val responserepository:RoomRepository = RoomRepository(responseDao)


    val getallresponse : LiveData<List<RoomData>> = responserepository.getalldata
    fun checkemptydatabase(roomdata:List<RoomData>){
        emptydatabase.value = roomdata.isEmpty()
    }

/*    init {
        responserepository = RoomRepository(responseDao)
        getallresponse = responserepository.getalldata
    }*/

    fun insertResponse(responsedata:RoomData){
        viewModelScope.launch(Dispatchers.IO) {
            responserepository.insertdata(responsedata)
        }
    }

    fun deleteresponse(){
        viewModelScope.launch(Dispatchers.IO) {
            responserepository.deleteallresponse()
        }
    }
}