package com.example.memorygameactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;

public class AlertWinner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_winner);
    }
    public void animacionInOut(View view){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            int cx = view.getWidth() / 2;
            int cy = view.getHeight() / 2;
            float radius = view.getWidth();
            Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, radius, 0);
            anim.setDuration(1000);
            Animator revealAnim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, radius);
            revealAnim.setDuration(1000);

            AnimatorSet set = new AnimatorSet();
            set.playSequentially(anim, revealAnim);
            set.start();
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                }
            });
        }
    }
}