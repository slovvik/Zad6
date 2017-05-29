package pl.slovvik.zad6;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GPS extends AppCompatActivity implements LocationListener {

    private LocationManager locationManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        TextView data = (TextView) findViewById(R.id.txt_data_gps);
        data.setText("Czekam na dane GPS...");
    }

    @Override
    public void onLocationChanged(Location location) {
        TextView data = (TextView) findViewById(R.id.txt_data_gps);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Altitude: ")
                .append(location.getAltitude())
                .append("m\nBearing: ")
                .append(location.getBearing())
                .append("\u00B0\nLatitude: ")
                .append(location.getLatitude())
                .append("\nLongitude: ")
                .append(location.getLongitude())
                .append("\nSpeed: ")
                .append(location.getSpeed())
                .append("m/s");
        data.setText(stringBuilder);

        TextView status = (TextView) findViewById(R.id.txt_status_gps);
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Accuracy: ")
                .append(location.getAccuracy())
                .append("m");
        status.setText(stringBuilder1);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        if (LocationManager.GPS_PROVIDER.contentEquals(provider)) finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, this);
        else ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION
        }, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            locationManager.removeUpdates(this);
    }
}


























