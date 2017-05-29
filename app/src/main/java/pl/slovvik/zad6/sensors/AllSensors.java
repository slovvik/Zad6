package pl.slovvik.zad6.sensors;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pl.slovvik.zad6.R;

public class AllSensors extends AppCompatActivity {

    private SensorManager sensorManager;
    private ListView sensorsList;
    private List<Sensor> deviceSensors;
    private List<String> deviceSensorsNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_sensors);

        sensorsList = (ListView) findViewById(R.id.sensors_list);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        deviceSensorsNames = new ArrayList<>();
        for (int i = 0; i < deviceSensors.size(); i++) {
            deviceSensorsNames.add(deviceSensors.get(i).getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, deviceSensorsNames);
        sensorsList.setAdapter(adapter);
    }
}
