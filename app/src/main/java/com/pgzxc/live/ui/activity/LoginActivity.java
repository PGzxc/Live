package com.pgzxc.live.ui.activity;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.pgzxc.live.MainActivity;
import com.pgzxc.live.R;
import com.pgzxc.live.databinding.ActivityLoginBinding;
import com.pgzxc.live.ui.base.BaseActivity;
import com.pgzxc.live.utils.ActivityUtils;
import com.pgzxc.live.utils.EMCUtils;
import com.pgzxc.live.utils.RxPermisionUtils;
import com.pgzxc.live.utils.VerifyUtils;
import org.kymjs.kjframe.ui.ViewInject;

/**
 * 登录
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        if (EMCUtils.isLoggedInBefore()) {
            ActivityUtils.skipActivity(LoginActivity.this, MainActivity.class);
        }
        showSuccess();
        mDataBinding.titleBar.setTitleTextView(getString(R.string.btn_login));
        mDataBinding.titleBar.setRightText(getString(R.string.btn_register));
        RxPermisionUtils.reqPermision(this);
    }

    @Override
    public void setListener() {
        super.setListener();
        mDataBinding.titleBar.setRightTextView(getString(R.string.btn_register), view -> ActivityUtils.startActivity(LoginActivity.this, RegisterActivity.class));
        findViewById(R.id.btn_login).setOnClickListener(view -> {
            if (VerifyUtils.verify(this, mDataBinding.etName, mDataBinding.etPassword)) {
                showLoading();
                login(mDataBinding.etName.getText().toString().trim(), mDataBinding.etPassword.getText().toString().trim());
            }
        });

    }

    private void login(String userName, String passWord) {
        EMClient.getInstance().login(userName, passWord, new EMCallBack() {
            @Override
            public void onSuccess() {
                ActivityUtils.startActivity(LoginActivity.this, MainActivity.class);
                showSuccess();
            }

            @Override
            public void onError(int code, String error) {
                showSuccess();
                runOnUiThread(() -> ViewInject.toast(LoginActivity.this, "登录失败，code==" + code + "error=" + error));
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }
}
