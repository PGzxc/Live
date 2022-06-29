package com.pgzxc.live.widget.nicedialog;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pgzxc.live.R;
import com.pgzxc.live.api.req.StatusConfig;
import com.pgzxc.live.data.bean.AudienceBean;

import butterknife.BindView;


public class AudienceDialog extends BaseNiceDialog {
    private ViewConvertListener convertListener;

    @BindView(R.id.image_round_audience)
    ImageView imageAudience;

    @BindView(R.id.tv_AudienceName)
    TextView tvAudienceName;

    public static AudienceDialog newInstance(AudienceBean audienceBean) {
        AudienceDialog dialog = new AudienceDialog();
        Bundle args = new Bundle();
        args.putSerializable(StatusConfig.ARG_ANCHOR, audienceBean);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public int intLayoutId() {
        return R.layout.dialog_live_audience;
    }

    @Override
    public void convertView(ViewHolder holder, BaseNiceDialog dialog) {
        if (convertListener != null) {
            convertListener.convertView(holder, dialog);
        }
    }

    public AudienceDialog setConvertListener(ViewConvertListener convertListener) {
        this.convertListener = convertListener;
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            convertListener = savedInstanceState.getParcelable("listener");
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            AudienceBean audienceBean = (AudienceBean) getArguments().getSerializable(StatusConfig.ARG_ANCHOR);
            imageAudience.setImageResource(audienceBean.getCover());
            tvAudienceName.setText(audienceBean.getName());
        }
    }

    /**
     * 保存接口
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("listener", convertListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        convertListener = null;
    }
}
