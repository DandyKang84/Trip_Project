package com.example.tirpappproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tirpappproject.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtnOK.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            if (binding.loginEdtID.text.isNotEmpty() && binding.loginEdtPass.text.isNotEmpty()){
                intent.putExtra("id",binding.loginEdtID.text.toString())
                startActivity(intent)
                Toast.makeText(this, "${binding.loginEdtID.text.toString()}님 환영합니다.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "id와 pw값이 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        binding.loginBtnClose.setOnClickListener {
            Toast.makeText(this, "로그인 취소합니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}