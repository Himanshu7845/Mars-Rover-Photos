package com.reapairsduniya.unorgassingment.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reapairsduniya.unorgassingment.databinding.SinglerowBinding
import com.reapairsduniya.unorgassingment.model.alldatamodel.AllDataModel

class MyAdapter(val context: Context,val i1:I1):ListAdapter<AllDataModel,MyAdapter.MyViewHolder>(MyDiffUtil())
{
    inner class MyViewHolder(val binding:SinglerowBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(posi: AllDataModel)
        {
            Glide.with(context).load(posi.img_src).into(binding.img)
            Log.d("imgSRC", "onBindViewHolder: ${posi.img_src}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        val binding=SinglerowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val posi = getItem(position)
        holder.bind(posi)
        holder.binding.card.setOnClickListener {
            i1.getAllData(posi,position)
        }
    }

    class MyDiffUtil :DiffUtil.ItemCallback<AllDataModel>(){
        override fun areItemsTheSame(oldItem: AllDataModel, newItem: AllDataModel): Boolean {
            return oldItem.img_src==newItem.img_src
        }

        override fun areContentsTheSame(oldItem: AllDataModel, newItem: AllDataModel): Boolean {
           return oldItem==newItem
        }

    }

    interface I1{
        fun getAllData(list: AllDataModel, position: Int)
    }

}