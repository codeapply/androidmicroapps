package com.example.httpjavapost;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        new PostTask().execute("http://wd5.pl/script.php","username=d&password=c");
                    }
                }
        );
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    private class PostTask extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {

            Log.d(" TryLoginCheck ", "Here");
            HttpURLConnection connection;
            OutputStreamWriter request = null;

            URL url = null;
            String response = null;
            String temp=null;
            String parameters = params[1];//"username=a&password=b";
            //System.out.println("UserName"+mUsername+"\n"+"password"+mPassword);
            Log.d("Parameters",parameters);
            try
            {
                Log.d("aaaaaaa","a");
                url = new URL(params[0]);
                Log.d("z","a");
                connection = (HttpURLConnection) url.openConnection();
                Log.d("q","a");
                connection.setDoOutput(true);
                Log.d("e", "a");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestMethod("POST");
                Log.d("b", "a");
                request = new OutputStreamWriter(connection.getOutputStream());
                Log.d("d","a");
                request.write(parameters);
                Log.d("e","a");
                request.flush();
                Log.d("d","a");
                request.close();
                Log.d("c","a");
                String line = "";
                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null)
                {

                    sb.append(line + "\n");
                }
                temp=sb.toString();
                Log.d("Temp",temp);
                // Response from server after login process will be stored in response variable.
                response = sb.toString();
                Log.d("Response",response);
                Log.d("Sb Value",sb.toString());
                isr.close();
                reader.close();


            }
            catch(IOException e)
            {
                // Toast.makeText(this, e.toString(), 0).show();
            }
            // Log.d("Response",response);
            return response;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
