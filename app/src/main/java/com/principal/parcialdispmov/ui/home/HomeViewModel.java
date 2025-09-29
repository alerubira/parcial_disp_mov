package com.principal.parcialdispmov.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.principal.parcialdispmov.clases.Producto;
import com.principal.parcialdispmov.MainActivity;

public class HomeViewModel extends ViewModel {

   // private final MutableLiveData<String> mText;
  private MutableLiveData<String> mText;
    public HomeViewModel() {
       // mText = new MutableLiveData<>();
        //mText.setValue("This is home fragment");
    }
    public LiveData<String> getMText()
    {
        if(mText==null){
            mText=new MutableLiveData<>();
            return mText;
        }
        return mText;
    }
    public void recibirObjeto(String descripcion,String codigo,String precio)
    {
        Double precioS;
        if (precio.isEmpty()) {
            precioS = (double) 0;
        } else {
            precioS = Double.parseDouble(precio);
        }
        if((descripcion==null||descripcion.length()<1)||(codigo==null||codigo.length()<1)||(precioS<1))
        {
            mText.setValue("Los Datos Ingresados son Incorrectos o No Estan");
        }else
        {
            Producto producto=new Producto(codigo,descripcion,precioS);
            if(buscarProducto(producto)){
                mText.setValue("Ese Producto ya existe");
            }else{
                MainActivity.listaProductos.add(producto);
                mText.setValue("El Producto se cargo Exitosamente");
                }
        }
    }
    private boolean buscarProducto(Producto producto)
    {

        for(Producto p: MainActivity.listaProductos)
        {
            if(p.getCodigo().equals(producto.getCodigo()))
            {
                return true;
            }
        }
        return false;
    }
}
   /* public LiveData<String> getText() {
        return mText;
    }*/

