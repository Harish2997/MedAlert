package medical.medicalapps;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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
import medical.medicalapps.utils.TamilUtil;

public class LoginActivity extends AppCompatActivity {

    InputStream input = null;
    String line = null;
    String result = null;

    public static String session = "";
    private EditText txtUser;
    private EditText txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toInitialize();
    }

    public void toInitialize() {
        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPass = (EditText) findViewById(R.id.txtPass);
        TextView labelUser = (TextView) findViewById(R.id.labelUser);
        TextView labelPass = (TextView) findViewById(R.id.labelPass);
        TextView btnLogin = (TextView) findViewById(R.id.btnLogin);
        TextView btnSigin = (TextView) findViewById(R.id.btnSignin);
        if (MainActivity.language.equals("ta")) {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Bamini.ttf");
            labelUser.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.username)));
            labelUser.setTypeface(typeface);

            labelPass.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.password)));
            labelPass.setTypeface(typeface);

            btnLogin.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.submit_btn)));
            btnLogin.setTypeface(typeface);

            btnSigin.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.submit_new)));
            btnSigin.setTypeface(typeface);
        }/* else {

            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Ubuntu-M.ttf");

            labelUser.setText(getResources().getString(R.string.username));
            labelUser.setTypeface(typeface);

            labelPass.setText(getResources().getString(R.string.password));
            labelPass.setTypeface(typeface);

            btnLogin.setText(getResources().getString(R.string.submit_btn));
            btnLogin.setTypeface(typeface);
        }*/

    }

    public void registerAction(View view) {
        Intent intent = new Intent(LoginActivity.this,
                RegisterActivity.class);
        startActivity(intent);
    }

    public void loginAction(View view) {
        String userName = txtUser.getText().toString();
        String password = txtPass.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            txtUser.setError("Username Required");
        } else {
            if (TextUtils.isEmpty(password)) {
                txtPass.setError("Password Required");
            } else {
                session = txtUser.getText().toString();
                UserLoginTask mAuthTask = new UserLoginTask();
                mAuthTask.execute(new String[]{userName, password});
            }
        }
    }


    public class UserLoginTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... parm) {
            try {
                String ip = Config.ipLogin;
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("MOBILE", parm[0]));
                nameValuePairs.add(new BasicNameValuePair("PASSWORD", parm[1]));
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(ip);
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                input = entity.getContent();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
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
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Webservice 2", e.toString());
            }
            if (result.contains("login")) {
                return true;
            }
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                finish();
                Intent intent = new Intent(LoginActivity.this,
                        HomeActivity.class);
                startActivity(intent);
            } else {
                txtPass.setError("Invalid Password");
                txtPass.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}
