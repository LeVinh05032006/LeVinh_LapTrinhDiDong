package com.example.baitapkotlin_quanlysinhvien
data class Student(
    val studentId: String,
    var fullName: String,
    var age: Int,
    var major: String,
    var gpa: Double
) {
    // Hien thi thong tin sinh vien
    fun display() {
        println("ID: $studentId | Name: $fullName | Age: $age | Major: $major | GPA: $gpa")
    }
}

fun main() {
    // Khoi tao danh sach sinh vien voi 5 sinh vien mau rieng biet
    val studentList = mutableListOf(
        Student("SV001", "Le Vinh", 20, "Cong Nghe Thong Tin", 8.6),
        Student("SV002", "Tran Thi Thu Thuy", 21, "Cong Nghe Thong Tin", 7.2),
        Student("SV003", "Le Hoang Nhat Nam", 19, "An Ninh Nhan Dan", 4.8),
        Student("SV004", "Pham Thu Thanh Tra", 22, "Su Pham Giao Duc", 9.1),
        Student("SV005", "Hoang Van Minh Duc", 20, "Kiem Toan Nha Nuoc", 6.4)
    )

    var choice: Int
    do {
        // Hien thi menu co ban
        println("\n========== S T U D E N T    M A N A G E M E N T ==========")
        println("1. Add student")
        println("2. Display all students")
        println("3. Search student")
        println("4. Calculate average GPA")
        println("5. Find student with highest GPA")
        println("6. Remove student")
        // Bo sung cac chuc nang theo phan Yeu cau
        println("7. Dem so sinh vien co GPA >= 8.0")
        println("8. Dem so sinh vien co GPA < 5.0")
        println("9. Tinh GPA trung binh cua sinh vien nganh duoc giao")
        println("10. Tim sinh vien lon tuoi nhat")
        println("11. Tim sinh vien co GPA nam trong khoang 7.0 -> 8.5")
        println("12. Tim tat ca sinh vien thuoc mot nganh")
        println("13. Tim sinh vien theo mot phan ten")
        println("14. Sap xep sinh vien theo GPA giam dan")
        println("15. Hien thi 3 sinh vien co GPA cao nhat")
        println("16. Sap xep sinh vien theo tuoi")
        println("17. Sap xep sinh vien theo ten")
        println("0. Exit")
        println("=======================================================")
        print("Choose: ")

        choice = readLine()?.toIntOrNull() ?: -1

        when (choice) {
            1 -> { // Add student
                print("Enter Student ID: ")
                val id = readLine() ?: ""
                print("Enter Full Name: ")
                val name = readLine() ?: ""
                print("Enter Age: ")
                val age = readLine()?.toIntOrNull() ?: 0
                print("Enter Major: ")
                val major = readLine() ?: ""
                print("Enter GPA: ")
                val gpa = readLine()?.toDoubleOrNull() ?: 0.0

                studentList.add(Student(id, name, age, major, gpa))
                println("-> Add student successfully!")
            }
            2 -> { // Display all students
                println("\n--- LIST OF STUDENTS ---")
                if (studentList.isEmpty()) {
                    println("List is empty!")
                } else {
                    studentList.forEach { it.display() }
                }
            }
            3 -> { // Search student
                print("Enter Student ID or name to search: ")
                val keyword = readLine()?.lowercase() ?: ""
                val results = studentList.filter {
                    it.studentId.lowercase().contains(keyword) || it.fullName.lowercase().contains(keyword)
                }
                if (results.isEmpty()) {
                    println("-> No students found!")
                } else {
                    println("-> Search results:")
                    results.forEach { it.display() }
                }
            }
            4 -> { // Calculate average GPA
                if (studentList.isEmpty()) {
                    println("-> List is empty!")
                } else {
                    val avgGpa = studentList.map { it.gpa }.average()
                    println("-> Average GPA of all students: $avgGpa")
                }
            }
            5 -> { // Find student with highest GPA
                if (studentList.isEmpty()) {
                    println("-> List is empty!")
                } else {
                    val maxGpa = studentList.maxOf { it.gpa }
                    val topStudents = studentList.filter { it.gpa == maxGpa }
                    println("-> Student(s) with highest GPA ($maxGpa):")
                    topStudents.forEach { it.display() }
                }
            }
            6 -> { // Remove student
                print("Enter Student ID to remove: ")
                val id = readLine() ?: ""
                val removed = studentList.removeIf { it.studentId.equals(id, ignoreCase = true) }
                if (removed) {
                    println("-> Removed student with ID: $id")
                } else {
                    println("-> Student ID not found!")
                }
            }
            7 -> { // 1. Dem so sinh vien co GPA >= 8.0
                val count = studentList.count { it.gpa >= 8.0 }
                println("-> Number of students with GPA >= 8.0: $count")
            }
            8 -> { // 2. Dem so sinh vien co GPA < 5.0
                val count = studentList.count { it.gpa < 5.0 }
                println("-> Number of students with GPA < 5.0: $count")
            }
            9 -> { // 3. Tinh GPA trung binh cua sinh vien nganh duoc giao
                print("Enter major to calculate average GPA (e.g., Information Technology): ")
                val majorInput = readLine() ?: ""
                val filtered = studentList.filter { it.major.equals(majorInput, ignoreCase = true) }
                if (filtered.isEmpty()) {
                    println("-> No students found in major '$majorInput'")
                } else {
                    val avg = filtered.map { it.gpa }.average()
                    println("-> Average GPA for major '$majorInput': $avg")
                }
            }
            10 -> { // 5. Tim sinh vien lon tuoi nhat
                if (studentList.isEmpty()) {
                    println("-> List is empty!")
                } else {
                    val maxAge = studentList.maxOf { it.age }
                    val oldestStudents = studentList.filter { it.age == maxAge }
                    println("-> Oldest student(s) (Age: $maxAge):")
                    oldestStudents.forEach { it.display() }
                }
            }
            11 -> { // 6. Tim sinh vien co GPA nam trong khoang 7.0 -> 8.5
                val rangeStudents = studentList.filter { it.gpa in 7.0..8.5 }
                if (rangeStudents.isEmpty()) {
                    println("-> No students found in GPA range [7.0 - 8.5]")
                } else {
                    println("-> Students with GPA from 7.0 to 8.5:")
                    rangeStudents.forEach { it.display() }
                }
            }
            12 -> { // 7. Tim tat ca sinh vien thuoc mot nganh
                print("Enter major name: ")
                val majorInput = readLine() ?: ""
                val studentsInMajor = studentList.filter { it.major.equals(majorInput, ignoreCase = true) }
                if (studentsInMajor.isEmpty()) {
                    println("-> No students found in this major.")
                } else {
                    println("-> Students in major '$majorInput':")
                    studentsInMajor.forEach { it.display() }
                }
            }
            13 -> { // 8. Tim sinh vien theo mot phan ten
                print("Enter partial name: ")
                val namePart = readLine()?.lowercase() ?: ""
                val matched = studentList.filter { it.fullName.lowercase().contains(namePart) }
                if (matched.isEmpty()) {
                    println("-> No matching students found.")
                } else {
                    println("-> Matching students:")
                    matched.forEach { it.display() }
                }
            }
            14 -> { // 9. Sap xep sinh vien theo GPA giam dan
                val sortedList = studentList.sortedByDescending { it.gpa }
                println("-> Students sorted by GPA (Descending):")
                sortedList.forEach { it.display() }
            }
            15 -> { // 10. Hien thi 3 sinh vien co GPA cao nhat
                val top3 = studentList.sortedByDescending { it.gpa }.take(3)
                println("-> Top 3 students with highest GPA:")
                top3.forEach { it.display() }
            }
            16 -> { // 11. Sap xep sinh vien theo tuoi
                val sortedList = studentList.sortedBy { it.age }
                println("-> Students sorted by Age (Ascending):")
                sortedList.forEach { it.display() }
            }
            17 -> { // 12. Sap xep sinh vien theo ten
                val sortedList = studentList.sortedBy { it.fullName }
                println("-> Students sorted by Name:")
                sortedList.forEach { it.display() }
            }
            0 -> println("Exiting program. Goodbye!")
            else -> println("Invalid choice! Please choose from 0 to 17.")
        }
    } while (choice != 0)
}