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
            tx.setText("Moon\n ag: " + (float) (smc.moonAz * RAD_TO_DEG) + "º\nEl: " + (float) (smc.moonEl * RAD_TO_DEG) + "º\nDist: "+(float) (smc.moonDist * AU)+"\nAge: "+(float) (smc.moonAge)+" days"+"\nRise: "+SunMoonCalculator.getDateAsString(smc.moonRise)+"\nSet: "+SunMoonCalculator.getDateAsString(smc.moonSet)+"\nday= "+day);


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
                    setimg((float) (smc.moonAge));
                   // img.setImageResource(R.drawable.px01);
                    tx.setText("Moon\n ag: " + (float) (smc.moonAz * RAD_TO_DEG) + "º\nEl: " + (float) (smc.moonEl * RAD_TO_DEG) + "º\nDist: "+(float) (smc.moonDist * AU)+"\nAge: "+(float) (smc.moonAge)+" days"+"\nRise: "+SunMoonCalculator.getDateAsString(smc.moonRise)+"\nSet: "+SunMoonCalculator.getDateAsString(smc.moonSet)+"\nday= "+day);


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
                    setimg((float) (smc.moonAge));
                    //img.setImageResource(R.drawable.px01);
                    tx.setText("Moon\n ag: " + (float) (smc.moonAz * RAD_TO_DEG) + "º\nEl: " + (float) (smc.moonEl * RAD_TO_DEG) + "º\nDist: "+(float) (smc.moonDist * AU)+"\nAge: "+(float) (smc.moonAge)+" days"+"\nRise: "+SunMoonCalculator.getDateAsString(smc.moonRise)+"\nSet: "+SunMoonCalculator.getDateAsString(smc.moonSet)+"\nday= "+day);


                } catch (Exception exc) {
                    exc.printStackTrace();
                }

            }
        });


    }


    void setimg(float ag)
    {
        if(ag>0 && ag<=0.5)
            img.setImageResource(R.drawable.p0);

        else if(ag>.5 && ag<=1)
            img.setImageResource(R.drawable.p1);
        else if(ag>1 && ag<=2)
            img.setImageResource(R.drawable.p3);
        else if(ag>2 && ag<=3)
            img.setImageResource(R.drawable.p4);

        else if(ag>3 && ag<=4)
            img.setImageResource(R.drawable.p5);
        else if(ag>4 && ag<=5)
            img.setImageResource(R.drawable.p6);
        else if(ag>5 && ag<=6)
            img.setImageResource(R.drawable.p7);
        else if(ag>6 && ag<=7)
            img.setImageResource(R.drawable.p8);
        else if(ag>7 && ag<= 8)
            img.setImageResource(R.drawable.p9);

        else if(ag>8 && ag<=9)
            img.setImageResource(R.drawable.p10);
        else if(ag>9 && ag<=10)
            img.setImageResource(R.drawable.p11);
        else if(ag>10 && ag<=11)
            img.setImageResource(R.drawable.p12);
        else if(ag>11 && ag<=12)
            img.setImageResource(R.drawable.p13);
        else if(ag>12 && ag<=13)
            img.setImageResource(R.drawable.p14);

        else if(ag>13 && ag<=14)
            img.setImageResource(R.drawable.p15);

        else if(ag>14 && ag<=15)
            img.setImageResource(R.drawable.p16);
        else if(ag>15 && ag<=16)
            img.setImageResource(R.drawable.p17);
        else if(ag>16 && ag<=17)
            img.setImageResource(R.drawable.p18);
        else if(ag>17 && ag<=18)
            img.setImageResource(R.drawable.p19);

        else if(ag>18 && ag<=19)
            img.setImageResource(R.drawable.p20);
        else if(ag>19 && ag<=20)
            img.setImageResource(R.drawable.p21);
        else if(ag>20 && ag<=21)
            img.setImageResource(R.drawable.p22);
        else if(ag>21 && ag<=22)
            img.setImageResource(R.drawable.p23);

        else if(ag>22 && ag<=23)
            img.setImageResource(R.drawable.p24);
        else if(ag>23 && ag<=24)
            img.setImageResource(R.drawable.p25);
        else if(ag>24 && ag<=25)
            img.setImageResource(R.drawable.p26);
        else if(ag>25 && ag<=26)
            img.setImageResource(R.drawable.p27);
        else if(ag>26 && ag<=27)
            img.setImageResource(R.drawable.p28);
        else if(ag>27 && ag<=28)
            img.setImageResource(R.drawable.p29);
        else if(ag>28 && ag<=29)
            img.setImageResource(R.drawable.p30);

    }


}



