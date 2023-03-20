package com.example.repositoryassignment.model.repository

import androidx.lifecycle.LiveData
import com.example.repositoryassignment.model.Database.RoomDao
import com.example.repositoryassignment.model.data.RoomData

class RoomRepository(private val responsedao:RoomDao) {

    val getalldata:LiveData<List<RoomData>> = responsedao.getAllResponse()

    suspend fun insertdata(responsedata:RoomData){
        responsedao.addResponse(responsedata)
    }

    suspend fun deleteallresponse(){
        responsedao.deleteallresponse()
    }
}