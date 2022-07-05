package com.example.universitasku.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.universitasku.dao.StudentsDao;
import com.example.universitasku.database.StudentRoomDatabase;
import com.example.universitasku.model.Students;

import java.util.List;

public class StudentRepository {
    private StudentsDao mstudentsDao;
    private LiveData<List<Students>> mAllStudents;

    public StudentRepository(Application application){
        StudentRoomDatabase db = StudentRoomDatabase.getDatabase(application);
        mstudentsDao = db.studentsDao();
        mAllStudents = mstudentsDao.getStudentsALL();
    }

    public LiveData<List<Students>> getmAllStudents(){
        return mAllStudents;
    }

    public void insert(Students students)
    {
        StudentRoomDatabase.databaseWriteExecuter.execute(()->{
            mstudentsDao.insert(students);
        });
    }

    public void update(Students students)
    {
        StudentRoomDatabase.databaseWriteExecuter.execute(()->{
            mstudentsDao.update(students);
        });
    }

    public void delete(Students students){
        StudentRoomDatabase.databaseWriteExecuter.execute(()->{
            mstudentsDao.delete(students);
        });
    }
}
