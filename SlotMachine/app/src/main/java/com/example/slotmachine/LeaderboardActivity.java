package com.example.slotmachine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class LeaderboardActivity extends AppCompatActivity {
    int last_score;
    String initials;
    int highscore1, highscore2, highscore3, highscore4, highscore5;
    String initials1, initials2, initials3, initials4, initials5;

    TextView tv_hs1, tv_hs2, tv_hs3, tv_hs4, tv_hs5;

    Intent chkIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        tv_hs1 = (TextView) findViewById(R.id.txt_highscore1);
        tv_hs2 = (TextView) findViewById(R.id.txt_highscore2);
        tv_hs3 = (TextView) findViewById(R.id.txt_highscore3);
        tv_hs4 = (TextView) findViewById(R.id.txt_highscore4);
        tv_hs5 = (TextView) findViewById(R.id.txt_highscore5);

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        last_score = preferences.getInt("LAST_SCORE", 0);
        highscore1 = preferences.getInt("HIGHSCORE1", 0);
        highscore2 = preferences.getInt("HIGHSCORE2", 0);
        highscore3 = preferences.getInt("HIGHSCORE3", 0);
        highscore4 = preferences.getInt("HIGHSCORE4", 0);
        highscore5 = preferences.getInt("HIGHSCORE5", 0);
        initials = preferences.getString("INITIALS", "AAA");
        initials1 = preferences.getString("INITIALS1", "AAA");
        initials2 = preferences.getString("INITIALS2", "AAA");
        initials3 = preferences.getString("INITIALS3", "AAA");
        initials4 = preferences.getString("INITIALS4", "AAA");
        initials5 = preferences.getString("INITIALS5", "AAA");

        chkIntent = this.getIntent();

        if(chkIntent != null) {
            String strData = chkIntent.getExtras().getString("UniqueID");
            if(strData.equals("From_Submission")) {
                if (last_score > highscore5) {
                    highscore5 = last_score;
                    initials5 = initials;
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("INITIALS5", initials5);
                    editor.putInt("HIGHSCORE5", highscore5);
                    editor.apply();
                }
                if (last_score > highscore4) {
                    int tempInt = highscore4;
                    highscore4 = last_score;
                    highscore5 = tempInt;
                    String tempStr = initials4;
                    initials4 = initials;
                    initials5 = tempStr;
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("INITIALS5", initials5);
                    editor.putInt("HIGHSCORE5", highscore5);
                    editor.putString("INITIALS4", initials4);
                    editor.putInt("HIGHSCORE4", highscore4);
                    editor.apply();
                }
                if (last_score > highscore3) {
                    int tempInt = highscore3;
                    highscore3 = last_score;
                    highscore4 = tempInt;
                    String tempStr = initials3;
                    initials3 = initials;
                    initials4 = tempStr;
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("INITIALS4", initials4);
                    editor.putInt("HIGHSCORE4", highscore4);
                    editor.putString("INITIALS3", initials3);
                    editor.putInt("HIGHSCORE3", highscore3);
                    editor.apply();
                }
                if (last_score > highscore2) {
                    int tempInt = highscore2;
                    highscore2 = last_score;
                    highscore3 = tempInt;
                    String tempStr = initials2;
                    initials2 = initials;
                    initials3 = tempStr;
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("INITIALS3", initials3);
                    editor.putInt("HIGHSCORE3", highscore3);
                    editor.putString("INITIALS2", initials2);
                    editor.putInt("HIGHSCORE2", highscore2);
                    editor.apply();
                }
                if (last_score > highscore1) {
                    int tempInt = highscore1;
                    highscore1 = last_score;
                    highscore2 = tempInt;
                    String tempStr = initials1;
                    initials1 = initials;
                    initials2 = tempStr;
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("INITIALS2", initials2);
                    editor.putInt("HIGHSCORE2", highscore2);
                    editor.putString("INITIALS1", initials1);
                    editor.putInt("HIGHSCORE1", highscore1);
                    editor.apply();
                }

                tv_hs1.setText(initials1 + " --- " + highscore1);
                tv_hs2.setText(initials2 + " --- " + highscore2);
                tv_hs3.setText(initials3 + " --- " + highscore3);
                tv_hs4.setText(initials4 + " --- " + highscore4);
                tv_hs5.setText(initials5 + " --- " + highscore5);
            } else if(strData.equals("From_Main")) {
                tv_hs1.setText(initials1 + " --- " + highscore1);
                tv_hs2.setText(initials2 + " --- " + highscore2);
                tv_hs3.setText(initials3 + " --- " + highscore3);
                tv_hs4.setText(initials4 + " --- " + highscore4);
                tv_hs5.setText(initials5 + " --- " + highscore5);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(chkIntent != null) {
            String strData = chkIntent.getExtras().getString("UniqueID");
            if (strData.equals("From_Submission")) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }
}
