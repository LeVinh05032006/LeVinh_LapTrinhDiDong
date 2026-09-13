package com.example.bt_kotlin

import java.util.Scanner
import kotlin.math.max

fun main() {
    val scanner = Scanner(System.`in`)

    // Thong tin sinh vien
    val hoTen = "Le Vinh"
    val msv = "2415053122148"
    val svInfo = "$hoTen - $msv"

    println("==================================================")
    println("          THONG TIN SINH VIEN & DIEM SO           ")
    println("==================================================")
    println("Sinh vien : $hoTen")
    println("Ma SV     : $msv")
    println("--------------------------------------------------")

    // Nhap diem
    print("-> Nhap diem Math        : ")
    val math = scanner.nextDouble()

    print("-> Nhap diem Programming : ")
    val programming = scanner.nextDouble()

    print("-> Nhap diem Database    : ")
    val database = scanner.nextDouble()

    // Xu ly tinh toan
    val tongDiem = math + programming + database
    val gpa = tongDiem / 3.0
    val diemCaoNhat = max(math, max(programming, database))
    val trangThai = if (gpa >= 5.0) "DAT" else "KHONG DAT"

    // In ket qua phan chia ro rang theo tung dong
    println("\n==================================================")
    println("                 KET QUA BAI TAP                  ")
    println("==================================================")
    println("1. TONG DIEM       : %-8.2f | SV: %s".format(tongDiem, svInfo))
    println("2. DIEM TRUNG BINH : %-8.2f | SV: %s".format(gpa, svInfo))
    println("3. DIEM CAO NHAT   : %-8.2f | SV: %s".format(diemCaoNhat, svInfo))
    println("4. TRANG THAI      : %-8s | SV: %s".format(trangThai, svInfo))
    println("==================================================")
}