package com.example.levinh_baitap_toappandroidcardsinhvien

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.levinh_baitap_toappandroidcardsinhvien.databinding.ActivityMainBinding
import com.example.levinh_baitap_toappandroidcardsinhvien.model.Student
import com.example.levinh_baitap_toappandroidcardsinhvien.utils.toAcademicRanking
import com.example.levinh_baitap_toappandroidcardsinhvien.utils.toast

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentStudent = Student(
        id = "2415053122148",
        name = "Lê Vinh",
        className = "126LTTD03",
        email = "levinh@ute.udn.vn",
        gender = "Nam",
        phone = "0963760551",
        gpa = 3.8
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindStudentData(currentStudent)

        // 1. Xử lý sự kiện bấm nút Cập nhật GPA (Có kiểm tra validate 0.0 - 4.0)
        binding.btnUpdateGpa.setOnClickListener {
            val inputStr = binding.edtNewGpa.text.toString().trim()
            val newGpa = inputStr.toDoubleOrNull()
            if (newGpa == null || newGpa !in 0.0..4.0) {
                binding.edtNewGpa.error = "Vui lòng nhập GPA hợp lệ (0.0 - 4.0)"
                toast("Điểm GPA không hợp lệ!")
                return@setOnClickListener
            }

            // Cập nhật sinh viên bằng hàm copy() bất biến của Data Class
            currentStudent = currentStudent.copy(gpa = newGpa)
            bindStudentData(currentStudent)
            toast("Cập nhật điểm thành công!")
        }

        // 2. Xử lý sự kiện bấm nút Gọi Điện (Sử dụng Implicit Intent)
        binding.btnCall.setOnClickListener {
            val phoneNumber = currentStudent.phone
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            try {
                startActivity(intent)
            } catch (e: Exception) {
                toast("Không thể mở ứng dụng gọi điện!")
            }
        }

        // 3. Xử lý sự kiện bấm nút Xóa Hồ Sơ (Sử dụng Higher-Order Function Dialog)
        binding.btnDeleteStudent.setOnClickListener {
            showConfirmDialog(
                title = "Xác nhận xóa",
                message = "Bạn có chắc chắn muốn xóa hồ sơ sinh viên này không?"
            ) {
                // Xử lý khi người dùng ấn "Đồng ý"
                currentStudent = currentStudent.copy(
                    name = "Đã xóa hồ sơ",
                    gpa = 0.0,
                    phone = "N/A"
                )
                bindStudentData(currentStudent)
                toast("Đã xóa thành công!")
            }
        }
    }

    // Hàm gán dữ liệu gọn gàng sử dụng Scope Function 'with'
    private fun bindStudentData(student: Student) {
        with(binding) {
            tvName.text = student.name
            // Hiển thị MSSV đi kèm Giới tính
            tvStudentId.text = "MSSV: ${student.id} • Giới tính: ${student.gender}"
            // Hiển thị Số điện thoại
            tvPhone.text = "SĐT: ${student.phone}"
            tvGpaBadge.text = "${student.gpa} GPA (${student.gpa.toAcademicRanking()})"
            edtNewGpa.setText(student.gpa.toString())
        }
    }
    // Hàm tiện ích Higher-Order Function hiển thị Dialog xác nhận
    private fun showConfirmDialog(title: String, message: String, onConfirm: () -> Unit) {
        AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton("Đồng ý") { _, _ -> onConfirm() }
            setNegativeButton("Hủy", null)
        }.show()
    }
}