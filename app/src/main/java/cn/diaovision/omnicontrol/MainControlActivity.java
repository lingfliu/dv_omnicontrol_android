package cn.diaovision.omnicontrol;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.diaovision.omnicontrol.core.model.device.matrix.MediaMatrix;
import cn.diaovision.omnicontrol.model.Config;
import cn.diaovision.omnicontrol.model.ConfigXXX;
import cn.diaovision.omnicontrol.util.CrashHandler;
import cn.diaovision.omnicontrol.util.PortHelper;
import cn.diaovision.omnicontrol.view.AudioFragment;
import cn.diaovision.omnicontrol.view.CameraFragment;
import cn.diaovision.omnicontrol.view.ConferenceFragment;
import cn.diaovision.omnicontrol.view.ConfigFragment;
import cn.diaovision.omnicontrol.view.DvdFragment;
import cn.diaovision.omnicontrol.view.LightFragment;
import cn.diaovision.omnicontrol.view.PowerFragment;
import cn.diaovision.omnicontrol.view.VideoFragment;
import cn.diaovision.omnicontrol.widget.AssistDrawerLayout;
import cn.diaovision.omnicontrol.widget.AudioDrawerLayout;

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
            new ConferenceFragment(),
            new ConfigFragment(),
    };

    //Context
    OmniControlApplication app;
    private int currentIndex=0;

    //Data
    int preTab = 0;

    public static MediaMatrix matrix;
    public static Config cfg;

    @BindView(R.id.navigation_bar0)
    RadioGroup radioGroup;

    @BindView(R.id.image_logo)
    ImageView logoImage;

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

        gestureDetector = new GestureDetectorCompat(this, this);

        CrashHandler.getInstance().init(this);
        initConfig();
        initMediaMatrix();
        PortHelper.getInstance().init();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.navigation_power:
                        switchFragment(0);
                        break;
                    case R.id.navigation_video:
                        switchFragment(1);
                        break;
                    case R.id.navigation_audio:
                        switchFragment(2);
                        break;
                    case R.id.navigation_light:
                        switchFragment(3);
                        break;
                    case R.id.navigation_camera:
                        switchFragment(4);
                        break;
                    case R.id.navigation_dvd:
                        switchFragment(5);
                        break;
                    case R.id.navigation_meeting:
                        switchFragment(6);
                        break;
                    case R.id.navigation_setting:
                        switchFragment(7);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initConfig() {
        cfg=ConfigXXX.fromFile(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Omnicontrol/config/config.xml");
    }

    private void initMediaMatrix() {
        matrix=new MediaMatrix.Builder()
                .id(cfg.getMatrixId())
                .ip(cfg.getMatrixIp())
                .port(cfg.getMatrixUdpIpPort())
                .localPreviewVideo(cfg.getMatrixPreviewIp(), cfg.getMatrixPreviewPort())
                .videoInInit(cfg.getInputPortList())
                .videoOutInit(cfg.getOutputPortList())
                .camerasInit(cfg.getMatrixCameras())
                .build();
        matrix.setVideoChnSet(cfg.getMatrixChannels());
    }

    void switchFragment(int i){
        if (i < 8) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FRAGMENTS[i], TAG_FRAGMENT[i]);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
            currentIndex=i;
        }
    }


    /* ************************************
     * Scroll & keydown event dispatch and handling
     * ************************************/
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //handle at this layer if needed
        if(FRAGMENTS[6]!=null&&currentIndex==6)
            ((ConferenceFragment)FRAGMENTS[6]).getActivityDispatchTouchEvent(ev);
        if(FRAGMENTS[1]!=null&&currentIndex==1)
            ((VideoFragment)FRAGMENTS[1]).getActivityDispatchTouchEvent(ev);
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

    @Override
    public void onBackPressed() {
        if(currentIndex==1){
            AssistDrawerLayout drawerLayout=((VideoFragment)FRAGMENTS[1]).getDrawerLayout();
            if(drawerLayout!=null&&drawerLayout.isDrawerOpen()){
                ((VideoFragment)FRAGMENTS[1]).editComplete();
                return;
            }
        }
        if(currentIndex==2){
            AudioDrawerLayout drawerLayout=((AudioFragment)FRAGMENTS[2]).getDrawerLayout();
            if(drawerLayout!=null&&drawerLayout.isDrawerOpen()){
                ((AudioFragment)FRAGMENTS[2]).editComplete();
                return;
            }
        }
        super.onBackPressed();
    }
}
