package com.launch.aliapplication;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.toolbar1)
    View mToolbar1;
    @BindView(R.id.toolbar2)
    View mToolbar2;
    @BindView(R.id.ivImg1)
    ImageView ivImg1;
    @BindView(R.id.tvBtn1)
    TextView tvBtn1;
    @BindView(R.id.llBtn1)
    LinearLayout llBtn1;
    @BindView(R.id.ivImg2)
    ImageView ivImg2;
    @BindView(R.id.tvBtn2)
    TextView tvBtn2;
    @BindView(R.id.llBtn2)
    LinearLayout llBtn2;
    @BindView(R.id.ivReceipt)
    ImageView ivReceipt;
    @BindView(R.id.ivScan)
    ImageView ivScan;
    @BindView(R.id.ivInfo2)
    ImageView ivInfo2;
    @BindView(R.id.ivTitle)
    TextView ivTitle;
    @BindView(R.id.ivInfo)
    ImageView ivInfo;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.llMain)
    LinearLayout llMain;

    Unbinder unbinder;


    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_main, container, false);

        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView(){
        activity = this.getActivity();

        tvBtn1.setText("Fun1");
        tvBtn2.setText("Fun2");

        llBtn1.setOnClickListener(this);
        ivReceipt.setOnClickListener(this);
        llBtn2.setOnClickListener(this);
        ivScan.setOnClickListener(this);
        ivInfo.setOnClickListener(this);
        ivInfo2.setOnClickListener(this);
//        llMain.setOnClickListener(this);

        //通过当前滑动的状态 隐藏/显示toolbar
        appBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (verticalOffset == 0) {
                //张开
                mToolbar1.setVisibility(View.VISIBLE);
                mToolbar2.setVisibility(View.GONE);
                setToolbar1Alpha(255);
            } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                //收缩
                mToolbar1.setVisibility(View.GONE);
                mToolbar2.setVisibility(View.VISIBLE);
                setToolbar2Alpha(255);
            } else {
                int alpha = 255 - Math.abs(verticalOffset);
                if (alpha < 0) {
                    //收缩toolbar
                    mToolbar1.setVisibility(View.GONE);
                    mToolbar2.setVisibility(View.VISIBLE);
                    setToolbar2Alpha(Math.abs(verticalOffset));
                } else {
                    //张开toolbar
                    mToolbar1.setVisibility(View.VISIBLE);
                    mToolbar2.setVisibility(View.GONE);
                    setToolbar1Alpha(alpha);
                }
            }
        });
    }

    //设置展开时各控件的透明度
    public void setToolbar1Alpha(int alpha) {
        ivTitle.setAlpha(alpha);
    }

    //设置闭合时各控件的透明度
    public void setToolbar2Alpha(int alpha) {
        ivScan.getDrawable().setAlpha(alpha);
        ivReceipt.getDrawable().setAlpha(alpha);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBtn1:
            case R.id.ivReceipt:            //功能1
                Toast.makeText(activity, "Btn1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.llBtn2:
            case R.id.ivScan:               //功能2
                Toast.makeText(activity, "Btn2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ivInfo:               //消息 （暂未开通）
            case R.id.ivInfo2:
                Toast.makeText(activity, "Info", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.llMain:
//                Toast.makeText(activity, "部分滑动", Toast.LENGTH_SHORT).show();
//                break;

        }
    }
}
