package com.pgzxc.live.widget.gif;

import android.app.Dialog;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;

import com.pgzxc.live.R;
import com.pgzxc.live.data.bean.Gif;
import com.pgzxc.live.eventbus.AnyEvent;
import com.pgzxc.live.eventbus.AnyEventType;
import com.pgzxc.live.ui.adapter.gif.GridViewAdapter;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.List;

/**
 * author：Administrator on 2016/12/19 14:02
 * description:文件说明
 * version:版本
 */
public class FragmentGiftDialog extends DialogFragment {
    private Dialog dialog;
    private ViewPager viewPager;

    LinearLayout linearDots;
    private List<View> mPagerList;
    private LayoutInflater layoutInflater;
    private ArrayList<Gif> gifLists;
    private Button btnSend;

    /*总的页数*/
    private int pageCount;
    /*每一页显示的个数*/
    private int pageSize = 8;
    /*当前显示的是第几页*/
    private int curIndex = 0;
    private GridViewAdapter[] arr = new GridViewAdapter[3];

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.common_gift_dialog_layout, null, false);
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        initDialogStyle(rootView);
        initView(rootView);
        initData();
        return dialog;

    }

    private void initData() {
        Bundle args = getArguments();
        if (args == null)
            return;
        String[] gifCosts = getResources().getStringArray(R.array.gif_costs);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.gif_resourceIds);
        int[] gifTypeIds = new int[typedArray.length()];
        for (int i = 0; i < typedArray.length(); i++) {
            gifTypeIds[i] = typedArray.getResourceId(i, 0);
        }
        String[] gifNames = getResources().getStringArray(R.array.gif_names);

        gifLists = new ArrayList<>();
        for (int i = 0; i < gifCosts.length; i++) {
            Gif gif = new Gif();
            gif.giftName = gifNames[i];
            gif.giftType = gifTypeIds[i];
            gif.gitCost = gifCosts[i];
            gifLists.add(gif);

        }
        //总的页数=总数/每页数量，并取整
        pageCount = (int) Math.ceil(gifLists.size() * 1.0 / pageSize);
        layoutInflater = getActivity().getLayoutInflater();
        mPagerList = new ArrayList<>();
        for (int j = 0; j < pageCount; j++) {
            final GridView gridview = (GridView) layoutInflater.inflate(R.layout.bottom_vp_gridview, viewPager, false);
            final GridViewAdapter gridAdapter = new GridViewAdapter(getActivity(), gifLists, j);
            gridview.setAdapter(gridAdapter);
            arr[j] = gridAdapter;
            //gifLists.get(0).setSelected(true);
            gridview.setOnItemClickListener((parent, view, position, id) -> {
                Toast.makeText(getActivity(), "点击位置position：" + position + "..id:" + id, Toast.LENGTH_SHORT).show();
                for (int i = 0; i < gifLists.size(); i++) {
                    Gif model = gifLists.get(i);
                    if (i == id) {
                        if (model.isSelected()) {
                            model.setSelected(false);
                        } else {
                            model.setSelected(true);
                            EventBus.getDefault().postSticky(new AnyEvent<>(AnyEventType.GIF_SELECT, model));
                        }
                        Log.i("tag", "==点击位置：" + i + "..id:" + id);
                    } else {
                        model.setSelected(false);
                    }
                }
                Log.i("tag", "状态：" + gifLists.toString());
                gridAdapter.notifyDataSetChanged();
            });
            mPagerList.add(gridview);
        }
        viewPager.setAdapter(new ViewPagerAdapter(mPagerList, getActivity()));
        //viewPager.addOnPageChangeListener(new ViewPagerIndicator(getActivity(), linearDots, 3));
        typedArray.recycle();
        setOvalLayout();
    }

    /**
     * 设置圆点
     */
    public void setOvalLayout() {
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        for (int i = 0; i < pageCount; i++) {
            linearDots.addView(mInflater.inflate(R.layout.view_gif_dot, null));
        }
        // 默认显示第一页
        linearDots.getChildAt(0).findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_selected);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                arr[0].notifyDataSetChanged();
                arr[1].notifyDataSetChanged();
                arr[2].notifyDataSetChanged();
                // 取消圆点选中
                linearDots.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_normal);
                // 圆点选中
                linearDots.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_selected);
                curIndex = position;
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEventMainThread(AnyEvent event) {
        switch (event.getEvent()) {
            case AnyEventType.GIF_SELECT:
                btnSend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onGridViewClickListener.click((Gif) event.getData());
                    }
                });
                break;
        }
    }

    private void initView(View rootView) {
        viewPager = rootView.findViewById(R.id.view_pager);
        linearDots = rootView.findViewById(R.id.linear_dots);
        btnSend = rootView.findViewById(R.id.btn_gif_send);
    }

    public static final FragmentGiftDialog newInstance() {
        FragmentGiftDialog fragment = new FragmentGiftDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    public OnGridViewClickListener onGridViewClickListener;

    public FragmentGiftDialog setOnGridViewClickListener(OnGridViewClickListener onGridViewClickListener) {
        this.onGridViewClickListener = onGridViewClickListener;
        return this;
    }

    public interface OnGridViewClickListener {
        void click(Gif gift);
    }

    private void initDialogStyle(View view) {
        dialog = new Dialog(getActivity(), R.style.CustomGiftDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
