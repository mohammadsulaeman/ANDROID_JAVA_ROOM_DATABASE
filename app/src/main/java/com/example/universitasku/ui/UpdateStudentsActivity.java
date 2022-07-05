package com.example.universitasku.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.universitasku.R;

public class UpdateStudentsActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.universitasku.ui.ID";
    public static final String EXTRA_FULLNAMES = "com.example.universitasku.ui.FULLNAME";
    public static final String EXTRA_ALAMAT = "com.example.universitasku.ui.ALAMAT";
    public static final String EXTRA_JURUSAN = "com.example.universitasku.ui.JURUSAN";
    public static final String EXTRA_PRODI = "com.example.universitasku.ui.PRODI";
    public static final String EXTRA_JEKEL = "com.example.universitasku.ui.JEKEL";
    String namaAnda,alamatAnda,jurusanAnda,prodiAnda,JekelAnda;
    EditText nama,alamat,jurusan,prodi,jekel;
    Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_students);
        nama = findViewById(R.id.idEDTUPDELFullName);
        alamat = findViewById(R.id.idEDTUPDELAlamat);
        jurusan = findViewById(R.id.idEDTUPDELJurusan);
        prodi = findViewById(R.id.idEDTUPDELProdi);
        jekel = findViewById(R.id.idEDTUPDELJekel);
        btnUpdate = findViewById(R.id.idBtnUpdateStudents);
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

        btnUpdate.setOnClickListener(view -> {
            namaAnda = nama.getText().toString();
            alamatAnda = alamat.getText().toString();
            jurusanAnda = jurusan.getText().toString();
            prodiAnda = prodi.getText().toString();
            JekelAnda = jekel.getText().toString();

            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            Intent updateData = new Intent();
            if (TextUtils.isEmpty(namaAnda)
                    || TextUtils.isEmpty(alamatAnda)
                    || TextUtils.isEmpty(jurusanAnda)
                    || TextUtils.isEmpty(prodiAnda)
                    || TextUtils.isEmpty(JekelAnda)
            )
            {
                setResult(RESULT_CANCELED, updateData);
            }else
            {

                updateData.putExtra(EXTRA_FULLNAMES,namaAnda);
                updateData.putExtra(EXTRA_ALAMAT,alamatAnda);
                updateData.putExtra(EXTRA_JURUSAN,jurusanAnda);
                updateData.putExtra(EXTRA_PRODI,prodiAnda);
                updateData.putExtra(EXTRA_JEKEL,JekelAnda);
                updateData.putExtra(EXTRA_ID,id);
                setResult(RESULT_OK,updateData);
            }
            finish();
        });


    }
}