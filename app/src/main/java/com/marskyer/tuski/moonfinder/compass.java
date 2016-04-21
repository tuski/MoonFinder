package com.marskyer.tuski.moonfinder;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class compass extends Activity implements SensorEventListener {

    // define the display assembly compass picture
    private ImageView image;
    private TextView txtvw;
    Geocoder geocoder;
    String bestProvider;
    List<Address> user = null;
    double lat;
    double lng;
    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
    SharedPreferences.Editor editor = pref.edit();
    // record the compass picture angle turned
    private float currentDegree = 0f;

    // device sensor manager
    private SensorManager mSensorManager;

    TextView tvHeading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compass);

        // our compass image
        image = (ImageView) findViewById(R.id.imageViewCompass);
        txtvw = (TextView) findViewById(R.id.moon_dtl_txt);


        //location
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        bestProvider = lm.getBestProvider(criteria, false);
        try {
            //location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location location = lm.getLastKnownLocation(bestProvider);
            if (location == null){
                Toast.makeText(this, "Location Not found", Toast.LENGTH_LONG).show();
            }else{
                geocoder = new Geocoder(this);
                try {
                    user = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    //editor.putFloat("key_name3", "float value");
                    lat=(double)user.get(0).getLatitude();
                    lng=(double)user.get(0).getLongitude();
                    System.out.println(" DDD lat: " +lat+",  longitude: "+lng);

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (SecurityException e) {
            // lets the user know there is a problem with the gps
        }

        //Location location = lm.getLastKnownLocation(bestProvider);







         final double RAD_TO_DEG = 180.0 / Math.PI;

        /** Degrees to radians. */
        final double DEG_TO_RAD = 1.0 / RAD_TO_DEG;
        final double AU = 149597870.691;
        try {
            int year = 2016, month = 4, day = 18, h = 7, m = 30, s = 0;
            double obsLon = 86 * DEG_TO_RAD, obsLat = 24 * DEG_TO_RAD;
            SunMoonCalculator smc = new SunMoonCalculator(year, month, day, h, m, s, obsLon, obsLat);

            smc.calcSunAndMoon();

            txtvw.setText("Moon\n Az: " + (float) (smc.moonAz * RAD_TO_DEG) + "º\nEl: " + (float) (smc.moonEl * RAD_TO_DEG) + "º\nDist: "+(float) (smc.moonDist * AU)+"\nAge: "+(float) (smc.moonAge)+" days"+"\nRise: "+SunMoonCalculator.getDateAsString(smc.moonRise)+"\nSet: "+SunMoonCalculator.getDateAsString(smc.moonSet)+"\nLat:"+lat+"\nlong:"+lng);

/*
            System.out.println("Sun");
            System.out.println(" Az:      "+(float) (smc.sunAz * RAD_TO_DEG)+"º");
            System.out.println(" El:      "+(float) (smc.sunEl * RAD_TO_DEG)+"º");
            System.out.println(" Dist:    "+(float) (smc.sunDist)+" AU");
            System.out.println(" Rise:    "+SunMoonCalculator.getDateAsString(smc.sunRise));
            System.out.println(" Set:     "+SunMoonCalculator.getDateAsString(smc.sunSet));
            System.out.println(" Transit: "+SunMoonCalculator.getDateAsString(smc.sunTransit)+" (max. elev. "+(float) (smc.sunTransitElev * RAD_TO_DEG)+"º)");
            System.out.println("Moon");
            System.out.println(" Az:      "+(float) (smc.moonAz * RAD_TO_DEG)+"º");
            System.out.println(" El:      "+(float) (smc.moonEl * RAD_TO_DEG)+"º");
            System.out.println(" Dist:    "+(float) (smc.moonDist * AU)+" km");
            System.out.println(" Age:     "+(float) (smc.moonAge)+" days");
            System.out.println(" Rise:    "+SunMoonCalculator.getDateAsString(smc.moonRise));
            System.out.println(" Set:     "+SunMoonCalculator.getDateAsString(smc.moonSet));
            System.out.println(" Transit: "+SunMoonCalculator.getDateAsString(smc.moonTransit)+" (max. elev. "+(float) (smc.moonTransitElev * RAD_TO_DEG)+"º)");

            smc.setTwilight(SunMoonCalculator.TWILIGHT.TWILIGHT_ASTRONOMICAL);
            smc.calcSunAndMoon();

            System.out.println("");
            System.out.println("Astronomical twilights:");
            System.out.println("Sun");
            System.out.println(" Rise:    "+SunMoonCalculator.getDateAsString(smc.sunRise));
            System.out.println(" Set:     "+SunMoonCalculator.getDateAsString(smc.sunSet));
            System.out.println("Moon");
            System.out.println(" Rise:    "+SunMoonCalculator.getDateAsString(smc.moonRise));
            System.out.println(" Set:     "+SunMoonCalculator.getDateAsString(smc.moonSet));
*/
            // Expected accuracy in over 1800 - 2200:
            // - Sun: 0.005 deg or 20 arcsec. 1-2s in rise/set/transit times.
            // - Mon: 0.02 deg or better. 10s or better in rise/set/transit times.
            //        In most of the cases the actual accuracy in the Moon will be better, but it is not guaranteed.
        } catch (Exception exc) {
            exc.printStackTrace();
        }



        int az=185;

        if(az==180)
        image.setImageResource(R.drawable.compass_180);
        else
            image.setImageResource(R.drawable.compass_185);

        // TextView that will tell the user what degree is he heading
        tvHeading = (TextView) findViewById(R.id.tvHeading);

        // initialize your android device sensor capabilities
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // for the system's orientation sensor registered listeners
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // to stop the listener and save battery
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        // get the angle around the z-axis rotated
        float degree = Math.round(event.values[0]);

        tvHeading.setText("Heading: " + Float.toString(degree) + " degrees");

        // create a rotation animation (reverse turn degree degrees)
        RotateAnimation ra = new RotateAnimation(
                currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        // how long the animation will take place
        ra.setDuration(210);

        // set the animation after the end of the reservation status
        ra.setFillAfter(true);

        // Start the animation
        image.startAnimation(ra);
        currentDegree = -degree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not in use
    }
}