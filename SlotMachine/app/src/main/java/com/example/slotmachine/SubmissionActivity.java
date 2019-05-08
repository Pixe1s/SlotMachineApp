package com.example.slotmachine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SubmissionActivity extends AppCompatActivity {
    EditText et_initials;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        et_initials = (EditText)findViewById(R.id.et_initials);
        btn_submit = (Button)findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_initials.getText().toString().trim().length() == 3) {
                    SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("INITIALS", et_initials.getText().toString());
                    editor.putInt("LAST_SCORE", Common.score);
                    editor.apply();

                    Common.score = 1000;

                    Intent intent = new Intent(getApplicationContext(), LeaderboardActivity.class);
                    intent.putExtra("UniqueID", "From_Submission");
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SubmissionActivity.this, "Please enter 3 initials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
