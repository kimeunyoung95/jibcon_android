package com.sm_arts.jibcon.ui.additional.floatingbuttonui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sm_arts.jibcon.ui.BaseActivity;
import com.sm_arts.jibcon.ui.adddevice.AddDeviceActivity;
import com.sm_arts.jibcon.R;

public class FloatingButtonDeviceActivity extends BaseActivity {
    boolean mExpanded = false;
    private View mFabItem1;
    private TextView mFabItem2;
    private View mFabItem3;
    ImageButton mFab;

    private float mOffset1;
    private float mOffset2;
    private float mOffset3;

    public void goToAddDevice() {
        Intent intent = new Intent(getApplicationContext(), AddDeviceActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_floatingbuttondeviceactivity_floatingbutton);

        final ViewGroup fabcontainer = (ViewGroup) findViewById(R.id.fab_container_device);

        fabcontainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mFabItem1 = findViewById(R.id.fab_action_1_device);
        mFabItem1.setVisibility(View.INVISIBLE);
        mFabItem2 = (TextView) findViewById(R.id.Txt_floating_device);

        mFabItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddDevice();
            }
        });
        mFab=(ImageButton)findViewById(R.id.fab_device);

        expandFab();
        mFabItem1.setVisibility(View.VISIBLE);
        mFabItem2.setVisibility(View.VISIBLE);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collapseFab();
                mFabItem1.setVisibility(View.INVISIBLE);
                mFabItem2.setVisibility(View.INVISIBLE);
                finish();
            }
        });


        fabcontainer.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                fabcontainer.getViewTreeObserver().removeOnPreDrawListener(this);
                mOffset1 = mFab.getY() - mFabItem1.getY();
                mFabItem1.setTranslationY(mOffset1);
                mOffset2 = mFab.getY() - mFabItem2.getY();
                mFabItem2.setTranslationY(mOffset2);
                return true;
            }
        });
    }

    private void collapseFab() {
        // mFab.setImageResource(R.drawable.animated_minus);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(createCollapseAnimator(mFabItem1, mOffset1),
                createCollapseAnimator(mFabItem2, mOffset2),
                createCollapseAnimator(mFabItem3, mOffset3));
        animatorSet.start();
        animateFab();
    }

    private void expandFab() {
        //.setImageResource(R.drawable.animated_plus);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(createExpandAnimator(mFabItem1, mOffset1),
                createExpandAnimator(mFabItem2, mOffset2),
                createExpandAnimator(mFabItem3, mOffset3));
        animatorSet.start();
        animateFab();
    }

    private static final String TRANSLATION_Y = "translationY";

    private Animator createCollapseAnimator(View view, float offset) {
        return ObjectAnimator.ofFloat(view, TRANSLATION_Y, 0, offset)
                .setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
    }

    private Animator createExpandAnimator(View view, float offset) {
        return ObjectAnimator.ofFloat(view, TRANSLATION_Y, offset, 0)
                .setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
    }

    private void animateFab() {
        Drawable drawable = mFab.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }
}
