package medical.medicalapps;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class UpdateActivity extends AppCompatActivity {

    InputStream input = null;
    String line = null;
    String result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        toInitialize();
    }

    public void toInitialize() {
        labelEmail = (EditText) findViewById(R.id.txtEmail);
        labelEmail.setText(LoginActivity.session);

        txtName = (EditText) findViewById(R.id.txtName);
        txtDays = (EditText) findViewById(R.id.txtDays);
        txtProblem = (EditText) findViewById(R.id.txtProblem);

    }

    public void updateAction(View view) {

        String email = labelEmail.getText().toString();
        String name =txtName.getText().toString();
        String days = txtDays.getText().toString();
        String problem = txtProblem.getText().toString();

        new RegisterTask().execute(new String[]{email, name, days, problem});
    }

    class RegisterTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... parm) {
            try {
                String ip = Config.ipUpdate;
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("EMAIL", parm[0]));
                nameValuePairs.add(new BasicNameValuePair("NAME", parm[1]));
                nameValuePairs.add(new BasicNameValuePair("DAYS", parm[2]));
                nameValuePairs.add(new BasicNameValuePair("SYMPTOMS", parm[3]));
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(ip);
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                input = entity.getContent();
                System.out.println("Sirius : " + ip);
                Log.e("Webservice 1", "Sirius : " + ip);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Webservice 1", e.toString());
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
                System.out.println(result);

                if (result != null) {
                    final String received = result;
                    if (received.contains("Already")) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        received, Toast.LENGTH_LONG).show();
                            }
                        });
                    } else {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Registered Successfully",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Webservice 2", e.toString());
            }
            return result;
        }

    }

    EditText txtName, txtDays, txtProblem, labelEmail;
}
