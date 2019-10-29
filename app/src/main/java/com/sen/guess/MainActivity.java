package com.sen.guess;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String TAG = MainActivity.class.getSimpleName();
    int secret = new Random().nextInt(10) + 1;
    private TextView number;
    String counter;
    int TextView = 0;
    private android.widget.TextView edCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "secret: " + secret);
        edCounter = findViewById(R.id.TextView);
        edCounter.setText(TextView + "");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        number = findViewById(R.id.guess);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int secret2 = new Random().nextInt(10) + 1;
                secret = secret2;
                Log.d(TAG, "secret: " + secret2);
                counter = "";
                number.setText(String.valueOf(counter));
            }
        });
    }

    public void test(View view) {
        int num = Integer.parseInt(number.getText().toString());
        TextView++;
        edCounter.setText(TextView+"");

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView = 0;
                edCounter.setText(TextView+"");
            }
        };

        if (num > secret) {
            Toast.makeText(MainActivity.this, "smaller", Toast.LENGTH_LONG).show();
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Hint")
                    .setMessage("smaller")
                    .setPositiveButton("OK",null)
                    .show();
        } else if (num < secret) {
            Toast.makeText(MainActivity.this, "largerer", Toast.LENGTH_LONG).show();
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Hint")
                    .setMessage("larger")
                    .setPositiveButton("OK", null)
                    .show();
        } else {
            Toast.makeText(MainActivity.this, "You guess it!", Toast.LENGTH_LONG).show();
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Hint")
                    .setMessage("You guess it!")
                    .setPositiveButton("OK",listener)
                    .show();

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
