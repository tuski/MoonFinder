package com.marskyer.tuski.moonfinder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class circular_view extends Activity {

    ImageView img;
    TextView tx;
    final double RAD_TO_DEG = 180.0 / Math.PI;

    /** Degrees to radians. */
    final double DEG_TO_RAD = 1.0 / RAD_TO_DEG;
    final double AU = 149597870.691;
    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR), month =1+ cal.get(Calendar.MONTH), day = cal.get(Calendar.DAY_OF_MONTH), h = 7, m = 30, s = 0;
    double obsLon = 88.63 * DEG_TO_RAD, obsLat = 24.36 * DEG_TO_RAD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_view);
                 img= (ImageView) findViewById(R.id.imageView4);
                    tx = (TextView) findViewById(R.id.textView);
        Button  nextday  = (Button) findViewById(R.id.btn_nxtday);
        Button  prvsday  = (Button) findViewById(R.id.btn_prvsday);




        try {

            SunMoonCalculator smc = new SunMoonCalculator(year, month, day, h, m, s, obsLon, obsLat);

            smc.calcSunAndMoon();

            img.setImageResource(R.drawable.px01);
            tx.setText("Moon\n Az: " + (float) (smc.moonAz * RAD_TO_DEG) + "º\nEl: " + (float) (smc.moonEl * RAD_TO_DEG) + "º\nDist: "+(float) (smc.moonDist * AU)+"\nAge: "+(float) (smc.moonAge)+" days"+"\nRise: "+SunMoonCalculator.getDateAsString(smc.moonRise)+"\nSet: "+SunMoonCalculator.getDateAsString(smc.moonSet)+"\nday= "+day);


        } catch (Exception exc) {
            exc.printStackTrace();
        }




        ////next day view
        nextday.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                            day++;

                try {
                    SunMoonCalculator smc = new SunMoonCalculator(year, month, day, h, m, s, obsLon, obsLat);

                    smc.calcSunAndMoon();

                    img.setImageResource(R.drawable.px01);
                    tx.setText("Moon\n Az: " + (float) (smc.moonAz * RAD_TO_DEG) + "º\nEl: " + (float) (smc.moonEl * RAD_TO_DEG) + "º\nDist: "+(float) (smc.moonDist * AU)+"\nAge: "+(float) (smc.moonAge)+" days"+"\nRise: "+SunMoonCalculator.getDateAsString(smc.moonRise)+"\nSet: "+SunMoonCalculator.getDateAsString(smc.moonSet)+"\nday= "+day);


                } catch (Exception exc) {
                    exc.printStackTrace();
                }

            }
        });


        prvsday.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                day--;

                try {
                    SunMoonCalculator smc = new SunMoonCalculator(year, month, day, h, m, s, obsLon, obsLat);

                    smc.calcSunAndMoon();

                    img.setImageResource(R.drawable.px01);
                    tx.setText("Moon\n Az: " + (float) (smc.moonAz * RAD_TO_DEG) + "º\nEl: " + (float) (smc.moonEl * RAD_TO_DEG) + "º\nDist: "+(float) (smc.moonDist * AU)+"\nAge: "+(float) (smc.moonAge)+" days"+"\nRise: "+SunMoonCalculator.getDateAsString(smc.moonRise)+"\nSet: "+SunMoonCalculator.getDateAsString(smc.moonSet)+"\nday= "+day);


                } catch (Exception exc) {
                    exc.printStackTrace();
                }

            }
        });


    }
}



