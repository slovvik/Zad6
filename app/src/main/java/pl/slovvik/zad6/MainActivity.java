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
    private TextView lightView;
    private TextView accelView;
    private TextView gpsView;
    private TextView gyroscopeView;
    private TextView pressureView;
    private TextView heartRateView;
    private TextView proximityView;
    private Button compassButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compassInfo = (TextView) findViewById(R.id.compass_info);
        compassButton = (Button) findViewById(R.id.compass_button);
        lightView = (TextView) findViewById(R.id.lighinfo);
        accelView = (TextView) findViewById(R.id.accel_info);
        gpsView = (TextView) findViewById(R.id.gps_info);
        gyroscopeView = (TextView) findViewById(R.id.gyroscope_info);
        pressureView = (TextView) findViewById(R.id.pressure_info);
        heartRateView = (TextView) findViewById(R.id.heart_rate_info);
        proximityView = (TextView) findViewById(R.id.proximity_info);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        light();
        accelerometer();
        gps();
        gyroscope();
        pressure();
        heartRate();
        proximity();
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

    private String getStatusAsString(boolean status) {
        return status ? getString(R.string.active): getString(R.string.active);
    }

    private void proximity() {
        boolean active = sensorManager.getSensorList(Sensor.TYPE_PROXIMITY) != null;
        proximityView.setText(getString(R.string.proximity) + " " + getStatusAsString(active));
        proximityView.setTextColor(active ? Color.GREEN : Color.RED);
    }

    private void heartRate() {
        boolean active = sensorManager.getSensorList(Sensor.TYPE_HEART_RATE) != null;
        heartRateView.setText(getString(R.string.heartRate) + " " + getStatusAsString(active));
        heartRateView.setTextColor(active ? Color.GREEN : Color.RED);
    }

    private void pressure() {
        boolean active = sensorManager.getSensorList(Sensor.TYPE_PRESSURE) != null;
        pressureView.setText(getString(R.string.pressure) + " " + getStatusAsString(active));
        pressureView.setTextColor(active ? Color.GREEN : Color.RED);
    }

    private void gyroscope() {
        boolean active = sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE) != null;
        gyroscopeView.setText(getString(R.string.gyroscope) + " " + getStatusAsString(active));
        gyroscopeView.setTextColor(active ? Color.GREEN : Color.RED);
    }

    private void gps() {
        final LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean active = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        gpsView.setText(getString(R.string.gps) + " " + getStatusAsString(active));
        gpsView.setTextColor(active ? Color.GREEN : Color.RED);
        findViewById(R.id.gps_button).setEnabled(active);
    }

    private void light() {
        boolean active = sensorManager.getSensorList(Sensor.TYPE_LIGHT) != null;
        lightView.setText(getString(R.string.light) + " " + getStatusAsString(active));
        lightView.setTextColor(active ? Color.GREEN : Color.RED);
        findViewById(R.id.light_button).setEnabled(active);
    }

    private void accelerometer() {
        boolean active = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER) != null;
        accelView.setText(getString(R.string.accelerometer) + " " + getStatusAsString(active));
        accelView.setTextColor(active ? Color.GREEN : Color.RED);
        findViewById(R.id.accel_button).setEnabled(active);
    }

    private void compass() {
        boolean active = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION) != null;
        compassInfo.setText(getString(R.string.compass) + " " + getStatusAsString(active));
        compassInfo.setTextColor(active ? Color.GREEN : Color.RED);
        compassButton.setEnabled(active);
    }
}
