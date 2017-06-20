package com.webdeveloper5.listsensors;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    List<Sensor> sensor;
    String output = "";
    boolean doRefresh;
    Map<Integer, String> aMap = new HashMap<Integer, String>();

    public final SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor arg0, int arg1) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            Sensor sensor = event.sensor;
            String s = "";

                s = sensor.getName() + " :: \r\n";
                for (int i = 0; i < event.values.length; i++)
                    s += Float.toString(event.values[i]) + "\r\n";
                s += "\r\n";
                if (aMap.containsKey(sensor.getType()))
                aMap.remove(sensor.getType());
                aMap.put(sensor.getType(),s);



            output = "";
            for (Map.Entry<Integer, String> entry : aMap.entrySet())
            {
                output += entry.getValue()  + "\r\n";
            }


            if (doRefresh == true) {
                TextView myTextView = (TextView) findViewById(R.id.textView);
                myTextView.setText(output);
                doRefresh = false;
            }

            //output += s;


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        /*
        sensor = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for (int i = 0; i < sensor.size(); i++) {
            output += sensor.get(i).getType() + " \r\n\r\n";
        }

        TextView myTextView = (TextView)findViewById(R.id.textView);
        myTextView.setText(output);
        */


        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                doRefresh = true;
            }
        },0,500);


        final Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Button button = (Button)findViewById(R.id.button);
                        button.setVisibility(View.GONE);
                        List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
                        for (Sensor sensor : sensors)
                        {
                            mSensorManager.registerListener(mSensorListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
                            Log.d("registered", "!!!registered" + sensor.getName());

                        }
                    }
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
