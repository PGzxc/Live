package com.pgzxc.live.ui.adapter.gif;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.pgzxc.live.R;
import com.pgzxc.live.data.bean.Gif;
import java.util.List;


/**
 * 礼物
 */
public class GridViewAdapter extends BaseAdapter {
    private List<Gif> mDatas;
    private LayoutInflater inflater;
    private Context mContext;
    /**
     * 页数下标,从0开始(当前是第几页)
     */
    private int curIndex;
    /**
     * 每一页显示的个数
     */
    private int pageSize = 8;

    public GridViewAdapter(Context context, List<Gif> mDatas, int curIndex) {
        inflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
        this.curIndex = curIndex;
        this.mContext = context;
    }

    /**
     * 先判断数据集的大小是否足够显示满本页？mDatas.size() > (curIndex+1)*pageSize,
     * 如果够，则直接返回每一页显示的最大条目个数pageSize,
     * 如果不够，则有几项返回几,(mDatas.size() - curIndex * pageSize);(也就是最后一页的时候就显示剩余item)
     */
    @Override
    public int getCount() {
        return mDatas.size() > (curIndex + 1) * pageSize ? pageSize : (mDatas.size() - curIndex * pageSize);
    }

    @Override
    public Gif getItem(int position) {
        return mDatas.get(position + curIndex * pageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + curIndex * pageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_gift, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvGifCost = convertView.findViewById(R.id.tv_gif_cost);
            viewHolder.imgGifType = convertView.findViewById(R.id.img_type_gif);
            viewHolder.layoutGif = convertView.findViewById(R.id.linear_gif);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        /**
         * 在给View绑定显示的数据时，计算正确的position = position + curIndex * pageSize，
         */
        Gif gif = getItem(position);
        viewHolder.tvGifCost.setText(gif.getGitCost());
        viewHolder.imgGifType.setImageResource(gif.getGiftType());
        if (gif.isSelected()) {//被选中
            viewHolder.layoutGif.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.selector_gif_bg));
        } else {
            viewHolder.layoutGif.setBackgroundDrawable(null);
        }
        return convertView;
    }


    class ViewHolder {
        public LinearLayout layoutGif;
        public TextView tvGifCost;
        public ImageView imgGifType;
    }

}