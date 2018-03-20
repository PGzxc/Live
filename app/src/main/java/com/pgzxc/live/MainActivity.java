package com.pgzxc.live;


import android.annotation.SuppressLint;
import android.view.KeyEvent;
import com.pgzxc.live.databinding.ActivityMainBinding;
import com.pgzxc.live.ui.base.BaseActivity;
import com.pgzxc.live.ui.base.SupportFragment;
import com.pgzxc.live.ui.fragment.FollowFragment;
import com.pgzxc.live.ui.fragment.HomeFragment;
import com.pgzxc.live.ui.fragment.MeFragment;
import com.pgzxc.live.ui.fragment.RecentFragment;
import com.pgzxc.live.widget.LiveDialog;
import org.kymjs.kjframe.ui.ViewInject;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;


public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private SupportFragment[] mFragments = new SupportFragment[4];
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    int position;
    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        HomeFragment firstFragment = findFragment(HomeFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = new HomeFragment();
            mFragments[SECOND] = new RecentFragment();
            mFragments[THIRD] = new FollowFragment();
            mFragments[FOURTH] = new MeFragment();
            getSupportDelegate().loadMultipleRootFragment(R.id.fragmentContainer, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用
            mFragments[0] = findFragment(HomeFragment.class);
            mFragments[1] = findFragment(RecentFragment.class);
            mFragments[2] = findFragment(FollowFragment.class);
            mFragments[3] = findFragment(MeFragment.class);
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void setListener() {
        super.setListener();
        mDataBinding.btnLive.setOnClickListener(view -> {
            LiveDialog liveDialog = new LiveDialog(MainActivity.this);
            liveDialog.showDialog();
        });
        mDataBinding.bottomBar.setOnTabSelectListener(tabId -> {
            switch (tabId) {
                case R.id.tab_home:
                    position = FIRST;
                    break;
                case R.id.tab_recent:
                    position = SECOND;
                    break;
                case R.id.tab_follow:
                    position = THIRD;
                    break;
                case R.id.tab_me:
                    position = FOURTH;
                    break;
            }
            getSupportDelegate().showHideFragment(mFragments[position]);
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void exit() {
        if (!isExit) {
            isExit = true;
            ViewInject.toast(getApplicationContext(), "再按一次退出程序");
            Observable.timer(2, TimeUnit.SECONDS).subscribe(aLong -> isExit = false);
        } else {
            finish();
            System.exit(0);
        }
    }

}
