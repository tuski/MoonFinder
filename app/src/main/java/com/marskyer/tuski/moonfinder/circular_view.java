package com.marskyer.tuski.moonfinder;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class circular_view extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_view);

        final double RAD_TO_DEG = 180.0 / Math.PI;

         final double DEG_TO_RAD = 1.0 / RAD_TO_DEG;
        TextView rslt=  (TextView) findViewById(R.id.textView);


        try {


            int year = 2016, month = 4, day = 18, h = 7, m = 30, s = 0;
            double obsLon = 86 * DEG_TO_RAD, obsLat = 24 * DEG_TO_RAD;
            SunMoonCalculator smc = new SunMoonCalculator(year, month, day, h, m, s, obsLon, obsLat);

            smc.calcSunAndMoon();


            rslt.setText("Sun\n Az:"+(float) (smc.sunAz * smc.RAD_TO_DEG)+"º");

//            System.out.println("Sun");
//            System.out.println(" Az:      "+(float) (smc.sunAz * smc.RAD_TO_DEG)+"º");
//            System.out.println(" El:      "+(float) (smc.sunEl * RAD_TO_DEG)+"º");
//            System.out.println(" Dist:    "+(float) (smc.sunDist)+" AU");
//            System.out.println(" Rise:    "+SunMoonCalculator.getDateAsString(smc.sunRise));
//            System.out.println(" Set:     "+SunMoonCalculator.getDateAsString(smc.sunSet));
//            System.out.println(" Transit: "+SunMoonCalculator.getDateAsString(smc.sunTransit)+" (max. elev. "+(float) (smc.sunTransitElev * RAD_TO_DEG)+"º)");
//            System.out.println("Moon");
//            System.out.println(" Az:      "+(float) (smc.moonAz * RAD_TO_DEG)+"º");
//            System.out.println(" El:      "+(float) (smc.moonEl * RAD_TO_DEG)+"º");
//            System.out.println(" Dist:    "+(float) (smc.moonDist * AU)+" km");
//            System.out.println(" Age:     "+(float) (smc.moonAge)+" days");
//            System.out.println(" Rise:    "+SunMoonCalculator.getDateAsString(smc.moonRise));
//            System.out.println(" Set:     "+SunMoonCalculator.getDateAsString(smc.moonSet));
//            System.out.println(" Transit: "+SunMoonCalculator.getDateAsString(smc.moonTransit)+" (max. elev. "+(float) (smc.moonTransitElev * RAD_TO_DEG)+"º)");
//
//            smc.setTwilight(TWILIGHT.TWILIGHT_ASTRONOMICAL);
//            smc.calcSunAndMoon();
//
//            System.out.println("");
//            System.out.println("Astronomical twilights:");
//            System.out.println("Sun");
//            System.out.println(" Rise:    "+SunMoonCalculator.getDateAsString(smc.sunRise));
//            System.out.println(" Set:     "+SunMoonCalculator.getDateAsString(smc.sunSet));
//            System.out.println("Moon");
//            System.out.println(" Rise:    "+SunMoonCalculator.getDateAsString(smc.moonRise));
//            System.out.println(" Set:     "+SunMoonCalculator.getDateAsString(smc.moonSet));

            // Expected accuracy in over 1800 - 2200:
            // - Sun: 0.005 deg or 20 arcsec. 1-2s in rise/set/transit times.
            // - Mon: 0.02 deg or better. 10s or better in rise/set/transit times.
            //        In most of the cases the actual accuracy in the Moon will be better, but it is not guaranteed.
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }


    }



