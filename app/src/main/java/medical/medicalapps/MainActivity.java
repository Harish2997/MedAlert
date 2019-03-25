package medical.medicalapps;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Locale myLocale;
    public static String language = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toInitialize();
    }
    public void setLocale(String lang) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();
    }
    public void toInitialize() {
        TextView txtDate = (TextView) findViewById(R.id.txtDate);
        DateFormat dateFormat = DateFormat.getDateInstance();
        txtDate.setText(dateFormat.format(new Date()));
    }
    public void loginAction(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.spinLanguage);
        String selected = spinner.getSelectedItem().toString();
        if(selected.equals("Tamil")) {
            setLocale("ta");
            language = "ta";
        }
        else {
            setLocale("en");
            language = "en";
          //  setLocale("en");
        }
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }
    public void generalAction(View view) {
        Intent intent = new Intent(MainActivity.this,GeneralActivity.class);
        startActivity(intent);
    }
}
