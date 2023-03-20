package com.example.repositoryassignment.model.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.repositoryassignment.model.data.RoomData

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addResponse(responsedata:RoomData)

    @Query("SELECT * FROM repo_table")
    fun getAllResponse():LiveData<List<RoomData>>

    @Query("DELETE FROM repo_table")
    suspend fun deleteallresponse()

}