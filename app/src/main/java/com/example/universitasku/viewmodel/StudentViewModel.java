package com.example.universitasku.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.universitasku.model.Students;
import com.example.universitasku.repository.StudentRepository;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    public StudentRepository mRepository;
    public final LiveData<List<Students>> mStudents;


    public StudentViewModel(@NonNull Application application) {
        super(application);
        mRepository = new StudentRepository(application);
        mStudents = mRepository.getmAllStudents();
    }

    public LiveData<List<Students>> getmAllStudents()
    {
        return mStudents;
    }

    public void insert(Students students){
        mRepository.insert(students);
    }

    public void update(Students students)
    {
        mRepository.update(students);
    }

    public void delete(Students students)
    {
        mRepository.delete(students);
    }
}
