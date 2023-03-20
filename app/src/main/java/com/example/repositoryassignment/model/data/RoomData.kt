package com.example.repositoryassignment.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo_table")
data class RoomData(
    @PrimaryKey(autoGenerate = true,)
    var id:Int,
    var name:String?=null,
    var location:String?=null,
    var bio:String?=null,
    var url:String?=null,
    var avatar_url:String?=null
)
