package com.example.tirpappproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter11drawerviewpagerfragment.CustomDialog
import com.example.chapter11drawerviewpagerfragment.ReservationDialog
import com.example.tirpappproject.databinding.FragmentThreeBinding
import com.example.tirpappproject.databinding.FragmentThreeItemBinding

class ThreeFragment : Fragment() {
    lateinit var binding: FragmentThreeBinding
    var dataList = mutableListOf<DataVOThree>()
    lateinit var fragmentThreeAdapter: FragmentThreeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThreeBinding.inflate(inflater, container, false)
        mutableListOf<DataVOThree>()
        for (i in 1..10) {
            if (i%2 == 0) {
                dataList.add(DataVOThree("손흥민","해남 땅끝마을 펜션",R.drawable.son))
            } else {
                dataList.add(DataVOThree("아이유","제주 산방산탄산온천 이용권",R.drawable.iu))
            }
        }
        val linearLayoutManager = LinearLayoutManager(container?.context)
        fragmentThreeAdapter = FragmentThreeAdapter(dataList)
        binding.recyclerViewFragThree.layoutManager = linearLayoutManager
        binding.recyclerViewFragThree.adapter = fragmentThreeAdapter
        binding.btnFloating2.setOnClickListener {
            val dialog = ReservationDialog(binding.root.context)
            dialog.showDialog()
        }
        return binding.root
    }
    fun refreshRecyclerViewadd(dataVOThree: DataVOThree) {
        Toast.makeText(binding.root.context, "[${dataVOThree.name}]님 예약완료 되었습니다.", Toast.LENGTH_SHORT).show()
        dataList.add(dataVOThree)
        fragmentThreeAdapter.notifyDataSetChanged()
    }
    fun refreshRecyclerViewThree(dataVOThree: DataVOThree) {
        Toast.makeText(
            binding.root.context,
            "[${dataVOThree.name}]님이 삭제 되었습니다.",
            Toast.LENGTH_SHORT
        ).show()
        dataList.remove(dataVOThree)
        fragmentThreeAdapter.notifyDataSetChanged()
    }
}