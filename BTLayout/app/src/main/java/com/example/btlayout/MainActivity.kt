package com.example.btlayout

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

class MainActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPhone: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var swNotify: SwitchCompat
    private lateinit var swNewsletter: SwitchCompat
    private lateinit var cbAgree: CheckBox
    private lateinit var btnSubmit: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtName = findViewById(R.id.edtName)
        edtEmail = findViewById(R.id.edtEmail)
        edtPhone = findViewById(R.id.edtPhone)
        rgGender = findViewById(R.id.rgGender)
        swNotify = findViewById(R.id.swNotify)
        swNewsletter = findViewById(R.id.swNewsletter)
        cbAgree = findViewById(R.id.cbAgree)
        btnSubmit = findViewById(R.id.btnSubmit)
        tvResult = findViewById(R.id.tvResult)

        btnSubmit.setOnClickListener {
            val name = edtName.text.toString().trim()
            val email = edtEmail.text.toString().trim()
            val phone = edtPhone.text.toString().trim()

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập họ tên và email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!cbAgree.isChecked) {
                Toast.makeText(this, "Bạn cần đồng ý điều khoản", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val gender = when (rgGender.checkedRadioButtonId) {
                R.id.rbMale -> "Nam"
                R.id.rbFemale -> "Nữ"
                else -> "Khác"
            }

            val result = "Họ tên: $name" +
                    "\nEmail: $email" +
                    "\nSĐT: ${if (phone.isEmpty()) "(không có)" else phone}" +
                    "\nGiới tính: $gender" +
                    "\nThông báo: ${if (swNotify.isChecked) "Bật" else "Tắt"}" +
                    "\nBản tin: ${if (swNewsletter.isChecked) "Có" else "Không"}"

            tvResult.text = result
        }
    }
}