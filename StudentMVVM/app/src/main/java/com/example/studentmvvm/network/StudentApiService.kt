package com.example.studentmvvm.network

import retrofit2.http.GET

interface StudentApiService {
    @GET("students.json")
    suspend fun getStudents(): List<StudentDto>
}