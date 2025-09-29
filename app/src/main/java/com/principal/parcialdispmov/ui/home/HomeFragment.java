package com.principal.parcialdispmov.ui.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.principal.parcialdispmov.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        homeViewModel.getMText().observe(this,String ->{

        mostrarDialogo(String);

        });
        binding.btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo =binding.edtCodigo.getText().toString();
                String precio =binding.edtPrecio.getText().toString().trim();
                homeViewModel.recibirObjeto(binding.estDescripcion.getText().toString(),codigo,precio);
                binding.edtCodigo.setText("");
                binding.estDescripcion.setText("");
                binding.edtPrecio.setText("");
            }

        });
        //final TextView textView = binding.textHome;
       // homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    private void mostrarDialogo(String cartel) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Notificacion de Ingreso")
                .setMessage(cartel)
                /*.setPositiveButton("Sí", (dialog, which) -> {
                    // Cierra la app completamente
                    requireActivity().finishAffinity();
                })*/
                .setNegativeButton("Cerrar", (dialog, which) -> {
                    // Solo cierra el diálogo
                    dialog.dismiss();
                })
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}