package com.example.universitasku.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.universitasku.R;
import com.example.universitasku.adapter.StudentListAdapter;
import com.example.universitasku.model.Students;
import com.example.universitasku.viewmodel.StudentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private StudentViewModel mStudentViewModel;
    public static final int NEW_STUDENTS_REQUEST_CODE = 1;
    public static final int UPDATE_STUDENTS_REQUEST_CODE = 2;
    public static final int DELETE_STUDENTS_REQUEST_CODE = 3;
    public static final String TAG = "RoomDatabaseUniversitas";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final StudentListAdapter adapter = new StudentListAdapter(new StudentListAdapter.StudentDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
        mStudentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        mStudentViewModel.getmAllStudents().observe(this,students ->
        {
            adapter.submitList(students);
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mStudentViewModel.delete(adapter.getStudentAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Students Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemClickListener(new StudentListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Students model) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Silakan Pilih Aksi");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent editData = new Intent(MainActivity.this, UpdateStudentsActivity.class);
                        editData.putExtra(UpdateStudentsActivity.EXTRA_ID, model.getId());
                        editData.putExtra(UpdateStudentsActivity.EXTRA_FULLNAMES,model.getFullName());
                        editData.putExtra(UpdateStudentsActivity.EXTRA_ALAMAT,model.getAlamat());
                        editData.putExtra(UpdateStudentsActivity.EXTRA_JURUSAN,model.getJurusan());
                        editData.putExtra(UpdateStudentsActivity.EXTRA_PRODI,model.getProdi());
                        editData.putExtra(UpdateStudentsActivity.EXTRA_JEKEL,model.getJekel());
                        startActivityForResult(editData,UPDATE_STUDENTS_REQUEST_CODE);
                    }
                }).setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent deleteData = new Intent(MainActivity.this, DeleteStudentsActivity.class);
                        deleteData.putExtra(DeleteStudentsActivity.EXTRA_ID, model.getId());
                        deleteData.putExtra(DeleteStudentsActivity.EXTRA_FULLNAMES,model.getFullName());
                        deleteData.putExtra(DeleteStudentsActivity.EXTRA_ALAMAT,model.getAlamat());
                        deleteData.putExtra(DeleteStudentsActivity.EXTRA_JURUSAN,model.getJurusan());
                        deleteData.putExtra(DeleteStudentsActivity.EXTRA_PRODI,model.getProdi());
                        deleteData.putExtra(DeleteStudentsActivity.EXTRA_JEKEL,model.getJekel());
                        startActivityForResult(deleteData,DELETE_STUDENTS_REQUEST_CODE);
                    }
                }).create().show();
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this, NewStudentsActivity.class);
            startActivityForResult(intent,NEW_STUDENTS_REQUEST_CODE);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_STUDENTS_REQUEST_CODE && resultCode == RESULT_OK)
        {
            Students students = new Students(
                    data.getStringExtra(NewStudentsActivity.EXTRA_FULLNAMES),
                    data.getStringExtra(NewStudentsActivity.EXTRA_JURUSAN),
                    data.getStringExtra(NewStudentsActivity.EXTRA_ALAMAT),
                    data.getStringExtra(NewStudentsActivity.EXTRA_PRODI),
                    data.getStringExtra(NewStudentsActivity.EXTRA_JEKEL)
            );
            mStudentViewModel.insert(students);
        }
        else if (requestCode == UPDATE_STUDENTS_REQUEST_CODE && resultCode == RESULT_OK)
        {
           int id =  data.getIntExtra(UpdateStudentsActivity.EXTRA_ID, -1);
            Log.d(TAG, "onActivityResult Upadate ID: " + id);
            String fullName = data.getStringExtra(UpdateStudentsActivity.EXTRA_FULLNAMES);
            String jurusan = data.getStringExtra(UpdateStudentsActivity.EXTRA_JURUSAN);
            String alamat = data.getStringExtra(UpdateStudentsActivity.EXTRA_ALAMAT);
            String prodi = data.getStringExtra(UpdateStudentsActivity.EXTRA_PRODI);
            String jekel = data.getStringExtra(UpdateStudentsActivity.EXTRA_JEKEL);
            
            Students students = new Students(fullName,alamat,jurusan,prodi,jekel);
            students.setId(id);
            Log.d(TAG, "onActivityResult StudentsID: "+students.getId());
            mStudentViewModel.update(students);
        }else if (requestCode == DELETE_STUDENTS_REQUEST_CODE && resultCode == RESULT_OK)
        {
            int id =  data.getIntExtra(UpdateStudentsActivity.EXTRA_ID, -1);
            Log.d(TAG, "onActivityResult Delete ID: " + id);
            String fullName = data.getStringExtra(UpdateStudentsActivity.EXTRA_FULLNAMES);
            String jurusan = data.getStringExtra(UpdateStudentsActivity.EXTRA_JURUSAN);
            String alamat = data.getStringExtra(UpdateStudentsActivity.EXTRA_ALAMAT);
            String prodi = data.getStringExtra(UpdateStudentsActivity.EXTRA_PRODI);
            String jekel = data.getStringExtra(UpdateStudentsActivity.EXTRA_JEKEL);

            Students students = new Students(fullName,alamat,jurusan,prodi,jekel);
            students.setId(id);
            Log.d(TAG, "onActivityResult Delete StudentsID: "+students.getId());
            mStudentViewModel.delete(students);
        }else {
            Toast.makeText(this, R.string.empty_not_saved, Toast.LENGTH_SHORT).show();
        }
    }
}