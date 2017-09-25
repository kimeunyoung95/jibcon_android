package com.sm_arts.jibcon.ui.main.cheatkey;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.main.cheatkey.active.CheatkeyActiveFragment;
import com.sm_arts.jibcon.ui.main.cheatkey.routine.RoutineFragment;
import com.sm_arts.jibcon.ui.main.devicemenu.fragment.DeviceMenuFragment;
import com.sm_arts.jibcon.ui.main.devicemenu.fragment.DeviceRoutineMenuFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Edited by ChanJoo on 2017-04-13.
 */

public class CheatkeyMenuFragment extends Fragment{
    private static final String TAG = "CheatkeyMenuFragment";
    
    // Viewpager
    ViewPager mVp;

    @BindView(R.id.btn_active) Button btn_active;
    @BindView(R.id.btn_passive) Button btn_passive;

    public CheatkeyMenuFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.maincheatkey_cheatkeymenu_fragment, container, false);
        ButterKnife.bind(this, layout);

        mVp = (ViewPager) layout.findViewById(R.id.vp_cheatkey);

        /* ↓Acitve & Passive Button↓ */

        mVp.setAdapter(new pagerAdapter(getChildFragmentManager())); // getSupportFragmentManager에서 수정
        mVp.setCurrentItem(0);

        btn_active.setOnClickListener(movePageListener);
        btn_active.setTag(0);
        btn_passive.setOnClickListener(movePageListener);
        btn_passive.setTag(1);
        /* ↑Acitve & Passive Button↑ */

        return layout;
    }

    /* ↓뷰 페이저(액티비티 슬라이드)↓ */
    View.OnClickListener movePageListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            int tag = (int) v.getTag();
            mVp.setCurrentItem(tag);
        }
    };

    private class pagerAdapter extends FragmentStatePagerAdapter {
        public pagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.w("CJ","movePageListener");
            switch(position) {
                case 0:
                    Log.d(TAG, "getItem: CheatkeyActiveFragment");
                    return new CheatkeyActiveFragment();
                case 1:
                    Log.d(TAG, "getItem: ");
                    return new DeviceRoutineMenuFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
    /* ↑뷰 페이저(액티비티 슬라이드)↑ */
}
