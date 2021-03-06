package com.sm_arts.jibcon.data.repository.helper.network;

import android.util.Log;

import com.sm_arts.jibcon.data.models.api.dto.User;
import com.sm_arts.jibcon.data.repository.network.api.UserService;
import com.sm_arts.jibcon.utils.network.RetrofitClients;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jaeyoung on 2017. 4. 29..
 */

public class UserNetworkImpl implements UserNetwork {
    private final String TAG = "jibcon/"+getClass().getSimpleName();
    private static UserNetwork sInstance;
    private UserService mUserService;


    private UserNetworkImpl() {
        mUserService = RetrofitClients.getInstance().getService(UserService.class);
    }

    public static UserNetwork getInstance() {
        if (sInstance == null) {
            synchronized(UserNetwork.class) {
                if (sInstance == null) {
                    sInstance = new UserNetworkImpl();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void getSampleUserInfoFromServerAsynchronisely(final onSuccessListener listener) {
        Log.d(TAG, "getSampleUserInfoFromServerAsynchronisely: Call.enqueue");

        Call<User> c = mUserService.getSampleUser();
        try {
            c.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User result = response.body();
                    Log.d(TAG, "getSampleUserInfoFromServerAsynchronisely/onResponse: "+ result.toString());
                    listener.onSuccessGetSampleUserInfoFromServerAsynchronisely(result);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.d(TAG, "getSampleUserInfoFromServerAsynchronisely/onFailure: ");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getSampleUserInfoFromServerSynchronisely() {
        Log.d(TAG, "getSampleUserInfoFromServerSynchronisely: Call.execute");

        Call<User> c = mUserService.getSampleUser();
        try {
            User result = c.execute().body();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
