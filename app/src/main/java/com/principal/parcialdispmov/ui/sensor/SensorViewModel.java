package com.principal.parcialdispmov.ui.sensor;

import static androidx.lifecycle.AndroidViewModel_androidKt.getApplication;

import android.app.Application;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import kotlinx.coroutines.CoroutineScope;

public class SensorViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mDatos;
    private SensorManager manager;
    private List<Sensor> sensores;
    private ManejaSensores maneja;
    private Sensor acelerometro;
    private ManejaSensores listener;
    public SensorViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<String> getMDatos(){
        if(mDatos==null){
            mDatos=new MutableLiveData<>();
        }
        return mDatos;
    }

    public void accederASensores(){
        manager=(SensorManager)getApplication().getSystemService(Context.SENSOR_SERVICE);
        sensores=manager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder cadena=new StringBuilder();
        for(Sensor s:sensores){
            cadena.append(s.getName()+" "+s.getStringType()+"\n");
        }

        mDatos.setValue(cadena.toString());


    }
    // Activar el acelerómetro
   /* public void activarAcelerometro() {
        manager = (SensorManager) getApplication().getSystemService(Context.SENSOR_SERVICE);

        if (manager != null) {
            List<Sensor> sensores = manager.getSensorList(Sensor.TYPE_ACCELEROMETER);
            if (!sensores.isEmpty()) {
                acelerometro = sensores.get(0);
                listener = new ManejaSensores();
                manager.registerListener(listener, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
                mDatos.setValue("✅ Acelerómetro activado");
            } else {
                mDatos.setValue("⚠️ No se encontró acelerómetro en el dispositivo");
            }
        }
    }

    // Desactivar el acelerómetro
    public void desactivarAcelerometro() {
        if (manager != null && listener != null) {
            manager.unregisterListener(listener);
            mDatos.setValue("⏹ Lecturas desactivadas");
        }
    }*/

    public void activarLecturas(){
        manager=(SensorManager) getApplication().getSystemService(Context.SENSOR_SERVICE);
        sensores=manager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        maneja=new ManejaSensores();
        manager.registerListener(maneja,sensores.get(0),SensorManager.SENSOR_DELAY_GAME);
    }

    public void desactivarLecturas(){
        manager.unregisterListener(maneja);
    }
    public void activarProximidad(){
        manager=(SensorManager) getApplication().getSystemService(Context.SENSOR_SERVICE);
        sensores=manager.getSensorList(Sensor.TYPE_PROXIMITY);
        maneja=new ManejaSensores();
        manager.registerListener(maneja,sensores.get(0),SensorManager.SENSOR_DELAY_GAME);
    }

    public void desactivarProximidad(){
        if (manager != null && maneja != null) {
            manager.unregisterListener(maneja);
        }
      //  manager.unregisterListener(maneja);
    }

    private class ManejaSensores implements SensorEventListener {


        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

           /* float x=sensorEvent.values[0];
            float y=sensorEvent.values[1];
            float z=sensorEvent.values[2];
            mDatos.setValue("x: "+x*10+" y: "+y*10+" z:"+z*10);*/
            float distancia = sensorEvent.values[0]; // distancia en cm proximidadtrae un solo valor
            mDatos.setValue("Distancia: " + distancia + " cm");
        }
    }
}