package com.sm_arts.jibcon.ui.splash.getothercon;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sm_arts.jibcon.ui.main.MainActivity;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.BaseActivity;

/**
 * Created by user on 2017-05-19.
 */

public class GetOtherConEndActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashgetothercon_getotherconend_activity);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(GetOtherConEndActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);
    }

}
