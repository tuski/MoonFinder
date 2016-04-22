package com.marskyer.tuski.moonfinder;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class compass extends Activity implements SensorEventListener {

    // define the display assembly compass picture
    ImageButton btnnxt;
    private ImageView image;
    private TextView txtvw;
    Geocoder geocoder;
    String bestProvider;
    List<Address> user = null;
    double lat;
    double lng;
    int az;
    final double RAD_TO_DEG = 180.0 / Math.PI;

    final double DEG_TO_RAD = 1.0 / RAD_TO_DEG;
    final double AU = 149597870.691;
    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR), month =1+ cal.get(Calendar.MONTH), day = cal.get(Calendar.DAY_OF_MONTH), h = 7, m = 30, s = 0;
    double obsLon = 88.63 * DEG_TO_RAD, obsLat = 24.36 * DEG_TO_RAD;

   // SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
   //SharedPreferences.Editor editor = pref.edit();
    //SharedPreferences.Editor editor = settings.edit();
    // record the compass picture angle turned
    private float currentDegree = 0f;

    // device sensor manager
    private SensorManager mSensorManager;

    TextView tvHeading;
   // Button  btn = (Button) findViewById(R.id.btnnxt);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.compass);

        // our compass image
        image = (ImageView) findViewById(R.id.imageViewCompass);
        txtvw = (TextView) findViewById(R.id.moon_dtl_txt);
        btnnxt= (ImageButton) findViewById(R.id.btnnxt);

btnnxt.setBackgroundResource(R.drawable.px01);

        btnnxt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(compass.this, circular_view.class);
                startActivity(intent);
                // do whatever we wish!
            }
        });
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
                    lat = (double)user.get(0).getLatitude();
                    lng=(double)user.get(0).getLongitude();
                    //editor.putFloat("latitude", (float) lat);
                    //editor.putFloat("longitude", (float) lng); // getting Float
                    //editor.commit();
                  //  System.out.println(" DDD lat: " + lat + ",  longitude: " + lng);

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (SecurityException e) {
            // lets the user know there is a problem with the gps
        }

        //Location location = lm.getLastKnownLocation(bestProvider);




          //  lat=pref.getFloat("latitude", Float.parseFloat(""));
       // lng=pref.getFloat("longitude", Float.parseFloat(""));


        final double RAD_TO_DEG = 180.0 / Math.PI;

        /** Degrees to radians. */
        final double DEG_TO_RAD = 1.0 / RAD_TO_DEG;
        final double AU = 149597870.691;
        Calendar cal = Calendar.getInstance();


        try {
            int year = cal.get(Calendar.YEAR), month =1+ cal.get(Calendar.MONTH), day = cal.get(Calendar.DAY_OF_MONTH), h = cal.get(Calendar.HOUR), m = cal.get(Calendar.MINUTE), s = 0;
            double obsLon = lng * DEG_TO_RAD, obsLat = lat * DEG_TO_RAD;
            SunMoonCalculator smc = new SunMoonCalculator(year, month, day, h, m, s, obsLon, obsLat);

            smc.calcSunAndMoon();

            txtvw.setText("Moon\n Az: " + (float) (smc.moonAz * RAD_TO_DEG) + "º\nEl: " + (float) (smc.moonEl * RAD_TO_DEG) + "º\nDist: " + (float) (smc.moonDist * AU) + "\nAge: " + (float) (smc.moonAge) + " days" + "\nRise: " + SunMoonCalculator.getDateAsString(smc.moonRise) + "\nSet: " + SunMoonCalculator.getDateAsString(smc.moonSet) + "\nLat:" + lat + "\nlong:" + lng);
            az= (int) (smc.moonAz * RAD_TO_DEG);
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        Toast error_toast = Toast.makeText(getApplicationContext(), "az:"+az, Toast.LENGTH_SHORT);
        error_toast.show();


        if(az>0 && az<=15)
        image.setImageResource(R.drawable.c1);

        else if(az>15 && az<=30)
            image.setImageResource(R.drawable.c2);
        else if(az>30 && az<=45)
            image.setImageResource(R.drawable.c3);
        else if(az>45 && az<=60)
            image.setImageResource(R.drawable.c4);

        else if(az>60 && az<=75)
            image.setImageResource(R.drawable.c5);
        else if(az>75 && az<=90)
            image.setImageResource(R.drawable.c6);
        else if(az>90 && az<=105)
            image.setImageResource(R.drawable.c7);
        else if(az>105 && az<=120)
            image.setImageResource(R.drawable.c8);

        else if(az>120 && az<=135)
            image.setImageResource(R.drawable.c9);
        else if(az>135 && az<=150)
            image.setImageResource(R.drawable.c10);
        else if(az>150 && az<=165)
            image.setImageResource(R.drawable.c11);
        else if(az>165 && az<=180)
            image.setImageResource(R.drawable.c12);

        else if(az>180 && az<=195)
            image.setImageResource(R.drawable.c13);
        else if(az>195 && az<=210)
            image.setImageResource(R.drawable.c14);
        else if(az>210 && az<=225)
            image.setImageResource(R.drawable.c15);
        else if(az>225 && az<=240)
            image.setImageResource(R.drawable.c16);

        else if(az>240 && az<=255)
            image.setImageResource(R.drawable.c17);
        else if(az>255 && az<=270)
            image.setImageResource(R.drawable.c18);
        else if(az>270 && az<=285)
            image.setImageResource(R.drawable.c19);
        else if(az>285 && az<=300)
            image.setImageResource(R.drawable.c20);

        else if(az>300 && az<=315)
            image.setImageResource(R.drawable.c21);
        else if(az>315 && az<=330)
            image.setImageResource(R.drawable.c22);
        else if(az>330 && az<=345)
            image.setImageResource(R.drawable.c23);
        else if(az>345 && az<=360)
            image.setImageResource(R.drawable.c24);





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