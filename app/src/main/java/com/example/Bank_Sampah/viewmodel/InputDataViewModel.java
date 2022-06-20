package com.example.Bank_Sampah.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.Bank_Sampah.dao.DatabaseDao;
import com.example.Bank_Sampah.database.DatabaseClient;
import com.example.Bank_Sampah.model.ModelDatabase;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InputDataViewModel extends AndroidViewModel {

    DatabaseDao databaseDao;

    public InputDataViewModel(@NonNull Application application) {
        super(application);

        databaseDao = DatabaseClient.getInstance(application).getAppDatabase().databaseDao();
    }

    public void addLaporan(final String kategori, final String image,
                              final String nama_pengguna, final String alamat, final String tanggal,
                              final String catatan, final String berat) {
        Completable.fromAction(() -> {
            ModelDatabase modelDatabase = new ModelDatabase();
            modelDatabase.kategori = kategori;
            modelDatabase.image = image;
            modelDatabase.nama_pengguna = nama_pengguna;
            modelDatabase.alamat = alamat;
            modelDatabase.tanggal = tanggal;
            modelDatabase.catatan = catatan;
            modelDatabase.berat = berat;
            databaseDao.insertData(modelDatabase);
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}
