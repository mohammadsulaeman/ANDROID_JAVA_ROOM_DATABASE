package com.example.universitasku.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.universitasku.R;

public class DeleteStudentsActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.universitasku.ui.ID";
    public static final String EXTRA_FULLNAMES = "com.example.universitasku.ui.FULLNAME";
    public static final String EXTRA_ALAMAT = "com.example.universitasku.ui.ALAMAT";
    public static final String EXTRA_JURUSAN = "com.example.universitasku.ui.JURUSAN";
    public static final String EXTRA_PRODI = "com.example.universitasku.ui.PRODI";
    public static final String EXTRA_JEKEL = "com.example.universitasku.ui.JEKEL";
    String namaAnda,alamatAnda,jurusanAnda,prodiAnda,JekelAnda;
    TextView nama,alamat,jurusan,prodi,jekel;
    Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_students);
        nama = findViewById(R.id.idEDTDELFullName);
        alamat = findViewById(R.id.idEDTDELAlamat);
        jurusan = findViewById(R.id.idEDTDELJurusan);
        prodi = findViewById(R.id.idEDTDELProdi);
        jekel = findViewById(R.id.idEDTDELJekel);
        btnDelete = findViewById(R.id.idBtnDeleteStudents);
        // ambil data
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID))
        {
            nama.setText(intent.getStringExtra(EXTRA_FULLNAMES));
            alamat.setText(intent.getStringExtra(EXTRA_ALAMAT));
            jurusan.setText(intent.getStringExtra(EXTRA_JURUSAN));
            prodi.setText(intent.getStringExtra(EXTRA_PRODI));
            jekel.setText(intent.getStringExtra(EXTRA_JEKEL));
        }

        btnDelete.setOnClickListener(view -> {
            namaAnda = nama.getText().toString();
            alamatAnda = alamat.getText().toString();
            jurusanAnda = jurusan.getText().toString();
            prodiAnda = prodi.getText().toString();
            JekelAnda = jekel.getText().toString();
            Intent deleteData = new Intent();
            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if (TextUtils.isEmpty(namaAnda)
                    || TextUtils.isEmpty(alamatAnda)
                    || TextUtils.isEmpty(jurusanAnda)
                    || TextUtils.isEmpty(prodiAnda)
                    || TextUtils.isEmpty(JekelAnda)
            )
            {
               setResult(RESULT_CANCELED, deleteData);
            }else
            {

                deleteData.putExtra(EXTRA_FULLNAMES,namaAnda);
                deleteData.putExtra(EXTRA_ALAMAT,alamatAnda);
                deleteData.putExtra(EXTRA_JURUSAN,jurusanAnda);
                deleteData.putExtra(EXTRA_PRODI,prodiAnda);
                deleteData.putExtra(EXTRA_JEKEL,JekelAnda);
                deleteData.putExtra(EXTRA_ID,id);
                setResult(RESULT_OK,deleteData);
            }
            finish();
        });
    }
}