package medical.medicalapps;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import medical.medicalapps.utils.Config;

public class NotificationActivity extends AppCompatActivity {
    InputStream input = null;
    String line = null;
    String result = null;

    EditText txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        toInitialize();
    }

    public void toInitialize() {
        txtEmail = (EditText) findViewById(R.id.emaileditid);
        Button btnLogin = (Button) findViewById(R.id.resultid);
        txtEmail.setText(LoginActivity.session);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NotifyTask().execute(new String[]{txtEmail.getText().toString()});
            }
        });
    }

    public class NotifyTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... parm) {
            try {
                String ip = Config.ipNotify;
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("EMAIL", parm[0]));

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(ip);
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                input = entity.getContent();
            } catch (Exception e) {
                e.printStackTrace();

            }
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(input, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                input.close();
                result = sb.toString();
                Log.e("Result is : ", result.toString());
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Webservice 2", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(final String success) {
            TextView txtName = ( TextView) findViewById(R.id.txtName);
            TextView txtDosage = ( TextView) findViewById(R.id.txtDosage);
            TextView txtSide = ( TextView) findViewById(R.id.txtSide);
            TextView txtSpecial = ( TextView) findViewById(R.id.txtSpecial);
            String result[] = success.split(":");

            txtName.setText("GENERICNAME:"+result[0]);
            txtDosage.setText("DOSAGE:"+result[1]);
            txtSide.setText("SIDEEFFORTS:"+result[2]);
            txtSpecial.setText("SPECIAL:"+result[3]);
        }

        @Override
        protected void onCancelled() {

        }
    }

}
