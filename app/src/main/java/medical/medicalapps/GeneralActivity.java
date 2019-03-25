package medical.medicalapps;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import medical.medicalapps.utils.TamilUtil;

public class GeneralActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
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


        // txtMessageString = getResources().getStringArray(R.array.home);
        for (int i = 0; i < txtMessageString.length; i++) {
            final LinearLayout convertView = (LinearLayout) mInflater.inflate(R.layout.home_button, null);
            convertView.setId(1001 + i);
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
                        feverAction(view);
                    }
                    if (action.equals("1")) {
                        headAction(view);
                    }
                    if (action.equals("2")) {
                        coldAction(view);
                    }
                    if (action.equals("3")) {
                        stomachAction(view);
                    }


                }
            });

            imgProduct.setImageResource(imageHome[i]);
            txtTitle.setText(txtMessageString[i]);

            TextView txtSpace = new TextView(this);
            txtSpace.setBackground(new ColorDrawable(getResources().getColor(R.color.white)));
           /* if (MainActivity.language.equals("ta")) {
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Bamini.ttf");
                txtTitle.setTypeface(typeface);
                txtTitle.setText(TamilUtil.convertToTamil(TamilUtil.BAMINI, txtMessageString[i]));
                convertView.setPadding(0, 0, 0, 10);
            }*/

            linearLayout.addView(convertView, params);
            linearLayout.addView(txtSpace, params1);


        }


    }

    public void feverAction(View view) {
        Intent intent = new Intent(GeneralActivity.this, FeverActivity.class);
        startActivity(intent);
    }

    public void headAction(View view) {
        Intent intent = new Intent(GeneralActivity.this, HeadActivity.class);
        startActivity(intent);
    }

    public void coldAction(View view) {
        Intent intent = new Intent(GeneralActivity.this, ColdActivity.class);
        startActivity(intent);
    }

    public void stomachAction(View view) {
        Intent intent = new Intent(GeneralActivity.this, StomachActivity.class);
        startActivity(intent);
    }

    String[] txtMessageString = {"FEVER", "HEADACHE", "COLD", "STOMACH PAIN"};
    int[] imageHome = {R.drawable.img_fever, R.drawable.img_head, R.drawable.img_cold, R.drawable.img_stomach};

}
