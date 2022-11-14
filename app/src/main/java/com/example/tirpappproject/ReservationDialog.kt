package com.example.chapter11drawerviewpagerfragment

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.tirpappproject.DataVOThree
import com.example.tirpappproject.MainActivity
import com.example.tirpappproject.R
import com.example.tirpappproject.databinding.ReservationDialogBinding

class ReservationDialog(val context: Context) {
    val dialog = Dialog(context)

    fun showDialog(){
        val binding = ReservationDialogBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        binding.reserBtnCancle.setOnClickListener {
            dialog.dismiss()
        }
        binding.reserBtnOk.setOnClickListener {
            val name = binding.reserEdtName.text.toString()
            val reservation = binding.reserEdtPass.text.toString()
            val number = (Math.random()*100).toInt()
            var dataVOThree: DataVOThree

            if(number % 2 == 0){
                dataVOThree = DataVOThree(name, reservation, R.drawable.son)
            }else{
                dataVOThree = DataVOThree(name, reservation,R.drawable.iu)
            }
            (context as MainActivity).threeFragment.refreshRecyclerViewadd(dataVOThree)
            dialog.dismiss()
        }
    }
}

