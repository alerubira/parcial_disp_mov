package com.principal.parcialdispmov.ui.mapa;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import static androidx.core.content.ContextCompat.checkSelfPermission;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.principal.parcialdispmov.R;

public class MapsFragment extends Fragment {
    private MapsViewModel mv;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(MapsViewModel.class);

        mv.getMlocation().observe(getViewLifecycleOwner(), new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                mv.cargarMapa(location);
            }
        });

        mv.getMMapa().observe(getViewLifecycleOwner(), new Observer<MapsViewModel.MapaActual>() {
            @Override
            public void onChanged(MapsViewModel.MapaActual mapaActual) {
                SupportMapFragment mapFragment =
                        (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                mapFragment.getMapAsync(mapaActual);
            }
        });
        solicitarPermisos();
        mv.obtenerUbicacion();
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }
    public void solicitarPermisos(){//solicitamos los permisos(primero,luego armamos el viewModel y terminamos el activity)
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && (checkSelfPermission(ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)  ||
                (checkSelfPermission(ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)){
            requestPermissions(new String[]{ACCESS_FINE_LOCATION,ACCESS_COARSE_LOCATION},1000);
        }

    }

    private int checkSelfPermission(String accessFineLocation) {
        return 1;
    }
}

