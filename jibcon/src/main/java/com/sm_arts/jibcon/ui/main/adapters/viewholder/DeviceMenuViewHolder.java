package com.sm_arts.jibcon.ui.main.adapters.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.device.DeviceItem;
import com.sm_arts.jibcon.network.MobiusService;
import com.sm_arts.jibcon.ui.dialogs.DeviceDialog;
import com.sm_arts.jibcon.utils.helper.CustomItemClickListener;
import com.sm_arts.jibcon.utils.network.RetrofiClients;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jaeyoung on 7/18/17.
 */

public class DeviceMenuViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "DeviceMenuViewHolder";

    private final ImageView mThreedotIv;
    private final ImageView mDeviceItemIv;

    public DeviceMenuViewHolder(View itemView,
                                CustomItemClickListener mDeviceItemIvClickedListener,
                                CustomItemClickListener mThreedotIvClickedListener) {
        super(itemView);
        Log.d(TAG, "DeviceMenuViewHolder: ");

        mThreedotIv = (ImageView) itemView.findViewById(R.id.ImgView_deviceItem_threedot);
        mThreedotIv.setOnClickListener(
                v ->
                    mThreedotIvClickedListener.onItemClick(v,
                            getAdapterPosition())
        );

        mDeviceItemIv = (ImageView) itemView.findViewById(R.id.deviceItemIv);
        mDeviceItemIv.setOnClickListener(
                v->
                    mDeviceItemIvClickedListener.onItemClick(v,
                            getAdapterPosition())
        );
    }

    public void configureWith(DeviceItem deviceItem) {
        Log.d(TAG, "configureWith() called with: " +
                "deviceItem = [" + deviceItem.toString() + "]");
        String deviceType = deviceItem.getDeviceType();

        if(DeviceType.AIRCONDITIONER.equals(deviceType)) {
            mDeviceItemIv.setImageResource(R.drawable.airconditioner);
        } else if (DeviceType.LIGHTBULB.equals(deviceType)) {
            mDeviceItemIv.setImageResource(R.drawable.lightbulb);
        } else if (DeviceType.FAN.equals(deviceType)) {
            mDeviceItemIv.setImageResource(R.drawable.fan);
        } else if (DeviceType.REFRIGERATOR.equals(deviceType)) {
            mDeviceItemIv.setImageResource(R.drawable.refrigerator);
        }
    }
}