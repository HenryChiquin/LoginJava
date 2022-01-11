package com.example.loginjava.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.loginjava.database.entity.tUniverso;

import java.util.List;

@Dao
public interface universoDAO {
    @Query("select * from tUniverso")
    List<tUniverso> getAll();

    @Query("select * from tUniverso where id = :id")
    tUniverso findById(int id);

    //Insertar datos a la tabla
    @Insert
    void insert(tUniverso tuniverso);

    @Update
    void update(tUniverso tuniverso);

    @Delete
    void delete(tUniverso tuniverso);

}
