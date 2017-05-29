package pl.slovvik.zad6;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ASensor extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManager = null;
    private int sensorType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asensor);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorType = getIntent().getIntExtra("sensorType", Sensor.TYPE_LIGHT);
        if (sensorType == Sensor.TYPE_LIGHT) setTitle("Swiatło");
        else if (sensorType == Sensor.TYPE_ACCELEROMETER) setTitle("Przyspieszenie");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        TextView data = (TextView) findViewById(R.id.txt_data);
        StringBuilder builder = new StringBuilder();

        if (sensorType == Sensor.TYPE_LIGHT) {
            builder.append("Ambient light level: ")
                    .append(event.values[0])
                    .append(" lux");
        } else if (sensorType == Sensor.TYPE_ACCELEROMETER) {
            builder.append("X acceleration: ")
                    .append(String.format("%7.4f", event.values[0]))
                    .append("m/s\u00B2\nY acceleration: ")
                    .append(String.format("%7.4f", event.values[1]))
                    .append("m/s\u00B2\nZ acceleration: ")
                    .append(String.format("%7.4f", event.values[2]))
                    .append("m/s\u00B2\n");
        }
        data.setText(builder);

        TextView status = (TextView) findViewById(R.id.txt_status);
        StringBuilder builder1 = new StringBuilder();
        builder1.append("\nAccuaracy ")
                .append(event.accuracy == 3 ? "High" : event.accuracy == 2 ? "Medium" : "Low");
        status.setText(builder1);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        final Sensor sensor = sensorManager.getSensorList(sensorType).get(0);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
