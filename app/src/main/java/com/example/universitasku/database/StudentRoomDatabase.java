package com.example.universitasku.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.universitasku.dao.StudentsDao;
import com.example.universitasku.model.Students;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Students.class}, version = 1, exportSchema = false)
public abstract class StudentRoomDatabase extends RoomDatabase
{
    public abstract StudentsDao studentsDao();
    public static volatile StudentRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecuter =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static StudentRoomDatabase getDatabase(final Context context)
    {
        if (INSTANCE == null){
            synchronized (StudentRoomDatabase.class){
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StudentRoomDatabase.class, "student_database")
                            .addCallback(sRoomDatabaseCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallBack =
            new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecuter.execute(()->{
                StudentsDao dao = INSTANCE.studentsDao();
                dao.deleteAll();


                Students students = new Students(
                        "Maulana",
                        "Bekasi",
                        "Kimia",
                        "MIPA",
                        "Pria"
                );
                dao.insert(students);
            });
        }
    };
}
