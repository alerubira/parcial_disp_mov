package com.principal.parcialdispmov;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.principal.parcialdispmov.clases.Producto;

import java.util.ArrayList;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Producto>> mlistaProductos;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<ArrayList<Producto>> getMListaProductos() {
        if (mlistaProductos == null){
            mlistaProductos = new MutableLiveData<>();

        }
        return mlistaProductos;
    }
    public void llenar(){
        Producto p=new Producto("123","fideo",234);
        Producto e=new Producto("145","arroz",250);
        Producto f=new Producto("145","lenteja",220);
        Producto s=new Producto("365","poroto",285);
        Producto r=new Producto("544","bianda",299);
        ArrayList<Producto> list=new ArrayList<>();
        list.add(p);
        list.add(e);
        list.add(f);
        list.add(s);
        list.add(r);
        mlistaProductos.setValue(list);
    }
}
