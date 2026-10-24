package com.example.eventvalidation

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

    // Họ tên: chỉ chữ cái (có dấu) + khoảng trắng, tối thiểu 2 từ
    private val namePattern = Regex("^\\p{L}+(\\s+\\p{L}+)+$")

    // SĐT Việt Nam: 0xxxxxxxxx hoặc +84xxxxxxxxx
    private val phonePattern = Regex("^(0|\\+84)(3|5|7|8|9)\\d{8}$")

    // MSV: chữ + số, 6-12 ký tự
    private val msvPattern = Regex("^[A-Za-z0-9]{6,12}$")

    // Lớp: chữ, số, '-', '_', '.' (3-20 ký tự). VD: D21CQCN01-N
    private val classPattern = Regex("^[A-Za-z0-9._-]{3,20}$")

    private lateinit var tilName: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPhone: TextInputLayout
    private lateinit var tilMsv: TextInputLayout
    private lateinit var tilClass: TextInputLayout

    private lateinit var edtName: TextInputEditText
    private lateinit var edtEmail: TextInputEditText
    private lateinit var edtPhone: TextInputEditText
    private lateinit var edtMsv: TextInputEditText
    private lateinit var edtClass: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        tilName = findViewById(R.id.tilName)
        tilEmail = findViewById(R.id.tilEmail)
        tilPhone = findViewById(R.id.tilPhone)
        tilMsv = findViewById(R.id.tilMsv)
        tilClass = findViewById(R.id.tilClass)

        edtName = findViewById(R.id.edtName)
        edtEmail = findViewById(R.id.edtEmail)
        edtPhone = findViewById(R.id.edtPhone)
        edtMsv = findViewById(R.id.edtMsv)
        edtClass = findViewById(R.id.edtClass)

        val btnRegister: Button = findViewById(R.id.btnRegister)
        val btnGoDetail: Button = findViewById(R.id.btnGoDetail)

        // Xóa lỗi khi người dùng bắt đầu sửa
        clearErrorOnType(edtName, tilName)
        clearErrorOnType(edtEmail, tilEmail)
        clearErrorOnType(edtPhone, tilPhone)
        clearErrorOnType(edtMsv, tilMsv)
        clearErrorOnType(edtClass, tilClass)

        btnRegister.setOnClickListener { onRegisterClicked() }

        // Button gọi intent chuyển sang màn Chi tiết
        btnGoDetail.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java))
        }
    }

    private fun onRegisterClicked() {
        val name = edtName.text.toString().trim()
        val email = edtEmail.text.toString().trim()
        val phone = edtPhone.text.toString().trim()
        val msv = edtMsv.text.toString().trim()
        val lop = edtClass.text.toString().trim()

        // Kiểm tra tất cả các trường để hiện hết lỗi cùng lúc
        val results = listOf(
            validate(
                tilName, name, namePattern,
                "Vui lòng nhập họ tên",
                "Họ tên chỉ gồm chữ cái và tối thiểu 2 từ"
            ),
            validateEmail(email),
            validate(
                tilPhone, phone, phonePattern,
                "Vui lòng nhập số điện thoại",
                "SĐT không hợp lệ (VD: 0912345678 hoặc +84912345678)"
            ),
            validate(
                tilMsv, msv, msvPattern,
                "Vui lòng nhập mã sinh viên",
                "MSV gồm chữ/số, độ dài 6-12 ký tự"
            ),
            validate(
                tilClass, lop, classPattern,
                "Vui lòng nhập lớp",
                "Lớp gồm chữ/số/-/_/. , độ dài 3-20 ký tự"
            )
        )

        val root = findViewById<View>(android.R.id.content)

        if (results.all { it }) {
            // Pass hết: Snackbar thành công + nút sang màn Chi tiết
            Snackbar.make(root, "Đăng kí thành công!", Snackbar.LENGTH_INDEFINITE)
                .setAction("Chi tiết") {
                    val intent = Intent(this, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_NAME, name)
                        putExtra(DetailActivity.EXTRA_EMAIL, email)
                        putExtra(DetailActivity.EXTRA_PHONE, phone)
                        putExtra(DetailActivity.EXTRA_MSV, msv)
                        putExtra(DetailActivity.EXTRA_CLASS, lop)
                    }
                    startActivity(intent)
                }
                .show()
        } else {
            // Fail: hiển thị lỗi
            Snackbar.make(
                root,
                "Đăng kí thất bại, vui lòng kiểm tra lại thông tin!",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun validate(
        til: TextInputLayout,
        value: String,
        pattern: Regex,
        emptyMsg: String,
        invalidMsg: String
    ): Boolean {
        return when {
            value.isEmpty() -> {
                til.error = emptyMsg
                false
            }
            !pattern.matches(value) -> {
                til.error = invalidMsg
                false
            }
            else -> {
                til.error = null
                true
            }
        }
    }

    private fun validateEmail(email: String): Boolean {
        return when {
            email.isEmpty() -> {
                tilEmail.error = "Vui lòng nhập email"
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                tilEmail.error = "Email không đúng định dạng (VD: abc@gmail.com)"
                false
            }
            else -> {
                tilEmail.error = null
                true
            }
        }
    }

    private fun clearErrorOnType(edt: TextInputEditText, til: TextInputLayout) {
        edt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                til.error = null
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}