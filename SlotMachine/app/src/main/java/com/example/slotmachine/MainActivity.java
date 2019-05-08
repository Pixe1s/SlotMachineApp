package com.example.slotmachine;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slotmachine.ImageViewScrolling.IEventEnd;
import com.example.slotmachine.ImageViewScrolling.ImageViewScrolling;
import com.example.slotmachine.ImageViewScrolling.Util;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements IEventEnd {
    ImageView btn_up, btn_down;
    ImageViewScrolling image1, image2, image3;
    TextView txt_score;
    Button btn_cashout, btn_leaderboard;

    int count_done = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_down = (ImageView)findViewById(R.id.btn_down);
        btn_up = (ImageView)findViewById(R.id.btn_up);

        image1 = (ImageViewScrolling)findViewById(R.id.image1);
        image2 = (ImageViewScrolling)findViewById(R.id.image2);
        image3 = (ImageViewScrolling)findViewById(R.id.image3);

        txt_score = (TextView)findViewById(R.id.txt_score);

        image1.setEventEnd(MainActivity.this);
        image2.setEventEnd(MainActivity.this);
        image3.setEventEnd(MainActivity.this);

        btn_cashout = (Button)findViewById(R.id.btn_cashout);
        btn_leaderboard = (Button)findViewById(R.id.btn_leaderboard);

        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Common.score >= 100) {
                    btn_up.setVisibility(View.GONE);
                    btn_down.setVisibility(View.VISIBLE);

                    image1.setValueRandom(new Random().nextInt(6), new Random().nextInt((15 - 5) + 1) + 5);
                    image2.setValueRandom(new Random().nextInt(6), new Random().nextInt((15 - 5) + 1) + 5);
                    image3.setValueRandom(new Random().nextInt(6), new Random().nextInt((15 - 5) + 1) + 5);

                    Common.score -= 100;
                    txt_score.setText(String.valueOf(Common.score));
                } else {
                    Toast.makeText(MainActivity.this, "Insufficient Funds", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_cashout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubmissionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LeaderboardActivity.class);
                intent.putExtra("UniqueID", "From_Main");
                startActivity(intent);
            }
        });
    }

    @Override
    public void eventEnd(int result, int count) {
        if(count_done < 2) {
            count_done++;
        } else {
            btn_down.setVisibility(View.GONE);
            btn_up.setVisibility(View.VISIBLE);

            count_done = 0;

            if(image1.getValue() == Util.CAKE && image2.getValue() == Util.CAKE && image3.getValue() == Util.CAKE) {
                Toast toast = Toast.makeText(MainActivity.this, "You win very small prize", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                Common.score += 100;
                txt_score.setText(String.valueOf(Common.score));
            } else if((image1.getValue() == Util.CAKE && image2.getValue() == Util.CAKE && image3.getValue() != Util.CAKE) || (image2.getValue() == Util.CAKE && image3.getValue() == Util.CAKE && image1.getValue() != Util.CAKE)) {
                Toast toast = Toast.makeText(MainActivity.this, "You win very small prize", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                Common.score += 50;
                txt_score.setText(String.valueOf(Common.score));
            } else if(image1.getValue() == Util.CAKE && image3.getValue() == Util.CAKE && image2.getValue() != Util.CAKE) {
                Toast toast = Toast.makeText(MainActivity.this, "You win, but can you call it a prize", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                Common.score += 25;
                txt_score.setText(String.valueOf(Common.score));
            }

            if(image1.getValue() == Util.BATTERY_20 && image2.getValue() == Util.BATTERY_20 && image3.getValue() == Util.BATTERY_20) {
                Toast toast = Toast.makeText(MainActivity.this, "You win small prize", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                Common.score += 200;
                txt_score.setText(String.valueOf(Common.score));
            } else if((image1.getValue() == Util.BATTERY_20 && image2.getValue() == Util.BATTERY_20 && image3.getValue() != Util.BATTERY_20) || (image2.getValue() == Util.BATTERY_20 && image3.getValue() == Util.BATTERY_20 && image1.getValue() != Util.BATTERY_20)) {
                Toast toast = Toast.makeText(MainActivity.this, "You win very small prize", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                Common.score += 100;
                txt_score.setText(String.valueOf(Common.score));
            } else if(image1.getValue() == Util.BATTERY_20 && image3.getValue() == Util.BATTERY_20 && image2.getValue() != Util.BATTERY_20) {
                Toast toast = Toast.makeText(MainActivity.this, "You win very small prize", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                Common.score += 50;
                txt_score.setText(String.valueOf(Common.score));
            }

            if(image1.getValue() == Util.CLOUD && image2.getValue() == Util.CLOUD && image3.getValue() == Util.CLOUD) {
                Toast toast = Toast.makeText(MainActivity.this, "You win medium prize", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                Common.score += 350;
                txt_score.setText(String.valueOf(Common.score));
            } else if((image1.getValue() == Util.CLOUD && image2.getValue() == Util.CLOUD && image3.getValue() != Util.CLOUD) || (image2.getValue() == Util.CLOUD && image3.getValue() == Util.CLOUD && image1.getValue() != Util.CLOUD)) {
                Toast toast = Toast.makeText(MainActivity.this, "You win small prize", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                Common.score += 150;
                txt_score.setText(String.valueOf(Common.score));
            } else if(image1.getValue() == Util.CLOUD && image3.getValue() == Util.CLOUD && image2.getValue() != Util.CLOUD) {
                Toast toast = Toast.makeText(MainActivity.this, "You win very small prize", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                Common.score += 75;
                txt_score.setText(String.valueOf(Common.score));
            }

            if(image1.getValue() == Util.BATTERY_FULL && image2.getValue() == Util.BATTERY_FULL && image3.getValue() == Util.BATTERY_FULL) {
                Toast toast = Toast.makeText(MainActivity.this, "You win big prize", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                Common.score += 650;
                txt_score.setText(String.valueOf(Common.score));
            } else if((image1.getValue() == Util.BATTERY_FULL && image2.getValue() == Util.BATTERY_FULL && image3.getValue() != Util.BATTERY_FULL) || (image2.getValue() == Util.BATTERY_FULL && image3.getValue() == Util.BATTERY_FULL && image1.getValue() != Util.BATTERY_FULL)) {
                Toast toast = Toast.makeText(MainActivity.this, "You win small prize", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                Common.score += 200;
                txt_score.setText(String.valueOf(Common.score));
            } else if(image1.getValue() == Util.BATTERY_FULL && image3.getValue() == Util.BATTERY_FULL && image2.getValue() != Util.BATTERY_FULL) {
                Toast toast = Toast.makeText(MainActivity.this, "You win very small prize", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                Common.score += 100;
                txt_score.setText(String.valueOf(Common.score));
            }

            if(image1.getValue() == Util.HOT && image2.getValue() == Util.HOT && image3.getValue() == Util.HOT) {
                Toast toast = Toast.makeText(MainActivity.this, "You win big prize", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                Common.score += 900;
                txt_score.setText(String.valueOf(Common.score));
            } else if((image1.getValue() == Util.HOT && image2.getValue() == Util.HOT && image3.getValue() != Util.HOT) || (image2.getValue() == Util.HOT && image3.getValue() == Util.HOT && image1.getValue() != Util.HOT)) {
                Toast toast = Toast.makeText(MainActivity.this, "You win medium prize", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                Common.score += 300;
                txt_score.setText(String.valueOf(Common.score));
            } else if(image1.getValue() == Util.HOT && image3.getValue() == Util.HOT && image2.getValue() != Util.HOT) {
                Toast toast = Toast.makeText(MainActivity.this, "You win small prize", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                Common.score += 150;
                txt_score.setText(String.valueOf(Common.score));
            }

            if(image1.getValue() == Util.ANDROID && image2.getValue() == Util.ANDROID && image3.getValue() == Util.ANDROID) {
                Toast toast = Toast.makeText(MainActivity.this, "JACKPOT", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                if(Common.score < 500) {
                    Common.score += 1000;
                } else {
                    Common.score = Common.score * 2;
                }
                txt_score.setText(String.valueOf(Common.score));
            } else if((image1.getValue() == Util.ANDROID && image2.getValue() == Util.ANDROID && image3.getValue() != Util.ANDROID) || (image2.getValue() == Util.ANDROID && image3.getValue() == Util.ANDROID && image1.getValue() != Util.ANDROID)) {
                Toast toast = Toast.makeText(MainActivity.this, "You medium prize", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                Common.score += 500;
                txt_score.setText(String.valueOf(Common.score));
            } else if(image1.getValue() == Util.ANDROID && image3.getValue() == Util.ANDROID && image2.getValue() != Util.ANDROID) {
                Toast toast = Toast.makeText(MainActivity.this, "You win small prize", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                Common.score += 250;
                txt_score.setText(String.valueOf(Common.score));
            }

            if(image1.getValue() != image2.getValue() && image2.getValue() != image3.getValue() && image1.getValue() != image3.getValue()) {
                Toast toast = Toast.makeText(MainActivity.this, "You lose", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        }
    }
}
