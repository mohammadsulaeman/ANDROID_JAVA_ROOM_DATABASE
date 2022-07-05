package com.example.universitasku.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.universitasku.model.Students;

import java.util.List;

@Dao
public interface StudentsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Students students);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Students students);

    @Delete
    void delete(Students students);

    // deleteall from database
    @Query("DELETE FROM students_table")
    void deleteAll();

    // ambil semua data
    @Query("SELECT * FROM students_table ORDER BY fullName ASC")
    LiveData<List<Students>> getStudentsALL();
}
