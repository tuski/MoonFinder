package com.marskyer.tuski.moonfinder;

import android.app.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.InputStream;

public class phase_anim extends Activity {
    GifView gifView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phase_anim);

        gifView = (GifView) findViewById(R.id.gif_view);


    }


}
