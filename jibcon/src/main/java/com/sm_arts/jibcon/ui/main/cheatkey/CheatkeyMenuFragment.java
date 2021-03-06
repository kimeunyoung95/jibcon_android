package com.sm_arts.jibcon.ui.main.cheatkey;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.ui.main.cheatkey.passive.passivehome.RoutinePassiveFragment;

import butterknife.ButterKnife;

/**
 * Edited by ChanJoo on 2017-04-13.
 */

public class CheatkeyMenuFragment extends Fragment{
    private static final String TAG = "CheatkeyMenuFragment";
    
    // Viewpager
    ViewPager mVp;

//    @BindView(R.id.btn_active) Button btn_active;
//    @BindView(R.id.btn_passive) Button btn_passive;

    Fragment mActiveFragment;
    Fragment mPassiveFragment;
    public CheatkeyMenuFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.maincheatkey_cheatkeymenu_fragment, container, false);
        ButterKnife.bind(this, layout);

        Log.d(TAG, "onCreateView: 1");
        //mActiveFragment = new CheatkeyActiveFragment();
        mPassiveFragment = new RoutinePassiveFragment();

        Log.d(TAG, "onCreateView: 2");
        mVp = (ViewPager) layout.findViewById(R.id.vp_cheatkey);

        Log.d(TAG, "onCreateView: 3");
        /* ↓Acitve & Passive Button↓ */

        mVp.setAdapter(new pagerAdapter(getChildFragmentManager())); // getSupportFragmentManager에서 수정
        mVp.setCurrentItem(0);
//        btn_active.setBackground(getResources().getDrawable(R.drawable.pr_bot_shadow_blue));
//        btn_passive.setBackground(getResources().getDrawable(R.drawable.pr_bot_shadow_gray));

        Log.d(TAG, "onCreateView: 4");
//        btn_active.setOnClickListener(movePageListener);
//        btn_active.setTag(0);
//
//
//        btn_passive.setOnClickListener(movePageListener);
//        btn_passive.setTag(1);
        Log.d(TAG, "onCreateView: 5");
        /* ↑Acitve & Passive Button↑ */

        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled: "+position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: "+position);
//                if(position==0) {
//                    btn_active.setBackground(getResources().getDrawable(R.drawable.pr_bot_shadow_blue));
//                    btn_passive.setBackground(getResources().getDrawable(R.drawable.pr_bot_shadow_gray));
//                } else {
//                    btn_active.setBackground(getResources().getDrawable(R.drawable.pr_bot_shadow_gray));
//                    btn_passive.setBackground(getResources().getDrawable(R.drawable.pr_bot_shadow_blue));
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged: "+state);
            }
        });
        return layout;
    }

    /* ↓뷰 페이저(액티비티 슬라이드)↓ */
    private View.OnClickListener movePageListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            int tag = (int) v.getTag();
            Log.d(TAG, "onClick: asd");
//            if(tag==0) {
//                btn_active.setBackground(getResources().getDrawable(R.drawable.pr_bot_shadow_blue));
//                btn_passive.setBackground(getResources().getDrawable(R.drawable.pr_bot_shadow_gray));
//                Log.d("asd","doingactive");
//            } else  {
//                btn_active.setBackground(getResources().getDrawable(R.drawable.pr_bot_shadow_gray));
//                btn_passive.setBackground(getResources().getDrawable(R.drawable.pr_bot_shadow_blue));
//                Log.d("asd","doingpassive");
//            }

            mVp.setCurrentItem(tag);





        }
    };

    private class pagerAdapter extends FragmentStatePagerAdapter {
        public pagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.w(TAG, "movePageListener");
            Log.d(TAG, "getItem: asdasd");
//            if (position == 0) {
//                Log.d(TAG, "getItem: CheatKeyActiveFragment");
//                return new CheatkeyActiveFragment();
//            } else  {
//                Log.d(TAG, "getItem: CheatKeyActiveFragment");
//                return new RoutinePassiveFragment();
//            }
            return new RoutinePassiveFragment();
        }


        @Override
        public int getCount() {
            return 1;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }
    /* ↑뷰 페이저(액티비티 슬라이드)↑ */
}
