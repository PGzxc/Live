package com.pgzxc.live.ui.activity;




import com.pgzxc.live.R;
import com.pgzxc.live.data.preference.guide.GuidePreference;
import com.pgzxc.live.data.preference.version.VersionPreference;
import com.pgzxc.live.databinding.ActivityWelcomeBinding;
import com.pgzxc.live.ui.base.BaseActivity;
import com.pgzxc.live.utils.ActivityUtils;
import com.pgzxc.live.utils.CutDownUtils;
import org.kymjs.kjframe.utils.SystemTool;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * 欢迎页面或广告页
 */

public class WelcomeActivity extends BaseActivity<ActivityWelcomeBinding> {

    CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initData() {
        super.initData();

        mCompositeDisposable.add(CutDownUtils.countDown(3).doOnSubscribe(disposable -> mDataBinding.tvSkip.setText("跳过 4")).subscribeWith(new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                mDataBinding.tvSkip.setText("跳过 " + (integer + 1));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                toNextPage();
            }
        }));
        /** 如果应用更新版本了，则重新调用向导页面 */
        if (SystemTool.getAppVersionCode(this) > VersionPreference.getVersionCode(this)) {
            VersionPreference.setVersionCode(this, SystemTool.getAppVersionCode(this));
            GuidePreference.setShowGuide(this, true);
        }
    }

    private void toNextPage() {
        if (GuidePreference.isShowGuide(this)) {
            ActivityUtils.skipActivity(this, GuideActivity.class);
        } else {
            ActivityUtils.skipActivity(this, LoginActivity.class);
        }
    }

    @Override
    public void setListener() {
        super.setListener();
        mDataBinding.tvSkip.setOnClickListener(view -> toNextPage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

}
