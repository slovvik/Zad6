package pl.slovvik.zad6.compass;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import pl.slovvik.zad6.R;

public class Compass extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView heading;
    private TextView heading2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        heading = (TextView) findViewById(R.id.heading);
        heading2 = (TextView) findViewById(R.id.heading_2);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = Math.round(event.values[0]);
        String headingDirection = "";
        if (degree >= 0 && degree < 30) headingDirection = "N";
        if (degree >= 30 && degree < 60) headingDirection = "NE";
        if (degree >= 60 && degree < 120) headingDirection = "E";
        if (degree >= 120 && degree < 150) headingDirection = "SE";
        if (degree >= 150 && degree < 210) headingDirection = "S";
        if (degree >= 210 && degree < 240) headingDirection = "SW";
        if (degree >= 240 && degree < 300) headingDirection = "W";
        if (degree >= 300 && degree < 330) headingDirection = "NW";
        if (degree >= 330 && degree <= 360) headingDirection = "N";

        heading.setText("Heading: " + headingDirection);
        heading2.setText("Heading: " + degree + " degrees");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
