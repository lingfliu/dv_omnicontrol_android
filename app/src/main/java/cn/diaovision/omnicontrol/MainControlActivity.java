package cn.diaovision.omnicontrol;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.diaovision.omnicontrol.view.AudioFragment;
import cn.diaovision.omnicontrol.view.CameraFragment;
import cn.diaovision.omnicontrol.view.ConfigLoginFragment;
import cn.diaovision.omnicontrol.view.DvdFragment;
import cn.diaovision.omnicontrol.view.LightFragment;
import cn.diaovision.omnicontrol.view.MeetingFragment;
import cn.diaovision.omnicontrol.view.PowerFragment;
import cn.diaovision.omnicontrol.view.VideoFragment;
import devlight.io.library.ntb.NavigationTabBar;

public class MainControlActivity extends BaseActivity implements GestureDetector.OnGestureListener{

    private final String[] TAG_FRAGMENT = {
            "frg_video",
            "frg_audio",
            "frg_dvd",
            "frg_camera",
            "frg_power",
            "frg_light",
            "frg_meeting",
            "frg_configlogin",
    };

    private final String[] TAB_FRAGMENT_NAME = {
            "电源",
            "视频",
            "音频",
            "灯光",
            "摄像",
            "DVD",
            "会议",
            "设置"
    };

    private final int[] TAB_ICON = {
            R.drawable.ic_power,
            R.drawable.ic_video,
            R.drawable.ic_audio,
            R.drawable.ic_light,
            R.drawable.ic_camera,
            R.drawable.ic_disc,
            R.drawable.ic_meeting,
            R.drawable.ic_config
    };

    private final Fragment[] FRAGMENTS = {
            new PowerFragment(),
            new VideoFragment(),
            new AudioFragment(),
            new LightFragment(),
            new CameraFragment(),
            new DvdFragment(),
            new MeetingFragment(),
            new ConfigLoginFragment(),
    };

    //Context
    OmniControlApplication app;

    //Data
    int preTab = 0;

    //UIs
    @BindView(R.id.navigation_bar)
    TabLayout navigationBar;

    //Gesture detector
    GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (OmniControlApplication) getApplication();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //set initial fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FRAGMENTS[0], TAG_FRAGMENT[0]);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();

        //init tabs
        for (int m = 0; m < TAB_FRAGMENT_NAME.length; m ++) {
            TabLayout.Tab tab = navigationBar.newTab().setCustomView(R.layout.tab_navi_item);
            View v = tab.getCustomView();

            AppCompatImageView imgView = (AppCompatImageView) v.findViewById(R.id.img);
            imgView.setImageResource(TAB_ICON[m]);
            AppCompatTextView txtView = (AppCompatTextView) v.findViewById(R.id.txt);
            txtView.setText(TAB_FRAGMENT_NAME[m]);

            navigationBar.addTab(tab);


        }
        //set init tab scale
        int pos =  navigationBar.getSelectedTabPosition();
        navigationBar.getTabAt(pos).getCustomView().findViewById(R.id.tab_content).setScaleX(1.2f);
        navigationBar.getTabAt(pos).getCustomView().findViewById(R.id.tab_content).setScaleY(1.2f);

        //navigation tab selection
        navigationBar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                AnimatorSet animeSet = new AnimatorSet();
                ObjectAnimator animeX = ObjectAnimator.ofFloat(tab.getCustomView().findViewById(R.id.tab_content), "scaleX", 1, 1.2f);
                ObjectAnimator animeY = ObjectAnimator.ofFloat(tab.getCustomView().findViewById(R.id.tab_content), "scaleY", 1, 1.2f);
                animeSet.setDuration(120);
                animeSet.playTogether(animeX, animeY);
                animeSet.start();

                switchFragment(tab.getPosition());
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                AnimatorSet animeSet = new AnimatorSet();
                ObjectAnimator animeX = ObjectAnimator.ofFloat(tab.getCustomView().findViewById(R.id.tab_content), "scaleX", 1.2f, 1);
                ObjectAnimator animeY = ObjectAnimator.ofFloat(tab.getCustomView().findViewById(R.id.tab_content), "scaleY", 1.2f, 1);
                animeSet.setDuration(120);
                animeSet.playTogether(animeX, animeY);
                animeSet.start();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        gestureDetector = new GestureDetectorCompat(this, this);
    }

    void switchFragment(int i){
        if (i < 8) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FRAGMENTS[i], TAG_FRAGMENT[i]);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        }
    }


    /* ************************************
     * Scroll & keydown event dispatch and handling
     * ************************************/
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //handle at this layer if needed
        gestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    /* ************************************
     * Gesture detector
     * ************************************/

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    /* ***********************************
     * handle swipe gesture here
     * ***********************************/
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        final float minMove = 80;
        final float minVelocity = 0;
        float beginY = e1.getY();
        float endY = e2.getY();
        float diffY = endY - beginY;
        float beginX = e1.getX();
        float endX = e2.getX();
        float diffX= endX - beginX;

        //detect if is x swipe or y swipe
        if (Math.abs(velocityX) > minVelocity && Math.abs(diffX/diffY) > 10 && Math.abs(diffX) > minMove){
            //x swipe
            Log.i("<UI>", "<UI> x swipe");
        }
        else if (Math.abs(velocityY) > minVelocity && Math.abs(diffY/diffX) > 10 && Math.abs(diffY) > minMove) {
            //y swipe
            Log.i("<UI>", "<UI> y swipe");
        }
        return false;
    }
}