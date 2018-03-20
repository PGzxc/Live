package com.pgzxc.live.ui.adapter.player;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pgzxc.live.R;
import com.pgzxc.live.data.bean.AudienceBean;
import org.kymjs.kjframe.widget.RoundImageView;
import java.util.List;

/**
 * 直播间观众
 */

public class AudienceAdapter extends BaseQuickAdapter<AudienceBean, BaseViewHolder> {
    public AudienceAdapter(int layoutResId, @Nullable List<AudienceBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AudienceBean item) {
        ((RoundImageView) helper.getView(R.id.image_round_avatar)).setImageResource(item.getCover());
    }

}
