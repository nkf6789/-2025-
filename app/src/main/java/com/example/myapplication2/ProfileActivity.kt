package com.example.myapplication2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication2.databinding.ActivityProfileBinding
import com.example.myapplication2.utils.PreferenceUtil

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 加载用户信息
        loadUserInfo()

        // 设置点击事件
        setupClickListeners()
    }

    private fun loadUserInfo() {
        val sp = PreferenceUtil.getInstance(this)
        val username = sp.getString("username", "未登录")
        val signature = sp.getString("signature", "暂无签名")

        binding.tvUsername.text = username
        binding.tvSignature.text = signature
    }

    private fun setupClickListeners() {
        // 我的收藏
        binding.llCollection.setOnClickListener {
            Toast.makeText(this, "我的收藏", Toast.LENGTH_SHORT).show()
        }

        // 我的订单
        binding.llOrder.setOnClickListener {
            Toast.makeText(this, "我的订单", Toast.LENGTH_SHORT).show()
        }

        // 我的钱包
        binding.llWallet.setOnClickListener {
            Toast.makeText(this, "我的钱包", Toast.LENGTH_SHORT).show()
        }

        // 我的地址
        binding.llAddress.setOnClickListener {
            Toast.makeText(this, "我的地址", Toast.LENGTH_SHORT).show()
        }

        // 设置
        binding.llSettings.setOnClickListener {
            Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show()
        }
    }
}
