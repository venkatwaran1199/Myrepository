package com.example.repositoryassignment.model.utils

import com.example.repositoryassignment.model.data.Retrofitdata
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface Retrofitservice {

    @GET("users/{name}")
    suspend fun getrepo(@Path ("name") name:String) : Response<Retrofitdata>

    companion object{
        val baseUrl = "https://api.github.com/"

        private var RetrofitService: Retrofitservice? = null

        fun getInstance(): Retrofitservice {
            if (RetrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                RetrofitService = retrofit.create(Retrofitservice::class.java)
            }
            return RetrofitService!!
        }
    }
}