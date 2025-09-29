package com.principal.parcialdispmov.ui.gallery;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.principal.parcialdispmov.MainActivity;
import com.principal.parcialdispmov.clases.Producto;

import java.util.ArrayList;
import java.util.Comparator;

import kotlinx.coroutines.CoroutineScope;

public class GalleryViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Producto>> listaMutable=null;
    private ArrayList<Producto> productos;
   // private final MutableLiveData<String> mText;
   public GalleryViewModel() {

   }

    public LiveData<ArrayList<Producto>> getListaMutable(){
        if(listaMutable==null){
            listaMutable=new MutableLiveData<>();
        }
        return listaMutable;
    }
    public void buscarProductos(){

        productos= MainActivity.listaProductos;
        Log.d("salida1", "productos");
        productos.sort(Comparator.comparing(Producto::getDescripcion, String.CASE_INSENSITIVE_ORDER));

        listaMutable.setValue(productos);
    }

}