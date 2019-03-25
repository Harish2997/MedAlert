package medical.medicalapps;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import medical.medicalapps.utils.TamilUtil;

public class HomeActivity extends AppCompatActivity {
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("MEDICATION REMINDER");
        toInitialize();

    }

    public void toInitialize() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutText);
        LayoutInflater mInflater = (LayoutInflater)
                getApplicationContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                10
        );


        txtMessageString = getResources().getStringArray(R.array.home);
        for (i = 0; i < txtMessageString.length; i++) {
            final LinearLayout convertView = (LinearLayout) mInflater.inflate(R.layout.home_button, null);
            convertView.setId(1001 + i);
            Log.e("Welcome : ",txtMessageString[i].toString());
            ImageView imgProduct = (ImageView) convertView.findViewById(R.id.imgAction);
            TextView txtTitle = (TextView) convertView.findViewById(R.id.txtAction);
            TextView txtString = (TextView) convertView.findViewById(R.id.txtString);
            txtString.setText("" + i);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LinearLayout convertView = (LinearLayout) view;
                    TextView txtTitle = (TextView) convertView.findViewById(R.id.txtString);
                    String action = txtTitle.getText().toString();

                    if (action.equals("0")) {
                        generalAction(view);
                    }
                    if (action.equals("1")) {
                        remainderAction(view);
                    }
                    if (action.equals("2")) {
                        requestAction(view);
                    }
                    if (action.equals("3")) {
                        requestAction(view);
                    }
                    if (action.equals("4")) {
                        notifyAction(view);
                    }
                    if (action.equals("5")) {
                        //logoutAction(view);
                    }

                }
            });

            imgProduct.setImageResource(imageHome[i]);
            txtTitle.setText(txtMessageString[i]);

            TextView txtSpace = new TextView(this);
            txtSpace.setBackground(new ColorDrawable(getResources().getColor(R.color.white)));
            if (MainActivity.language.equals("ta")) {
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Bamini.ttf");
                txtTitle.setTypeface(typeface);
                txtTitle.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, txtMessageString[i]));
                convertView.setPadding(0, 0, 0, 10);
            }

            linearLayout.addView(convertView, params);
            linearLayout.addView(txtSpace, params1);


        }


    }

    public void generalAction(View view) {
        Intent intent = new Intent(HomeActivity.this, GeneralActivity.class);
        startActivity(intent);
    }

    public void requestAction(View view) {
        Intent intent = new Intent(HomeActivity.this, UpdateActivity.class);
        startActivity(intent);
    }

    public void notifyAction(View view) {
        Intent intent = new Intent(HomeActivity.this, NotificationActivity.class);
        startActivity(intent);
    }

    public void remainderAction(View view) {
        Intent intent = new Intent(HomeActivity.this, AlaramActivity.class);
        startActivity(intent);
    }

    public void logoutAction(View view) {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    String[] txtMessageString = {"GENERAL DRUGS", "ADD PRESCRIPTION", "BLOOD SUGAR(DIABETES)", "BLOOD PRESSURE", "NOTIFICATION", "LOGOUT"};
    int[] imageHome = {R.drawable.img_general, R.drawable.img_precripsion, R.drawable.img_sugar, R.drawable.img_pressure, R.drawable.notify, R.drawable.img_logout};
}

