package com.principal.parcialdispmov.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.principal.parcialdispmov.R;
import com.principal.parcialdispmov.clases.Producto;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolderProducto>{

    private List<Producto> listado;
    private Context context;
    private LayoutInflater li;
    public ProductoAdapter(List<Producto>listado,Context context,LayoutInflater li){
        this.context=context;
        this.listado=listado;
        this.li=li;
    }
    @NonNull
    @Override
    public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=li.inflate(R.layout.item,parent,false);
        return new ViewHolderProducto(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto holder, int position) {
        Producto productoActual=listado.get(position);
        holder.descripcion.setText("Descripcion: "+productoActual.getDescripcion());
        holder.precio.setText("Precio: "+productoActual.getPrecio());
        holder.codigo.setText("Codigo: "+productoActual.getCodigo());
    }

    @Override
    public int getItemCount() {
        return listado.size();
    }

    public class ViewHolderProducto extends RecyclerView.ViewHolder{
        TextView descripcion,precio,codigo;
        public ViewHolderProducto(@NonNull View itemView) {
            super(itemView);
            descripcion=itemView.findViewById(R.id.txvDescripcion);
            precio=itemView.findViewById(R.id.txvPrecio);
            codigo=itemView.findViewById(R.id.txvCodigo);

        }
    }


}
