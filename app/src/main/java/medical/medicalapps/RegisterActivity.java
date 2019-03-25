package medical.medicalapps;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import medical.medicalapps.MainActivity;
import medical.medicalapps.R;
import medical.medicalapps.utils.Config;
import medical.medicalapps.utils.TamilUtil;

public class RegisterActivity extends AppCompatActivity {

    String optionRadio = "";
    InputStream input = null;
    String line = null;
    String result = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle(getResources().getString(R.string.str_register_head));
        toInitialize();
    }

    public void toInitialize() {

        TextView labelFirst = (TextView) findViewById(R.id.labelFirst);
        TextView labelLast = (TextView) findViewById(R.id.labelLast);
        TextView labelEmail = (TextView) findViewById(R.id.labelEmail);
        TextView labelPassword = (TextView) findViewById(R.id.labelPassword);
        TextView labelRetype = (TextView) findViewById(R.id.labelRetype);
        TextView labelMobile = (TextView) findViewById(R.id.labelMobile);
        TextView labelAge = (TextView) findViewById(R.id.labelAge);
        TextView labelGender = (TextView) findViewById(R.id.labelGender);
        TextView labelBlood = (TextView) findViewById(R.id.labelBlood);
        TextView labelCity = (TextView) findViewById(R.id.labelCity);
        TextView labelAddress = (TextView) findViewById(R.id.labelAddress);
        TextView labelHead = (TextView) findViewById(R.id.labelHead);
        TextView btnRegister = (TextView) findViewById(R.id.btnRegister);
        TextView labelAlergy = (TextView) findViewById(R.id.labelAlergy);
        TextView labelisAlergy = (TextView) findViewById(R.id.labelisAlergy);
        RadioButton radioYes = (RadioButton) findViewById(R.id.radioYes);
        RadioButton radioNo = (RadioButton) findViewById(R.id.radioNo);


        MultiSelectSpinner multiSelectSpinner = (MultiSelectSpinner) findViewById(R.id.spinnAlergi);
        multiSelectSpinner.setItems(getResources().getStringArray(R.array.alergicmedicine));




        /*TextView btnLogin = (TextView) findViewById(R.id.btnLogin);
        TextView btnSigin = (TextView) findViewById(R.id.btnSignin);*/
        if (MainActivity.language.equals("ta")) {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Bamini.ttf");
            labelFirst.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.str_firstname)));
            labelFirst.setTypeface(typeface);
            labelLast.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.str_lastname)));
            labelLast.setTypeface(typeface);


            labelEmail.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.str_email)));
            labelEmail.setTypeface(typeface);
            labelPassword.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.str_password)));
            labelPassword.setTypeface(typeface);

            labelRetype.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.str_retype)));
            labelRetype.setTypeface(typeface);
            labelMobile.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.str_mobile)));
            labelMobile.setTypeface(typeface);

            labelAge.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.str_age)));
            labelAge.setTypeface(typeface);
            labelGender.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.str_gender)));
            labelGender.setTypeface(typeface);

            labelBlood.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.str_blood)));
            labelBlood.setTypeface(typeface);
            labelCity.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.str_city)));
            labelCity.setTypeface(typeface);

            labelAddress.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.str_address)));
            labelAddress.setTypeface(typeface);

            btnRegister.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.str_submit)));
            btnRegister.setTypeface(typeface);

            TextView txtTitle = new TextView(this);
            txtTitle.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.str_register_head)));
            txtTitle.setTypeface(typeface);

            labelHead.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.str_register_head)));
            labelHead.setTypeface(typeface);


            labelAlergy.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.str_select_allergies)));
            labelAlergy.setTypeface(typeface);
            labelisAlergy.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.str_allergies)));
            labelisAlergy.setTypeface(typeface);

            radioNo.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.str_no)));
            radioNo.setTypeface(typeface);
            radioYes.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, getResources().getString(R.string.str_yes)));
            radioYes.setTypeface(typeface);


        } else {
            TextView txtTitle = new TextView(this);
            txtTitle.setText(getResources().getString(R.string.str_register_head));
            //this.getActionBar().setCustomView(txtTitle);
        }

        txtFirst = (EditText) findViewById(R.id.txtFirst);
        txtLast = (EditText) findViewById(R.id.txtLast);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtRetype = (EditText) findViewById(R.id.txtRetype);
        txtMobile = (EditText) findViewById(R.id.txtMobile);
        txtAge = (EditText) findViewById(R.id.txtAge);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        txtCity = (EditText) findViewById(R.id.txtCity);
        spinnGender = (Spinner) findViewById(R.id.spinnGender);
        spinnBlood = (Spinner) findViewById(R.id.spinnBlood);
        spinnAlergi = (MultiSelectSpinner) findViewById(R.id.spinnAlergi);
        radioAllergies = (RadioGroup) findViewById(R.id.radioAllergies);


        radioAllergies.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioButtonID = radioAllergies.getCheckedRadioButtonId();
                View radioButton = radioAllergies.findViewById(radioButtonID);
                int idx = radioAllergies.indexOfChild(radioButton);
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearAlergy);
                if (idx == 0) {
                    linearLayout.setVisibility(View.VISIBLE);
                    optionRadio = "Yes";
                }
                else {
                    linearLayout.setVisibility(View.GONE);
                    optionRadio = "No";
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strFirst = txtFirst.getText().toString();
                String strLast = txtLast.getText().toString();
                String strEmail = txtEmail.getText().toString();
                String strPassword = txtPassword.getText().toString();
                String strRetype = txtRetype.getText().toString();
                String strMobile = txtMobile.getText().toString();
                String strAge = txtAge.getText().toString();
                String strGender = spinnGender.getSelectedItem().toString();
                String strBlood = spinnBlood.getSelectedItem().toString();
                String strCity = txtCity.getText().toString();
                String strAddress = txtAddress.getText().toString();

                List<String> strAlergy = spinnAlergi.getSelectedStrings();
                if (strPassword.equals(strRetype)) {
                    new RegisterTask().execute(new String[]{
                            strFirst,
                            strLast,
                            strEmail,
                            strPassword,
                            strMobile,
                            strAge,
                            strGender,
                            strBlood,
                            strCity,
                            strAddress,
                            optionRadio,
                            strAlergy.toString()
                    });
                } else {
                    txtRetype.setError("Password Missmatch");
                }
            }
        });


    }

    class RegisterTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... parm) {
            try {
                String ip = Config.ipRegister;
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("firstname", parm[0]));
                nameValuePairs.add(new BasicNameValuePair("lastname", parm[1]));
                nameValuePairs.add(new BasicNameValuePair("emailid", parm[2]));
                nameValuePairs.add(new BasicNameValuePair("password", parm[3]));
                nameValuePairs.add(new BasicNameValuePair("mobile", parm[4]));
                nameValuePairs.add(new BasicNameValuePair("age", parm[5]));
                nameValuePairs.add(new BasicNameValuePair("gender", parm[6]));
                nameValuePairs.add(new BasicNameValuePair("blood", parm[7]));
                nameValuePairs.add(new BasicNameValuePair("city", parm[8]));
                nameValuePairs.add(new BasicNameValuePair("address", parm[9]));
                nameValuePairs.add(new BasicNameValuePair("optionRadio", parm[10]));
                nameValuePairs.add(new BasicNameValuePair("aller_name", parm[11]));


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
                                        "Registration successful!!",
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

    EditText txtFirst, txtLast, txtEmail, txtPassword, txtRetype, txtMobile, txtAge, txtAddress, txtCity;
    Spinner spinnGender, spinnBlood;
    MultiSelectSpinner spinnAlergi;
    RadioGroup radioAllergies;

}
