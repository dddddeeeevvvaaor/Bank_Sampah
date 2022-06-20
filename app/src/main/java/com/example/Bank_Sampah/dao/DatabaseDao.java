package com.example.Bank_Sampah.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.Bank_Sampah.model.ModelDatabase;

import java.util.List;

@Dao
public interface DatabaseDao {

    @Query("SELECT * FROM tbl_banksampah")
    LiveData<List<ModelDatabase>> getAllReport();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(ModelDatabase... modelDatabases);

    @Query("DELETE FROM tbl_banksampah")
    void deleteAllReport();

    @Query("DELETE FROM tbl_banksampah WHERE uid= :uid")
    void deleteSingleReport(int uid);

}
