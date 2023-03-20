package com.example.repositoryassignment.views.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.repositoryassignment.databinding.RepoContentBinding
import com.example.repositoryassignment.model.data.RoomData

class RecyclerAdapter:RecyclerView.Adapter<RecyclerAdapter.MyViewholder>() {

    var repodata = mutableListOf<RoomData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val binding=RepoContentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewholder(binding)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        val curritem = repodata[position]
        holder.bind(curritem)
    }

    override fun getItemCount(): Int {
        return repodata.size
    }

    fun setdata(repodata:List<RoomData>){
        this.repodata = repodata.toMutableList()
        notifyDataSetChanged()
    }

    class MyViewholder(private val binding: RepoContentBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(repodata:RoomData){
            binding.repolistdata = repodata
            binding.executePendingBindings()
        }

    }
}