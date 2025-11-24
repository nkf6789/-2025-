package com.example.myapplication2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication2.databinding.ActivityLoginBinding
import com.example.myapplication2.utils.PreferenceUtil

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 初始化数据库
        dbHelper = DatabaseHelper(this)

        // 预埋测试账号
        initDefaultAccount()

        // 设置点击事件
        setupClickListeners()
    }

    private fun initDefaultAccount() {
        // 首次运行时添加默认账号
        val sp = PreferenceUtil.getInstance(this)
        if (!sp.getBoolean("is_init", false)) {
            dbHelper.addUser("admin", "123456")
            dbHelper.addUser("test", "test123")
            sp.putBoolean("is_init", true)
        }
    }

    private fun setupClickListeners() {
        // 登录按钮
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "请输入用户名和密码", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 验证登录
            if (dbHelper.checkUser(username, password)) {
                // 保存用户信息到SharedPreferences
                PreferenceUtil.getInstance(this).apply {
                    putString("username", username)
                    putString("signature", "这个人很懒，什么都没留下~")
                }

                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()

                // 跳转到个人中心
                startActivity(Intent(this, ProfileActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show()
            }
        }

        // 微信登录
        binding.ivWechat.setOnClickListener {
            Toast.makeText(this, "微信登录功能开发中...", Toast.LENGTH_SHORT).show()
        }

        // Apple登录
        binding.ivApple.setOnClickListener {
            Toast.makeText(this, "Apple登录功能开发中...", Toast.LENGTH_SHORT).show()
        }
    }
}
