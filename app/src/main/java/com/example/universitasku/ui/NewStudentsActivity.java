package com.example.universitasku.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.universitasku.R;

public class NewStudentsActivity extends AppCompatActivity {

    public static final String EXTRA_FULLNAMES = "com.example.universitasku.ui.FULLNAME";
    public static final String EXTRA_ALAMAT = "com.example.universitasku.ui.ALAMAT";
    public static final String EXTRA_JURUSAN = "com.example.universitasku.ui.JURUSAN";
    public static final String EXTRA_PRODI = "com.example.universitasku.ui.PRODI";
    public static final String EXTRA_JEKEL = "com.example.universitasku.ui.JEKEL";
    public EditText nama,alamat,jurusan,prodi,jekel;
    String fullNames,alamatAnda,JurusanAnda,prodiAnda,JekelAnda;
    Button submitAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_students);
        nama = findViewById(R.id.idEDTFullName);
        alamat = findViewById(R.id.idEDTAlamat);
        jurusan = findViewById(R.id.idEDTJurusan);
        prodi = findViewById(R.id.idEDTProdi);
        jekel = findViewById(R.id.idEDTJekel);
        submitAdd = findViewById(R.id.idBtnAddStudents);

        submitAdd.setOnClickListener(view -> {
            fullNames = nama.getText().toString();
            alamatAnda = alamat.getText().toString();
            JurusanAnda = jurusan.getText().toString();
            prodiAnda = prodi.getText().toString();
            JekelAnda = jekel.getText().toString();

            if (TextUtils.isEmpty(fullNames)
                    || TextUtils.isEmpty(alamatAnda)
                    || TextUtils.isEmpty(JurusanAnda)
                    || TextUtils.isEmpty(prodiAnda)
                    || TextUtils.isEmpty(JekelAnda))
            {
                Toast.makeText(this, "Silakan isi Terlebih dahulu", Toast.LENGTH_SHORT).show();
            }else{
                Intent addStudents = new Intent();
                addStudents.putExtra(EXTRA_FULLNAMES, fullNames);
                addStudents.putExtra(EXTRA_ALAMAT,alamatAnda);
                addStudents.putExtra(EXTRA_JURUSAN,JurusanAnda);
                addStudents.putExtra(EXTRA_PRODI,prodiAnda);
                addStudents.putExtra(EXTRA_JEKEL,JekelAnda);
                setResult(RESULT_OK, addStudents);
            }
            finish();

        });
    }
}