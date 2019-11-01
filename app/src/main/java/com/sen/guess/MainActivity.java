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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String TAG = MainActivity.class.getSimpleName();
    int secret = new Random().nextInt(10) + 1;
    private TextView number;
    String counter;
    int count = 0;
    private android.widget.TextView edCounter;
    private ImageView result;
    private TextView information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "secret: " + secret);
        edCounter = findViewById(R.id.count);
        edCounter.setText("猜了" + count + "次");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        number = findViewById(R.id.guess);
        result = findViewById(R.id.result_image);
        result.setVisibility(View.GONE);
        information = findViewById(R.id.information);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int secret2 = new Random().nextInt(10) + 1;
                secret = secret2;
                Log.d(TAG, "secret: " + secret2);
                counter = "";
                number.setText(String.valueOf(counter));
                edCounter.setText("猜了" + count + "次");
                information.setText("");
                result.setVisibility(view.GONE);
            }
        });
    }

    public void test(View view) {
        int num = Integer.parseInt(number.getText().toString());
        result.setAlpha(1.0f);
        count++;
        edCounter.setText("猜了" + count + "次");
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edCounter.setText("恭喜猜中\n共猜了" + count + "次");
                count = 0;
            }
        };

        if (num > secret) {
            //Toast.makeText(MainActivity.this, "smaller", Toast.LENGTH_LONG).show();
            result.setVisibility(view.VISIBLE);
            result.setImageResource(R.drawable.angry);
            result.animate().alpha(0.0f).setDuration(1200);
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Hint")
                    .setMessage("smaller")
                    .setPositiveButton("OK",null)
                    .show();
            information.setText("<<<<<");
        } else if (num < secret) {
            //Toast.makeText(MainActivity.this, "largerer", Toast.LENGTH_LONG).show();
            result.setVisibility(view.VISIBLE);
            result.setImageResource(R.drawable.angry);
            result.animate().alpha(0.0f).setDuration(1200);
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Hint")
                    .setMessage("larger")
                    .setPositiveButton("OK", null)
                    .show();
            information.setText(">>>>>");
        } else {
            //Toast.makeText(MainActivity.this, "You guess it!", Toast.LENGTH_LONG).show();
            result.setVisibility(view.VISIBLE);
            result.setImageResource(R.drawable.happy);
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Hint")
                    .setMessage("You guess it!")
                    .setPositiveButton("OK",listener)
                    .show();
            information.setText("Great");
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
