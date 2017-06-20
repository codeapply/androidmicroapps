package com.example.savefile;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import com.example.savefile.FileOperations;


public class MainActivity extends AppCompatActivity {

        EditText fname, fcontent, fnameread;
        Button write, read;
        TextView filecon;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            fname = (EditText) findViewById(R.id.fname);
            fcontent = (EditText) findViewById(R.id.ftext);
            fnameread = (EditText) findViewById(R.id.fnameread);
            write = (Button) findViewById(R.id.btnwrite);
            read = (Button) findViewById(R.id.btnread);
            filecon = (TextView) findViewById(R.id.filecon);
            write.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    String filename = fname.getText().toString();
                    String filecontent = fcontent.getText().toString();
                    FileOperations fop = new FileOperations();
                    fop.write(filename, filecontent);
                    if (fop.write(filename, filecontent)) {
                        Toast.makeText(getApplicationContext(), filename + ".txt created", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "I/O error", Toast.LENGTH_SHORT).show();

                    }
                }
            });
            read.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    String readfilename = fnameread.getText().toString();
                    FileOperations fop = new FileOperations();
                    String text = fop.read(readfilename);
                    if (text != null) {
                        filecon.setText(text);
                    } else {
                        Toast.makeText(getApplicationContext(), "File not Found", Toast.LENGTH_SHORT).show();
                        filecon.setText(null);
                    }

                }
            });
        }
    }

    /*
    private void saveFile() {
        String state = Environment.getExternalStorageState();
        File filesDir;

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            filesDir = getExternalFilesDir(null);
            Log.i("log", "We can read and write the media: " + filesDir.getAbsolutePath()); // This is the local on the phone
        } else {
            // Load another directory, probably local memory
            filesDir = getFilesDir();
            Log.i("log", "Load another directory, probably local memory: " + filesDir.getAbsolutePath());
        }

        try {
            // Creates a trace file in the primary external storage space of the
            // current application.
            // If the file does not exists, it is created.
            //File traceFile = new File(((Context)this).getExternalFilesDir(null), "TraceFile.txt"); //This one saves to the internal file folder
            File traceFile = new File(filesDir, "TraceFile1.txt");

            Log.i("log", traceFile.getAbsolutePath());

            if (!traceFile.exists())
                traceFile.createNewFile();
            // Adds a line to the trace file
            BufferedWriter writer = new BufferedWriter(new FileWriter(traceFile, true /*append* /));
            writer.write("This is a test trace file.");
            writer.close();
            // Refresh the data so it can seen when the device is plugged in a
            // computer. You may have to unplug and replug the device to see the
            // latest changes. This is not necessary if the user should not modify
            // the files.
            MediaScannerConnection.scanFile((Context)(this),
                    new String[] { traceFile.toString() },
                    null,
                    null);

        } catch (IOException e) {
            Log.i("log", "Unable to write to the TraceFile.txt file.");
        }
    }
    private void saveFile2() {
        try {

            Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date today = Calendar.getInstance().getTime();
            String sDate = formatter.format(today);

            File traceFile = new File(((Context)this).getExternalFilesDir(null), "TraceFile2.txt");
            if (!traceFile.exists())
                traceFile.createNewFile();
            Log.d("eee", "eee");
            // Adds a line to the trace file
            BufferedWriter writer = new BufferedWriter(new FileWriter(traceFile, true /*append* /));
            writer.write("This is a test trace file. " + sDate);
            writer.close();
            // Refresh the data so it can seen when the device is plugged in a
            // computer. You may have to unplug and replug the device to see the
            // latest changes. This is not necessary if the user should not modify
            // the files.
            MediaScannerConnection.scanFile((Context) (this),
                    new String[]{traceFile.toString()},
                    null,
                    null);
        } catch (IOException e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }

    }


    public String read_file(Context context, String filename) {
        try {
            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        } catch (IOException e) {
            return "";
        }
    }


    public String read_file2(Context context, String filename) {


        File sdcard = Environment.getExternalStorageDirectory();
        Log.i("sd",sdcard.getPath());
        File local = getFilesDir();

//Get the text file
        Log.i("sdcard", sdcard.getPath());

            File file = new File(local,"TraceFile1.txt");
        if (!file.exists()) {
            Log.i("not ex","not ex");
            File file2 = new File(sdcard,"TraceFile1.txt");
            if (!file2.exists())
                Log.i("not ex2","not ex2"+getFilesDir());
        }
//Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
        }
    return text.toString();

    }



    ArrayList<File> allTXT = new ArrayList<File>();

    public void searchTXT(File dir){
        Log.i("ssss", "sssssss");
        try {
            if (!dir.exists())
                Log.i("nodir","nodir");

            File[] files = dir.listFiles();

       Log.i("DIR", "Found " + files.length + " in " + dir.getAbsolutePath());
        for (File file : files) {
            if(file.isFile()){
                allTXT.add(file);
                Log.i("TXT", file.getPath());
            }else if(file.isDirectory()){
                searchTXT(file.getAbsoluteFile());
            }
        }
        } catch (Exception e) {
            Log.i("err","msg"+e.getMessage().toString());
        }
    }

    private boolean isTXT(File file){
        boolean is = true;
        if(file.getName().endsWith(".txt")){
            is = true;
        }
        return is;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  saveFile();
               // saveFile2();
                Context context = getApplicationContext();
                String filename = "TraceFile1.txt";
                String str = read_file2(context, filename);
                TextView tv = (TextView)findViewById(R.id.TV);
                tv.setText("t:" + str.toString()+":t:");

                Log.i("www", "www");
                searchTXT(new File(getFilesDir().getAbsolutePath()));
                Log.i("all", Integer.toString(allTXT.size()));
                for (int i = 0; i < allTXT.size(); i++) {
                    tv.append(allTXT.get(i).getAbsolutePath());
                }
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });
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
*/