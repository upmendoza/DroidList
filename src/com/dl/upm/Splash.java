package com.dl.upm;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
//import android.text.Layout;
import android.view.Display;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
//import android.widget.Button;
import android.widget.ImageView;
//import android.widget.Spinner;
//import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import android.app.ActionBar;

/**
 * Created by IntelliJ IDEA.
 * User: Ulises
 * Date: 20/03/12
 * Time: 11:16
 * To change this template use File | Settings | File Templates.
 */

public class Splash extends Activity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        return false;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animation();

    }

    void animation () {
        setContentView(R.layout.splash);
        ImageView IMG = (ImageView) findViewById(R.id.logo);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width_screen = size.x;
        int height_screen = size.y;
        int imgWidth = (int ) (((double) width_screen) * 0.25);
        int imgHeight = (int) ((double) imgWidth * (1.16));
        IMG.setMaxWidth(imgWidth);
        IMG.setMaxHeight(imgHeight);
        Animation A = AnimationUtils.loadAnimation(this, R.anim.rotate);
        A.reset();
        //Button btn = (Button) findViewById(R.id.btn_ok);
        //TextView texto = (TextView) findViewById(R.id.txt_DR);
        IMG.clearAnimation();//btn.clearAnimation();texto.clearAnimation();
        IMG.startAnimation(A);//btn.startAnimation(A);texto.startAnimation(A);


        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                Intent i = new Intent(Splash.this, Catalogo.class);
                startActivity(i);
                finish();

            }
        };
        Timer t = new Timer();
        t.schedule(task, 3500);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        animation();
    }
}
