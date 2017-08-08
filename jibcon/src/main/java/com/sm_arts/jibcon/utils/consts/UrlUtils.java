package com.sm_arts.jibcon.utils.consts;

import com.sm_arts.jibcon.BuildConfig;
import com.sm_arts.jibcon.data.repository.network.api.UserService;
import com.sm_arts.jibcon.data.repository.network.mobius.MobiusAeService;
import com.sm_arts.jibcon.data.repository.network.mobius.MobiusCiService;
import com.sm_arts.jibcon.data.repository.network.mobius.MobiusCntService;
import com.sm_arts.jibcon.data.repository.network.mobius.MobiusSubService;

import java.util.HashMap;

/**
 * Created by jaeyoung on 7/17/17.
 */

public class UrlUtils {
    private static HashMap<String, String> urls;

    static {
        urls = new HashMap<>();

        String apiUrl = BuildConfig.DEBUG ?
                "http://52.79.142.130/" :
                "http://52.79.142.130/";
        urls.put(UserService.class.getName(), apiUrl);

        String mobiusUrl =
                "http://" + Configs.Mobius.HOST + ":" + Configs.Mobius.PORT + "/";
        urls.put(MobiusCiService.class.getName(), mobiusUrl);
        urls.put(MobiusAeService.class.getName(), mobiusUrl);
        urls.put(MobiusCntService.class.getName(), mobiusUrl);
        urls.put(MobiusSubService.class.getName(), mobiusUrl);
    }

    public static String getUrlWithClassName(String clsName) {
        return urls.get(clsName);
    }
}
