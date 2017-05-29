package pl.slovvik.zad6;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.slovvik.zad6.compass.Compass;
import pl.slovvik.zad6.sensors.AllSensors;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private TextView compassInfo;
    private Button compassButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        compassInfo = (TextView) findViewById(R.id.compass_info);
        compassButton = (Button) findViewById(R.id.compass_button);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean enabled = !sensorManager.getSensorList(Sensor.TYPE_LIGHT).isEmpty();

        TextView lightView = (TextView) findViewById(R.id.lighinfo);
        lightView.setText(enabled ? "Active" : "UnActive");
        lightView.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.light_button).setEnabled(enabled);

        enabled = !sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).isEmpty();
        TextView accelView = (TextView) findViewById(R.id.accel_info);
        accelView.setText(enabled ? "Active" : "UnActive");
        accelView.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.accel_button).setEnabled(enabled);

        final LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        TextView gpsView = (TextView) findViewById(R.id.gps_info);
        gpsView.setText(enabled ? "Active" : "UnActive");
        gpsView.setTextColor(enabled ? Color.GREEN : Color.RED);
        findViewById(R.id.gps_button).setEnabled(enabled);

        compass();
    }

    public final void showSensorParameters(final View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.gps_button:
                intent = new Intent(this, GPS.class);
                break;
            case R.id.compass_button:
                intent = new Intent(this, Compass.class);
                break;
            default:
                intent = new Intent(this, ASensor.class);
                if (view.getId() == R.id.light_button)
                    intent.putExtra("sensorType", Sensor.TYPE_LIGHT);
                else if (view.getId() == R.id.accel_button)
                    intent.putExtra("sensorType", Sensor.TYPE_ACCELEROMETER);
                break;
        }
        startActivity(intent);
    }

    public void showAllSensors(View view) {
        Intent intent = new Intent(this, AllSensors.class);
        startActivity(intent);
    }

    private void compass() {
        boolean active = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION) != null;
        compassInfo.setText(active ? "Active" : "UnActive");
        compassInfo.setTextColor(active ? Color.GREEN : Color.RED);
        compassButton.setEnabled(active);
    }
}
