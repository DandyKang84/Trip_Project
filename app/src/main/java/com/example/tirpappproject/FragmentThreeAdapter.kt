package com.example.tirpappproject

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tirpappproject.databinding.FragmentThreeItemBinding

class FragmentThreeAdapter(val dataList: MutableList<DataVOThree>):
    RecyclerView.Adapter<FragmentThreeAdapter.CustomViewHolder>() {
    override fun getItemCount(): Int {
        return dataList.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentThreeAdapter.CustomViewHolder {
        val binding = FragmentThreeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val customViewHolder = FragmentThreeAdapter.CustomViewHolder(binding)

        customViewHolder.itemView.setOnClickListener {
            val position: Int = customViewHolder.bindingAdapterPosition
            val dataVOThree = dataList.get(position)
            Toast.makeText(parent.context,"[${dataVOThree.name}]님 예약이 완료되었습니다", Toast.LENGTH_SHORT).show()
        }
        customViewHolder.itemView.setOnLongClickListener {
            val position: Int = customViewHolder.bindingAdapterPosition
            val dataVOThree = dataList.get(position)
            (parent.context as MainActivity).threeFragment.refreshRecyclerViewThree(dataVOThree)
            true
        }
        return customViewHolder
    }
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val binding = (holder as FragmentThreeAdapter.CustomViewHolder).binding
        val dataVOThree = dataList.get(position)
        binding.fragThreeIvPicture.setImageResource(dataVOThree.picture)
        binding.fragThreeTvName.text = dataVOThree.name
        binding.fragThreeTvReservation.text = dataVOThree.reservation
    }
        class CustomViewHolder(val binding: FragmentThreeItemBinding) :RecyclerView.ViewHolder(binding.root)
}