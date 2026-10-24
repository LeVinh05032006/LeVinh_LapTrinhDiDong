package com.example.eventvalidation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_PHONE = "extra_phone"
        const val EXTRA_MSV = "extra_msv"
        const val EXTRA_CLASS = "extra_class"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvName: TextView = findViewById(R.id.tvName)
        val tvEmail: TextView = findViewById(R.id.tvEmail)
        val tvPhone: TextView = findViewById(R.id.tvPhone)
        val tvMsv: TextView = findViewById(R.id.tvMsv)
        val tvClass: TextView = findViewById(R.id.tvClass)
        val btnBack: Button = findViewById(R.id.btnBack)

        val empty = "(chưa có dữ liệu)"
        tvName.text = "Họ tên: " + valueOrEmpty(intent.getStringExtra(EXTRA_NAME), empty)
        tvEmail.text = "Email: " + valueOrEmpty(intent.getStringExtra(EXTRA_EMAIL), empty)
        tvPhone.text = "SĐT: " + valueOrEmpty(intent.getStringExtra(EXTRA_PHONE), empty)
        tvMsv.text = "MSV: " + valueOrEmpty(intent.getStringExtra(EXTRA_MSV), empty)
        tvClass.text = "Lớp: " + valueOrEmpty(intent.getStringExtra(EXTRA_CLASS), empty)

        // Button gọi intent quay về màn Đăng kí (giữ instance cũ, không mất dữ liệu đã nhập)
        btnBack.setOnClickListener {
            val back = Intent(this, RegisterActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            startActivity(back)
        }
    }

    private fun valueOrEmpty(s: String?, fallback: String): String {
        return if (s.isNullOrEmpty()) fallback else s
    }
}