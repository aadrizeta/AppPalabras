package com.aadrizeta.RoomApp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PalabraDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Palabra palabra);

    @Query("DELETE FROM table_palabras")
    void deleteAll();

    @Query("SELECT * FROM table_palabras ORDER BY palabra ASC")
    LiveData<List<Palabra>> getPalabrasOrdenadas();
}