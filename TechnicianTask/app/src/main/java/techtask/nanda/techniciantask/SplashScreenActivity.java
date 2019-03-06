package techtask.nanda.techniciantask;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {
    CountDownTimer timer;
    long splashDuration = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        countDownTimer();
    }

    private void countDownTimer(){
        timer = new CountDownTimer(splashDuration, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                gotoMainActivity();
            }
        }.start();
    }

    private void gotoMainActivity(){
        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
        finish();
    }
}
