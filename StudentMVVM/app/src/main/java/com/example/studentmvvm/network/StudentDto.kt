package com.example.studentmvvm.network

import com.example.studentmvvm.model.Student

data class StudentDto(
    val fullName: String?,
    val studentCode: String?,
    val email: String?,
    val gpa: Double?,
    val major: String?
)

fun StudentDto.toEntity(): Student = Student(
    fullName = fullName ?: "Không tên",
    studentCode = studentCode ?: "",
    email = email ?: "",
    gpa = gpa ?: 0.0,
    major = major ?: "Công Nghệ Thông Tin"
)