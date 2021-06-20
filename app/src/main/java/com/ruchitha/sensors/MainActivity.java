package com.ruchitha.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.github.nisrulz.sensey.LightDetector;
import com.github.nisrulz.sensey.Sensey;
import com.github.nisrulz.sensey.ShakeDetector;
import com.github.nisrulz.sensey.TouchTypeDetector;

public class MainActivity extends AppCompatActivity {
    Switch s1,s2,s3;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s1=findViewById(R.id.shake);
        s2=findViewById(R.id.light);
        s3=findViewById(R.id.touch);
        tv=findViewById(R.id.mysensor);
        Sensey.getInstance().init(MainActivity.this);
        final ShakeDetector.ShakeListener shakeListener=new ShakeDetector.ShakeListener() {
            @Override
            public void onShakeDetected() {
                tv.setText("Shake Sensor Detected");
            }

            @Override
            public void onShakeStopped() {
                tv.setText("Shake Sensor Stopped");
            }
        };
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Sensey.getInstance().startShakeDetection(shakeListener);
                }
                else{
                    Sensey.getInstance().stopShakeDetection(shakeListener);
                }
            }
        });
        final LightDetector.LightListener lightListener=new LightDetector.LightListener() {
            @Override
            public void onDark() {
                tv.setText("It is a dark room");
            }

            @Override
            public void onLight() {
                tv.setText("It is a light room");
            }
        };
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Sensey.getInstance().startLightDetection(lightListener);
                }
                else {
                    Sensey.getInstance().stopLightDetection(lightListener);
                }
            }
        });
        final TouchTypeDetector.TouchTypListener touchTypListener=new TouchTypeDetector.TouchTypListener() {
            @Override
            public void onDoubleTap() {
                tv.setText("Double Tap");
            }

            @Override
            public void onLongPress() {
                tv.setText("Long Press");
            }

            @Override
            public void onScroll(int i) {
                tv.setText("Scroll");
            }

            @Override
            public void onSingleTap() {
                tv.setText("Single Tap");

            }

            @Override
            public void onSwipe(int i) {
                tv.setText("Swipe");

            }

            @Override
            public void onThreeFingerSingleTap() {
                tv.setText("Three Finger Single Tap");

            }

            @Override
            public void onTwoFingerSingleTap() {
                tv.setText("Two Finger Single Tap");

            }
        };
        s3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Sensey.getInstance().startTouchTypeDetection(MainActivity.this,touchTypListener);
                }
                else{
                    Sensey.getInstance().stopTouchTypeDetection();
                }
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Sensey.getInstance().setupDispatchTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Sensey.getInstance().stop();
    }
}