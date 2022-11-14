package com.example.chapter11drawerviewpagerfragment

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.tirpappproject.databinding.DialogCustomBinding
import com.example.tirpappproject.DataVO
import com.example.tirpappproject.MainActivity
import com.example.tirpappproject.R
import com.example.tirpappproject.databinding.FragmentOneBinding

 class CustomDialog(val context: Context, val OnFragmentOneBinding : FragmentOneBinding) {
    val dialog = Dialog(context)

    fun showDialog(){
        val binding = DialogCustomBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        binding.diaBtnCancel.setOnClickListener {
            dialog.dismiss()
        }
        binding.diaBtnOK.setOnClickListener {
            val area = binding.diaEdtArea.text.toString()
            val title = binding.diaEdtTitle.text.toString()
            val price = binding.diaEdtPrice.text.toString()
            val price2 = binding.diaEdtPrice2.text.toString()
            val number = (Math.random()*100).toInt()
            var dataVO: DataVO
            if(number % 2 == 0){
                dataVO = DataVO(area, title,price,price2, R.drawable.image1)
            }else{
                dataVO = DataVO(area, title,price,price2,R.drawable.image2)
            }
            (context as MainActivity).oneFragment.refreshRecyclerViewadd(dataVO)
            dialog.dismiss()
        }
    }
}