package medical.medicalapps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;


public class SplashScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        Thread background = new Thread() {
            public void run() {

                try {
                    sleep(5*1000);

                    Intent i=new Intent(getBaseContext(),MainActivity.class);
                    startActivity(i);

                    finish();

                } catch (Exception e) {

                }
            }
        };



        background.start();

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }
}





