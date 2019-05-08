package com.example.slotmachine.ImageViewScrolling;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.slotmachine.R;

public class ImageViewScrolling extends FrameLayout {
    private static final int ANIMATION_DUR = 150;
    ImageView current_image;
    ImageView next_image;

    int last_result = 0;
    int old_value = 0;

    IEventEnd eventEnd;

    public void setEventEnd(IEventEnd eventEnd) {
        this.eventEnd = eventEnd;
    }

    public ImageViewScrolling(Context context) {
        super(context);
        init(context);
    }

    public ImageViewScrolling(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.image_view_scrolling, this);
        current_image = (ImageView)getRootView().findViewById(R.id.current_image);
        next_image = (ImageView)getRootView().findViewById(R.id.next_image);

        next_image.setTranslationY(getHeight());
    }

    public void setValueRandom(final int image, final int rotate_count) {
        current_image.animate().translationY(-getHeight()).setDuration(ANIMATION_DUR).start();
        next_image.setTranslationY(next_image.getHeight());
        next_image.animate().translationY(0).setDuration(ANIMATION_DUR).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setImage(current_image, old_value % 6);
                current_image.setTranslationY(0);
                if(old_value != rotate_count) {
                    setValueRandom(image, rotate_count);
                    old_value++;
                } else {
                    last_result = 0;
                    old_value = 0;
                    setImage(next_image, image);
                    eventEnd.eventEnd(image % 6, rotate_count);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void setImage(ImageView image_view, int value) {
        if(value == Util.ANDROID) {
            image_view.setImageResource(R.drawable.android_complete);
        } else if(value == Util.BATTERY_20) {
            image_view.setImageResource(R.drawable.battery20_complete);
        } else if(value == Util.BATTERY_FULL) {
            image_view.setImageResource(R.drawable.batteryfull_complete);
        } else if(value == Util.CLOUD) {
            image_view.setImageResource(R.drawable.cloud_complete);
        } else if(value == Util.CAKE) {
            image_view.setImageResource(R.drawable.cake_complete);
        } else {
            image_view.setImageResource(R.drawable.hot_complete);
        }

        image_view.setTag(value);
        last_result = value;
    }

    public int getValue() {
        return Integer.parseInt(next_image.getTag().toString());
    }
}
