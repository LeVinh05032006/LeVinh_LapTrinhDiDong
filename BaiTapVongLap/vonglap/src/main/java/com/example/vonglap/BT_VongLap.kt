package com.example.vonglap

fun main() {
    // Thong tin sinh vien
    println("Ho ten: Le Vinh - MSV: 2415053122148")
    println("=====================================\n")
    // --- BAI 1: In cac so tu 1 den 10 ---
    println("=== BAI 1: IN CAC SO TU 1 DEN 10 ===")
    for (i in 1..10) {
        print("$i ")
    }
    println("\n")
    // --- BAI 2: Tinh tong 1 + 2 + ... + 100 ---
    println("=== BAI 2: TINH TONG TU 1 DEN 100 ===")
    var sum = 0
    for (i in 1..100) {
        sum += i
    }
    println("Tong S = 1 + 2 + ... + 100 la: $sum\n")

    // --- BAI 3: In cac so chan tu 1 den 20 ---
    println("=== BAI 3: IN CAC SO CHAN TU 1 DEN 20 ===")
    for (i in 2..20 step 2) {
        print("$i ")
    }
    println()
}