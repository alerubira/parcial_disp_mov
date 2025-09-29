package com.principal.parcialdispmov.ui.slideshow;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.principal.parcialdispmov.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

      //  final TextView textView = binding.textSlideshow;
      //  slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    @Override
    public void onResume() {
        super.onResume();
        // Mostramos el diálogo cada vez que el fragment se vuelve visible
        mostrarDialogoSalir();
    }

    private void mostrarDialogoSalir() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Cerrar aplicación")
                .setMessage("¿Seguro que quieres salir?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    // Cierra la app completamente
                    requireActivity().finishAffinity();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> {
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