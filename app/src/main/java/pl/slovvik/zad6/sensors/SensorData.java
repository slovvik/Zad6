package pl.slovvik.zad6.sensors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import pl.slovvik.zad6.R;

public class SensorData extends AppCompatActivity {

    private TextView sensorData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_data);

        sensorData = (TextView) findViewById(R.id.sensor_data);

        sensorData.setText(getIntent().getStringExtra("sensor"));
    }
}
