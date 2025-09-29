package com.principal.parcialdispmov.ui.sensor;



import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.principal.parcialdispmov.R;
import com.principal.parcialdispmov.databinding.FragmentGalleryBinding;
import com.principal.parcialdispmov.databinding.FragmentSensorBinding;
import com.principal.parcialdispmov.ui.mapa.MapsViewModel;


public class SensorFragment extends Fragment {

    private SensorViewModel mViewModel;
    private FragmentSensorBinding binding;

    public static SensorFragment newInstance() {
        return new SensorFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSensorBinding
                .inflate(inflater, container, false);
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(SensorViewModel.class);
        mViewModel.getMDatos().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMostrar.setText(s);
            }
        });
       //mViewModel.activarLecturas();
       // mViewModel.accederASensores();
       // mViewModel.activarAcelerometro();
        mViewModel.activarProximidad();
        return binding.getRoot();
      //  return inflater.inflate(R.layout.fragment_sensor, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SensorViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onPause() {
        super.onPause();
        //mViewModel.desactivarAcelerometro();
        //mViewModel.desactivarLecturas();
        mViewModel.desactivarProximidad();
    }
}