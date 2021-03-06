package com.sm_arts.jibcon.ui.additional.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.data.repository.helper.DeviceNetworkHelper;
import com.sm_arts.jibcon.utils.helper.ToastHelper;

/**
 * Created by admin on 2017-05-27.
 */

public class DeviceDialog extends Dialog {

    public DeviceDialog(@NonNull final Context context, DeviceItem deviceItem) {
        super(context);
        setContentView(R.layout.ui_devicedialog_dialog);
        TextView mTextView1 = (TextView) findViewById(R.id.Txt_dialog_device_txt1);
        TextView mTextView2 = (TextView) findViewById(R.id.Txt_dialog_device_txt2);
        TextView mTextView3 = (TextView) findViewById(R.id.Txt_dialog_device_txt3);

        mTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastHelper.toast(context, "상세보기");
            }
        });

        mTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastHelper.toast(context, "루틴에 추가하기");
            }
        });

        mTextView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastHelper.toast(context, "연동 해제하기");
                DeviceNetworkHelper.getInstance().deleteDevice(deviceItem,
                        (Void)->{

                        });

            }
        });
    }
}
