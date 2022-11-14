package com.example.tirpappproject

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.tirpappproject.databinding.ActivityMainBinding
import com.example.tirpappproject.databinding.FragmentOneItemBinding

class CustomAdater(val dataList: MutableList<DataVO>) :
    RecyclerView.Adapter<CustomAdater.CustomViewHolder>() {
    lateinit var binding: ActivityMainBinding
    override fun getItemCount(): Int {
        return dataList.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding =
            FragmentOneItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val customViewHolder = CustomViewHolder(binding)

        binding.itemIvPayment.setOnClickListener {
            val position: Int = customViewHolder.bindingAdapterPosition
            val dataVO = dataList.get(position)
            val eventHandler = DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        notifyDataSetChanged()
                        dialog.dismiss()
                        (parent.context as MainActivity).binding.viewpager.currentItem = 2
                    }
                    DialogInterface.BUTTON_NEGATIVE -> dialog.dismiss()
                }
            }
            AlertDialog.Builder(parent.context).run {
                setIcon(android.R.drawable.ic_dialog_alert)
                setTitle("예약 하시겠습니까?")
                setMessage("예약하시려면 확인을 눌러주세요")
                setPositiveButton("확인", eventHandler)
                setNegativeButton("취소", eventHandler)
                setCancelable(false)
                show()
            }
            Toast.makeText(parent.context, "[${dataVO.area}], ${dataVO.title}", Toast.LENGTH_SHORT)
                .show()
        }
        customViewHolder.itemView.setOnClickListener {
            val position: Int = customViewHolder.bindingAdapterPosition
            val dataVO = dataList.get(position)
            Toast.makeText(parent.context, "[${dataVO.area}], ${dataVO.title}", Toast.LENGTH_SHORT)
                .show()
        }
        customViewHolder.itemView.setOnLongClickListener {
            val position: Int = customViewHolder.bindingAdapterPosition
            val dataVO = dataList.get(position)
            (parent.context as MainActivity).oneFragment.refreshRecyclerViewDrop(dataVO)
            true
        }
        return customViewHolder
    }
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val binding = (holder as CustomViewHolder).binding
        val dataVO = dataList.get(position)
        binding.itemIvPiture.setImageResource(dataVO.picture)
        binding.itemTvArea.text = dataVO.area
        binding.itemTvTitle.text = dataVO.title
        binding.itemIvPrice.text = dataVO.price
        binding.itemIvPrice2.text = dataVO.price2
    }
    class CustomViewHolder(val binding: FragmentOneItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}