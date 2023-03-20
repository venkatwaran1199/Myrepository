package com.example.repositoryassignment.views.add

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.repositoryassignment.R
import com.example.repositoryassignment.databinding.FragmentAddBinding
import com.example.repositoryassignment.model.data.RoomData
import com.example.repositoryassignment.model.repository.Retrofitrepository
import com.example.repositoryassignment.model.utils.Retrofitservice
import com.example.repositoryassignment.viewmodel.Retrofitviewmodel
import com.example.repositoryassignment.viewmodel.Retrofitviewmodelfactory
import com.example.repositoryassignment.viewmodel.Responseviewmodel

class AddFragment : Fragment() {

    private var Abinding:FragmentAddBinding?=null
    private val binding get() = Abinding!!

    lateinit var RerofitviewModel: Retrofitviewmodel
    val RoomviewModel: Responseviewmodel by viewModels()
    private val retrofitService = Retrofitservice.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        Abinding =  FragmentAddBinding.inflate(inflater, container, false)

        RerofitviewModel = ViewModelProvider(this,Retrofitviewmodelfactory(Retrofitrepository(retrofitService))).get(Retrofitviewmodel::class.java)
        RerofitviewModel.reporesult.observe(viewLifecycleOwner) {
            val roomdata=RoomData(0,it.name,it.location,it.bio,it.html_url,it.avatar_url)
            RoomviewModel.insertResponse(roomdata)
        }

        RerofitviewModel.status.observe(viewLifecycleOwner) {
            if (it == true) {
                Toast.makeText(context, "Successfully Added repository", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addFragment_to_listfragment)
            } else {
                Toast.makeText(context, "Please enter a valid repo name", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnAddrepo.setOnClickListener {
            gettingdatafromserver()
        }
        return binding.root
    }

    private fun gettingdatafromserver() {
        val name = binding.edtusername.text.toString()
        val url =  binding.edtuserurl.text.toString()

        val validation = RerofitviewModel.validateuser(name,url)
        if(validation){
            RerofitviewModel.getRepoName(binding.edtuserurl.text.toString())
        }else{
            Toast.makeText(context,"Please fill out all the Fields",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Abinding = null
    }

}