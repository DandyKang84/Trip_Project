package com.example.tirpappproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tirpappproject.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {
    private lateinit var binding: FragmentTwoBinding
    var dataList = mutableListOf<DataVoTwo>()
    lateinit var fragmentTwoAdapter: FragmentTwoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoBinding.inflate(inflater, container, false)
        mutableListOf<DataVoTwo>()
        for (i in 1..10) {
            if (i%2 == 0) {
                dataList.add(
                    DataVoTwo(
                    "[할인코드 & 특가스케줄]",
                    "11.24 (목)",
                    "11.30 (수)",
                    "항공&호텔 50% 할인코드",
                    "진에어 단독특가 30% DC",
                    "프리미엄호텔 5만원부터 반짝 특가",
                        R.drawable.hootel1
                    )
                )
            } else {
                dataList.add(
                    DataVoTwo(
                    "[할인코드 & 특가스케줄]",
                    "10.24 (월)",
                    "10.30 (일)",
                    "호텔룸 50% 할인코드",
                    "다이닝레스토랑 30% DC",
                    "프리미엄호텔룸 10만원부터 반짝 특가", R.drawable.bg1
                    )
                )
                dataList.add(
                    DataVoTwo(
                    "[할인코드 & 특가스케줄]",
                    "11.06 (일)",
                    "11.30 (수)",
                    "호텔 50% 할인코드",
                    "아시아나항공 단독특가 30% DC",
                    "프리미엄호텔 15만원부터", R.drawable.bg2
                    )
                )
                dataList.add(
                    DataVoTwo(
                    "[할인코드 & 특가스케줄]",
                    "11.07 (월)",
                    "11.20 (일)",
                    "항공&호텔 50% 할인코드",
                    "진에어 특가 50% DC",
                    "OutDoor 5만원부터 반짝 특가",
                        R.drawable.outdoor
                    )
                )
                dataList.add(
                    DataVoTwo(
                    "[할인코드 & 특가스케줄]",
                    "12.01 (목)",
                    "12.04 (일)",
                    "항공&호텔 할인",
                    "진에어 단독특가 30% DC",
                    "프리미엄호텔 레스토랑 5만원부터 반짝 특가",
                        R.drawable.restaurant
                    )
                )
            }
            val linearLayoutManager = LinearLayoutManager(container?.context)
            fragmentTwoAdapter = FragmentTwoAdapter(dataList)
            binding.recyclerViewFragTwo.addItemDecoration(MyDecoration(binding.root.context))
            binding.recyclerViewFragTwo.layoutManager = linearLayoutManager
            binding.recyclerViewFragTwo.adapter = fragmentTwoAdapter
            }
            return binding.root
        }
        fun refreshRecyclerViewTwo(dataVoTwo: DataVoTwo) {
            Toast.makeText(
                binding.root.context,
                "${dataVoTwo.title} 이벤트가 삭제 되었습니다.",
                Toast.LENGTH_SHORT
            ).show()
            dataList.remove(dataVoTwo)
            fragmentTwoAdapter.notifyDataSetChanged()
        }
}