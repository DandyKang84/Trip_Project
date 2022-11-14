package com.example.tirpappproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter11drawerviewpagerfragment.CustomDialog
import com.example.tirpappproject.databinding.FragmentOneBinding

class OneFragment : Fragment() {
    lateinit var binding: FragmentOneBinding
    var dataList = mutableListOf<DataVO>()
    lateinit var customAdater: CustomAdater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater, container, false)
        for(i in 1..29){
            if(i%2 == 0){
                dataList.add(DataVO("제주", "산방산 탄산온천 이용권",
                    "10,200원","최저가", R.drawable.image1) )
            }else{
                dataList.add(DataVO("부산", "해운대 입장료",
                    "2,200원"," ", R.drawable.image2) )
                dataList.add(DataVO("해남", "땅끝마을 배편",
                    "13,500원","최저가", R.drawable.image3) )
            }
        }
        val linearLayoutManager = LinearLayoutManager(container?.context)
        customAdater = CustomAdater(dataList)
        binding.recyclerViewFragOne.addItemDecoration(MyDecoration(binding.root.context))
        binding.recyclerViewFragOne.layoutManager = linearLayoutManager
        binding.recyclerViewFragOne.adapter = customAdater
        binding.btnFloating.setOnClickListener{
            val dialog = CustomDialog(binding.root.context,binding)
            dialog.showDialog()
        }
        return binding.root
    }
    fun refreshRecyclerViewadd(dataVO: DataVO) {
        Toast.makeText(binding.root.context, "${dataVO.area} 지역이 추가 되었습니다.", Toast.LENGTH_SHORT).show()
        dataList.add(dataVO)
        customAdater.notifyDataSetChanged()
    }
    fun refreshRecyclerViewDrop(dataVO: DataVO) {
        Toast.makeText(binding.root.context, "${dataVO.area} 지역이 삭제 되었습니다.", Toast.LENGTH_SHORT).show()
        dataList.remove(dataVO)
        customAdater.notifyDataSetChanged()
    }
}