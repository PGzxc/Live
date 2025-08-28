package com.pgzxc.live;


import android.annotation.SuppressLint;
import android.util.Log;
import android.view.KeyEvent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.pgzxc.live.databinding.ActivityMainBinding;
import com.pgzxc.live.ui.base.BaseActivity;
import com.pgzxc.live.ui.base.BaseFragment;
import com.pgzxc.live.ui.fragment.FollowFragment;
import com.pgzxc.live.ui.fragment.HomeFragment;
import com.pgzxc.live.ui.fragment.MeFragment;
import com.pgzxc.live.ui.fragment.RecentFragment;
import com.pgzxc.live.widget.LiveDialog;
import org.kymjs.kjframe.ui.ViewInject;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;


public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private BaseFragment[] mFragments = new BaseFragment[4];
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    int position;
    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    private FragmentTransaction transaction;
    Fragment mCurrentFragment;

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        super.initWidget();
//        mFragments[0] = new HomeFragment();
//        mFragments[1] = new RecentFragment();
//        mFragments[2] = new FollowFragment();
//        mFragments[3] = new MeFragment();
//
//
//        transaction= getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.fragmentContainer,mFragments[0]);
//        mCurrentFragment=mFragments[0];
//       // transaction.add(R.id.fragmentContainer,mFragments[1]).hide(mFragments[1]);
//        //transaction.add(R.id.fragmentContainer,mFragments[2]).hide(mFragments[2]);
//        //transaction.add(R.id.fragmentContainer,mFragments[3]).hide(mFragments[3]);
//        transaction.commitNow();
//        //transaction.show(mFragments[0]);
//        //transaction.commit();
//        //transaction.show(mFragments[0]);
//        //transaction.commitAllowingStateLoss();

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
//        mDataBinding.bottomBar.setOnTabSelectListener(tabId -> {
//            switch (tabId) {
//                case R.id.tab_home:
//                    position = FIRST;
//                    break;
//                case R.id.tab_recent:
//                    position = SECOND;
//                    break;
//                case R.id.tab_follow:
//                    position = THIRD;
//                    break;
//                case R.id.tab_me:
//                    position = FOURTH;
//                    break;
//            }
//
//            getSupportDelegate().showHideFragment(mFragments[position]);
//        });
        mDataBinding.bottomBar.setOnTabSelectListener(tabId1 -> {
                    if (tabId1 == R.id.tab_home) {
                        position = FIRST;
                    } else if (tabId1 == R.id.tab_recent) {
                        position = SECOND;
                    } else if (tabId1 == R.id.tab_follow) {
                        position = THIRD;
                    } else {
                        position = FOURTH;
                    }
                    getSupportDelegate().showHideFragment(mFragments[position]);
                }
        );
    }
    /**
     * 主activity进行控制不同的fragment
     *
     * @param from
     * @param to
     */
    public void switchFragment(Fragment from, Fragment to) {
        if (mCurrentFragment != to) {
            mCurrentFragment = to;
            if (!to.isAdded()) {//判断是否被添加到了Activity里面去了
                transaction.hide(from).add(R.id.fragmentContainer, to).commitAllowingStateLoss();
            } else {
                transaction.hide(from).show(to).commitAllowingStateLoss();
            }
        }
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
