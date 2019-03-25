package medical.medicalapps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.widget.TextView;

public class FeverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fever);
        toInitialize();
    }
    public void toInitialize() {
        TextView textView = (TextView) findViewById(R.id.txtLink);
        textView.setText("http://www.google.com");
        Linkify.addLinks(textView, Linkify.WEB_URLS);
    }
}
