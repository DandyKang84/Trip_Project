package com.example.tirpappproject

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tirpappproject.databinding.FragmentTwoItemBinding

data class FragmentTwoAdapter(val dataList: MutableList<DataVoTwo>):
    RecyclerView.Adapter<FragmentTwoAdapter.CustomViewHolder>() {
    override fun getItemCount(): Int {
        return dataList.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = FragmentTwoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val customViewHolder = CustomViewHolder(binding)

        customViewHolder.itemView.setOnClickListener {
            val position: Int = customViewHolder.bindingAdapterPosition
            val dataVoTwo = dataList.get(position)
            Toast.makeText(parent.context,"[${dataVoTwo.title}]", Toast.LENGTH_SHORT).show()
        }
        customViewHolder.itemView.setOnLongClickListener {
            val position: Int = customViewHolder.bindingAdapterPosition
            val dataVoTwo = dataList.get(position)
            (parent.context as MainActivity).twoFragment.refreshRecyclerViewTwo(dataVoTwo)
            true
        }
        return customViewHolder
    }
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val binding = (holder as CustomViewHolder).binding
        val dataVoTwo = dataList.get(position)

        binding.fragTwoIvPicture.setImageResource(dataVoTwo.picture)
        binding.fragTwoTvTitle.text = dataVoTwo.title
        binding.fragTwoTvDay1.text = dataVoTwo.day1
        binding.fragTwoTvDay2.text = dataVoTwo.day2
        binding.fragTwoTvService1.text = dataVoTwo.service1
        binding.fragTwoTvService2.text = dataVoTwo.service2
        binding.fragTwoTvService3.text = dataVoTwo.service3
    }
    class CustomViewHolder(val binding: FragmentTwoItemBinding): RecyclerView.ViewHolder(binding.root)
}