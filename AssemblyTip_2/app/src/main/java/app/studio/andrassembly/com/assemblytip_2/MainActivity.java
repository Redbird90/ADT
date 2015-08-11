package app.studio.andrassembly.com.assemblytip_2;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TextView textViewTimer = (TextView) findViewById(R.id.textView);

        final CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String prefix = "Time Remaining: ";
                textViewTimer.setText(prefix + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Context context = getApplicationContext();
                Intent intent = new Intent(context, SecondaryActivity.class);
                startActivity(intent);
            }
        };

        Button startButton = (Button) findViewById(R.id.buttonStart);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.start();
            }
        });

        final Button pauseButton = (Button) findViewById(R.id.buttonPause);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            boolean paused;
            @Override
            public void onClick(View v) {
                try {
                    countDownTimer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (paused) {
                pauseButton.setText("Resume");
            }
        });

        setContentView(R.layout.activity_main);
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
