package com.sm_arts.jibcon.ui.main.cheatkey.passive.createpassiveroutine.practivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.NotiData;
import com.sm_arts.jibcon.ui.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrTriggerSelect extends BaseActivity {

    String time="default";
    String token="default";
    String message="default";


    @OnClick(R.id.pr_trigger_select_weather) void godate() {
        Intent intent = new Intent(this, PrTriggerGeneralWeather.class);
        NotiData notidata = new NotiData(time,token,message);
        intent.putExtra("come", notidata);
        finish();
        startActivity(intent);
    }

    @OnClick(R.id.pr_trigger_select_dateandtime) void goweather() {
        Intent intent = new Intent(this, PrTriggerGeneralDate.class);
        NotiData notidata = new NotiData(time,token,message);
        intent.putExtra("come", notidata);
        finish();
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pr_trigger_select);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        NotiData notidata = (NotiData)intent.getSerializableExtra("come");

        Log.d("findme",""+notidata.time);
        Log.d("findme",""+notidata.token);
        Log.d("findme",""+notidata.message);

        time = notidata.time;
        token = notidata.token;
        message = notidata.message;
    }
}
