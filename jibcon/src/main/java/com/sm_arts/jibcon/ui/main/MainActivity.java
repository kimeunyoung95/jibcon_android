package com.sm_arts.jibcon.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm_arts.jibcon.R;
import com.sm_arts.jibcon.app.BaseActivity;
import com.sm_arts.jibcon.app.GlobalApplication;
import com.sm_arts.jibcon.app.cheatkey.CheatkeyMenuFragment;
import com.sm_arts.jibcon.app.conshop.ConshopFragment;
import com.sm_arts.jibcon.app.datacontrol.DataControlFragment;
import com.sm_arts.jibcon.app.sidebar.AboutJibconActivity;
import com.sm_arts.jibcon.app.sidebar.ConnectedDevicesActivity;
import com.sm_arts.jibcon.app.sidebar.MyJibconActivity;
import com.sm_arts.jibcon.app.sidebar.UserAuthorityActivity;
import com.sm_arts.jibcon.app.sidebar.WidgetActivity;
import com.sm_arts.jibcon.login.loginmanager.JibconLoginManager;
import com.sm_arts.jibcon.ui.dialogs.SidebarDialog;
import com.sm_arts.jibcon.ui.main.fragments.DeviceMenuFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final String TAG = "jibcon/" + getClass().getSimpleName();
    ViewPager mVp;
    GlobalApplication mApp;
    Fragment mDeviceFragment;
    Fragment mCheatkeyFragment;
    Fragment mConshopFragment;
    Fragment mDataControlFragment;
    ImageView mUserProfileImage;

    @BindView(R.id.btn1) ImageButton mDeviceBtn;
    @BindView(R.id.btn2) ImageButton mCheatkeyBtn;
    @BindView(R.id.btn3) ImageButton mConshopBtn;
    @BindView(R.id.btn4) ImageButton mDataControlBtn;

    ImageButton mtoSettingBtn;

    //각 프래그먼트 정보 바뀌면 갱신에서 담아주기
    //속도 너무 느림 ㅠ
    private class pagerAdapter extends FragmentStatePagerAdapter{
        public pagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return mDeviceFragment;
                case 1:
                    return mCheatkeyFragment;
                case 2:
                    return mConshopFragment;
                case 3:
                    return mDataControlFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
    /* ↑뷰 페이저(액티비티 슬라이드)↑ */

    /* ↓뷰 페이저(액티비티 슬라이드)↓ */
    View.OnClickListener movePageListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag();
            setSelectedMainMenuBtn(tag);
            mVp.setCurrentItem(tag);
        }
    };

    private  void initLayout() {
        mDeviceFragment = new DeviceMenuFragment();
        mDataControlFragment = new DataControlFragment();
        mCheatkeyFragment = new CheatkeyMenuFragment();
        mConshopFragment = new ConshopFragment();

        mVp = (ViewPager) findViewById(R.id.vp); // activity_main에서 viewpager 객체 생성

        mVp.setOffscreenPageLimit(3);
        mVp.setAdapter(new pagerAdapter(getSupportFragmentManager()));

        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled: "+position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: "+position);
                setSelectedMainMenuBtn(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged: "+state);
            }
        });

        mVp.setCurrentItem(0); // 첫 뷰페이저로는 기기 목록이 나오도록 설정.

        mDeviceBtn.setTag(0);
        mDeviceBtn.setOnClickListener(movePageListener);
        mCheatkeyBtn.setTag(1);
        mCheatkeyBtn.setOnClickListener(movePageListener);
        mConshopBtn.setTag(2);
        mConshopBtn.setOnClickListener(movePageListener);
        mDataControlBtn.setTag(3);
        mDataControlBtn.setOnClickListener(movePageListener);
    }

    private void setDefaultMainMenuBtn() {
        mDeviceBtn.setImageResource(R.drawable.ic_home_gray_48dp);
        mCheatkeyBtn.setImageResource(R.drawable.ic_link_gray_48dp);
        mConshopBtn.setImageResource(R.drawable.ic_shopping_cart_gray_48dp);
        mDataControlBtn.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
    }

    private void setSelectedMainMenuBtn(int position) {
        switch (position) {
            case 0 :
                setDefaultMainMenuBtn();
                mDeviceBtn.setImageResource(R.drawable.ic_home_blue_48dp);
                break;
            case 1:
                setDefaultMainMenuBtn();
                mCheatkeyBtn.setImageResource(R.drawable.ic_link_blue_48dp);
                break;
            case 2:
                setDefaultMainMenuBtn();
                mConshopBtn.setImageResource(R.drawable.ic_shopping_cart_blue_48dp);
                break;
            case 3:
                setDefaultMainMenuBtn();
                mDataControlBtn.setImageResource(R.drawable.ic_pie_chart_blue_48dp);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_mainactivity_activity);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        //toolbar search tab  : res-> menu
        //toolbar hamburder menu :  style->ToolbarColoredBackArrow

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mApp = (GlobalApplication)getApplicationContext();

        ActionBarDrawerToggle drawerToggle;

        String[] drawer_str = {"about Jibcon", "문의", "알림 설정", "외출", "연결된 디바이스"};// 사이드바 임시 메뉴 껍데기

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //네비게이션 뷰 풀화면
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        DrawerLayout.LayoutParams params =(DrawerLayout.LayoutParams)navigationView.getLayoutParams();

        mtoSettingBtn = (ImageButton) findViewById(R.id.Btn_Setting);

        mtoSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SidebarDialog sidebarDialog = new SidebarDialog(MainActivity.this);
                sidebarDialog.show();

//                Intent intent=new Intent(MainActivity.this,SettingActivity.class);
//                startActivity(intent);
            }
        });

        params.width=dm.widthPixels;
        navigationView.setLayoutParams(params);
        //네비게이션 뷰 풀화면

        View headerView =navigationView.getHeaderView(0);
        TextView userEmail = (TextView) headerView.findViewById(R.id.Txt_Drawer_UserEmail);
        TextView username = (TextView) headerView.findViewById(R.id.Txt_Drawer_Username);
        mUserProfileImage = (ImageView) headerView.findViewById(R.id.ImgView_Drawer_UserProfile);

        Glide.with(getApplicationContext())
                .load(JibconLoginManager.getInstance()
                        .getUserProfileImageUrl())
                .into(mUserProfileImage);

        //Log.d("userProfile",mApp.getUserProfileImageUrl().toString());
        username.setText(JibconLoginManager.getInstance().getUserName());
        userEmail.setText(JibconLoginManager.getInstance().getUserEmail());

        initLayout();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Sidebar_myjibcon) {
            Intent intent= new Intent(getApplicationContext(), MyJibconActivity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.Sidebar_userAuthority) {
            Intent intent= new Intent(getApplicationContext(), UserAuthorityActivity.class);
            startActivity(intent);
        } else if (id == R.id.Sidebar_connectedDevices) {
            Intent intent= new Intent(getApplicationContext(), ConnectedDevicesActivity.class);
            startActivity(intent);
        } else if (id == R.id.Sidebar_widget) {
            Intent intent= new Intent(getApplicationContext(), WidgetActivity.class);
            startActivity(intent);
        } else if (id == R.id.Sidebar_aboutJibcon) {
            Intent intent= new Intent(getApplicationContext(), AboutJibconActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setDefaultImages() {
        mDeviceBtn.setImageResource(R.drawable.ic_home_gray_48dp);
        mCheatkeyBtn.setImageResource(R.drawable.ic_link_gray_48dp);
        mConshopBtn.setImageResource(R.drawable.ic_shopping_cart_gray_48dp);
        mDataControlBtn.setImageResource(R.drawable.ic_pie_chart_gray_48dp);
    }


}