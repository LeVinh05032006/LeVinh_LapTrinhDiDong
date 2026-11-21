package com.example.studentmvvm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.studentmvvm.data.AppDatabase
import com.example.studentmvvm.repository.StudentRepository
import com.example.studentmvvm.ui.StudentListFragment
import com.example.studentmvvm.viewmodel.StudentViewModel
import com.example.studentmvvm.viewmodel.StudentViewModelFactory

class MainActivity : AppCompatActivity() {

    private val database by lazy { AppDatabase.getDatabase(this) }
    private val repository by lazy { StudentRepository(database.studentDao()) }
    private val viewModel: StudentViewModel by viewModels {
        StudentViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // BẮT BUỘC: gọi viewModel để nó được tạo bằng Factory.
        // Nếu bỏ dòng này, Fragment (activityViewModels) sẽ crash.
        viewModel

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, StudentListFragment())
                .commit()
        }
    }
}