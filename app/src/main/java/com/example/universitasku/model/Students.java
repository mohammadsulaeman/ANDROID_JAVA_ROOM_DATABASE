package com.example.universitasku.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students_table")
public class Students {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "fullName")
    public String fullName;

    public String alamat;

    public String jurusan;

    public String prodi;

    public String jekel;

    public Students(String fullName, String alamat, String jurusan, String prodi, String jekel) {
        this.fullName = fullName;
        this.alamat = alamat;
        this.jurusan = jurusan;
        this.prodi = prodi;
        this.jekel = jekel;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getJekel() {
        return jekel;
    }

    public void setJekel(String jekel) {
        this.jekel = jekel;
    }
}
