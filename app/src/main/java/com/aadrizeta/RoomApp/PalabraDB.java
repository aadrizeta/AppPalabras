package com.aadrizeta.RoomApp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteCompat;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Palabra.class}, version = 1, exportSchema = false)

public abstract class PalabraDB extends RoomDatabase{

    public abstract PalabraDao palabraDAO();
    private static volatile PalabraDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PalabraDB getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (PalabraDB.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PalabraDB.class, "palabras_db").build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {

                PalabraDao dao = INSTANCE.palabraDAO();
                dao.deleteAll();

                Palabra palabra = new Palabra("Hola");
                dao.insert(palabra);
                palabra = new Palabra("Mundo");
                dao.insert(palabra);
            });
        }
    };
}