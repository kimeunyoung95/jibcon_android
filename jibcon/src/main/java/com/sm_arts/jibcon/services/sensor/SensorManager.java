package com.sm_arts.jibcon.services.sensor;

import android.text.TextUtils;
import android.util.Log;

import com.sm_arts.jibcon.data.models.api.dto.DeviceItem;
import com.sm_arts.jibcon.data.models.api.dto.Routine;
import com.sm_arts.jibcon.data.models.mobius.MqttSurCon;
import com.sm_arts.jibcon.data.repository.helper.DeviceNetworkHelper;
import com.sm_arts.jibcon.data.repository.helper.RoutineNetworkHelper;
import com.sm_arts.jibcon.services.actuator.ActuatorManager;
import com.sm_arts.jibcon.services.actuator.HueActuator;
import com.sm_arts.jibcon.utils.loginmanager.JibconLoginManager;
import com.sm_arts.jibcon.utils.mqtt.MqttManager;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;

/**
 * Created by jaeyoung on 8/13/17.
 */

public class SensorManager {
    private static final String TAG = "SensorManager";

    private static SensorManager sInstance;
    private final Observable<MqttSurCon> mMqttSurConObservable;
    private CompositeDisposable mDisposables = new CompositeDisposable();

    public static void init() {
        if (JibconLoginManager.getInstance().userSignin()) {
            sInstance = new SensorManager();
        } else {
            Log.e(TAG, "init: must call init() after signin");
        }
    }

    public static SensorManager getInstance() {
        if (sInstance == null) {
            throw new ExceptionInInitializerError("call init() first");
        }

        return sInstance;
    }

    private SensorManager() {
        MqttManager.init();
        mMqttSurConObservable = MqttManager.getInstance().asObservable();

        mDisposables.add(
                mMqttSurConObservable.subscribe(
                        (surCon) -> {
                            String sur = surCon.getSur();
                            if (TextUtils.equals(sur, "/Mobius/ae-smarts/cnt-ultra_res/aei-jibcon_subscription")) {
                                Log.d(TAG, "SensorManager: surCon=" + surCon.toString());
                                float con = Float.parseFloat(surCon.getCon());
                                if (con < 0.5) {
                                    HueActuator.getInstance().turnOn("2", () -> {
                                    });
                                }
                            } else {
                                Log.d(TAG, "SensorManager: surCon=" + surCon.toString());
//                                "temperature is [26C] and humidity is [54%]"
                            }
                        }
                )
        );

        initRoutines();
    }

    private void initRoutines() {
        // TODO: 8/14/17 GET Routines, Add Disposable routine by routine
        Log.d(TAG, "initRoutines: ");
    }
}
