package com.aadrizeta.RoomApp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PalabraViewModel extends AndroidViewModel {

    private PalabraRepository mRepository;
    private LiveData<List<Palabra>> mAllPalabras;

    public PalabraViewModel (Application application) {
        super(application);
        mRepository = new PalabraRepository(application);
        mAllPalabras = mRepository.getAllPalabras();
    }

    LiveData<List<Palabra>> getAllPalabras() {
        return mAllPalabras;
    }

    public void insert(Palabra palabra) {
        mRepository.insert(palabra);
    }

    public void delete(Palabra palabra) {
        mRepository.delete(palabra);
    }
}
