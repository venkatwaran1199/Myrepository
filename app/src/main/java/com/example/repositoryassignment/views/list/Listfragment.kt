package com.example.repositoryassignment.views.list

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.repositoryassignment.R
import com.example.repositoryassignment.databinding.FragmentListBinding
import com.example.repositoryassignment.viewmodel.Responseviewmodel

class Listfragment : Fragment() {

    private var Lbinding:FragmentListBinding?=null
    private val binding get() = Lbinding!!
    private val RoomviewModel: Responseviewmodel by viewModels()
    private lateinit var recyclerView:RecyclerView
    private val adapter:RecyclerAdapter by lazy { RecyclerAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        Lbinding = FragmentListBinding.inflate(inflater, container, false)
        //menu
        setHasOptionsMenu(true)

        recyclerView =  binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        binding.responseviewmodel = RoomviewModel
        binding.lifecycleOwner = this

        binding.btnAddnewrepo.setOnClickListener {
            findNavController().navigate(R.id.action_listfragment_to_addFragment)
        }

        RoomviewModel.getallresponse.observe(viewLifecycleOwner) {
            adapter.setdata(it)
            RoomviewModel.checkemptydatabase(it)
        }

        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list,menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_list -> {findNavController().navigate(R.id.action_listfragment_to_addFragment)}
            R.id.delete_all -> {deleteallfromrepo()}
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteallfromrepo() {
        val builder = AlertDialog.Builder(context)
        builder.setPositiveButton("Yes"){
                _,_ ->
            RoomviewModel.deleteresponse()
            Toast.makeText(context,"Successfully deleted Everything!!", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_,_ ->}
        builder.setTitle("Delete Everything?")
        builder.setMessage("Are you sure you want to remove Everything?")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Lbinding = null
    }

}