package com.example.tirpappproject

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.tirpappproject.databinding.FragmentThreeBinding
import com.example.tirpappproject.databinding.FragmentThreeItemBinding

class PaymentDialog(val context: Context, val twoFragmentThreeBinding: FragmentThreeBinding){
    val dialog = Dialog(context)

    fun showDialog() {
        val binding = FragmentThreeItemBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        binding.fragThreeTvName.setOnClickListener {
            dialog.dismiss()
        }
    }
}