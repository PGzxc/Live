package com.pgzxc.live.ui.activity;


import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.pgzxc.live.R;
import com.pgzxc.live.databinding.ActivityRegisterBinding;
import com.pgzxc.live.ui.base.BaseActivity;
import com.pgzxc.live.utils.ActivityUtils;
import com.pgzxc.live.utils.HandleError;
import com.pgzxc.live.utils.VerifyUtils;

import org.kymjs.kjframe.ui.ViewInject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * 注册页面
 */

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> {

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        showSuccess();
        mDataBinding.titleBar.setTitleTextView(getString(R.string.btn_register));
        mDataBinding.titleBar.setRightText(getString(R.string.btn_login));
    }

    @Override
    public void setListener() {
        super.setListener();
        mDataBinding.titleBar.setRightTextView(getString(R.string.btn_login), view -> ActivityUtils.startActivity(RegisterActivity.this, LoginActivity.class));
        findViewById(R.id.btn_register).setOnClickListener(view -> {
            if (VerifyUtils.verify(RegisterActivity.this, mDataBinding.etName, mDataBinding.etPassword)) {
                showLoading();
                register(mDataBinding.etName.getText().toString().trim(), mDataBinding.etPassword.getText().toString().trim());
            }
        });
    }

    private void register(String userName, String passWord) {
        Observable
                .just("注册")
                .observeOn(Schedulers.newThread())
                .subscribe(s -> {
                    try {
                        EMClient
                                .getInstance()
                                .createAccount(userName, passWord);
                        ActivityUtils.startActivity(RegisterActivity.this, LoginActivity.class);
                        runOnUiThread(() -> ViewInject.toast(RegisterActivity.this, getString(R.string.register_success)));
                        showSuccess();
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                        HandleError handleError = new HandleError(RegisterActivity.this);
                        handleError.handleError(e);
                        showSuccess();
                    }

                });

    }
//
//    @Override
//    public void onBackPressedSupport() {
//        super.onBackPressedSupport();
//        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
//    }
}
