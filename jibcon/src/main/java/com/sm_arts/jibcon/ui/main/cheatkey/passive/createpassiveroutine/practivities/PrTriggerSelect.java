package com.sm_arts.jibcon.ui.main.cheatkey.passive.createpassiveroutine.practivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.google.firebase.iid.FirebaseInstanceId;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.NotiData;
import com.sm_arts.jibcon.ui.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrTriggerSelect extends BaseActivity {

    String hour="";
    String minute="";
    String token="";
    String triggertype ="";
    String actiontype="";

    String lat="";
    String lon="";

    @OnClick(R.id.btn_pr_trigger_select_popular_time) void gototimefrompop() {
        Intent intent = new Intent(this, PrTriggerTime.class);
        NotiData notidata = new NotiData(hour, minute, token, "time", actiontype, lat, lon);
        intent.putExtra("come", notidata);
        finish();
        startActivity(intent);
    }

    @OnClick(R.id.btn_pr_trigger_select_all_time) void gototimefromall() {
        Intent intent = new Intent(this, PrTriggerTime.class);
        NotiData notidata = new NotiData(hour, minute, token, "time", actiontype, lat, lon);
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

        hour = notidata.hour;
        minute = notidata.minute;
        token = notidata.token;

        triggertype = notidata.triggertype;
        actiontype = notidata.actiontype;

        lat = notidata.lat;
        lon = notidata.lon;
    }
}
