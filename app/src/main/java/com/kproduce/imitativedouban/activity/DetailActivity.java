package com.kproduce.imitativedouban.activity;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kproduce.imitativedouban.BaseActivity;
import com.kproduce.imitativedouban.R;
import com.kproduce.imitativedouban.utils.DensityUtil;
import com.kproduce.imitativedouban.utils.ScreenUtil;
import com.kproduce.imitativedouban.view.VerticalDrawerLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by KG on 2019/9/2.
 */
public class DetailActivity extends BaseActivity {

    @BindView(R.id.tv_bar_name)
    TextView tvBarName;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    @BindView(R.id.iv_cover)
    ImageView ivCover;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.rv_leader)
    RecyclerView rvLeader;
    @BindView(R.id.tv_member_count)
    TextView tvMemberCount;
    @BindView(R.id.iv_member_angle)
    ImageView ivMemberAngle;
    @BindView(R.id.sv_content)
    NestedScrollView svContent;
    @BindView(R.id.vd)
    VerticalDrawerLayout vDrawer;
    @BindView(R.id.tv_join_handle)
    TextView tvJoinHandle;
    @BindView(R.id.empty_handle)
    FrameLayout emptyHandle;
    @BindView(R.id.rl_title_second)
    RelativeLayout rlTitleSecond;
    @BindView(R.id.tv_join_title_second)
    TextView tvJoinTitleSecond;

    private long mId;
    private int mScreenHeight;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        int color = CSDNUtils.getColorFromAttr(this, R.attr.windowBackground);
//        StatusBarUtils.setStatusBarBack(this, color, CSDNApp.isDayMode);

        getInfo();
        init();
        initListener();
    }

    private void getInfo() {
        Bundle bundle = getIntent().getExtras();
//        if (bundle != null && bundle.containsKey(MarkUtils.ID)) {
//            mId = bundle.getLong(MarkUtils.ID, 0);
//        }
    }

    private void init() {
        tvBarName.setText(tvName.getText());
        // 获取内部View高度
//        rootView.post(new Runnable() {
//            @Override
//            public void run() {
//                mScreenHeight = rootView.getMeasuredHeight();
//            }
//        });
        // 组长、管理员
        GridLayoutManager glManager = new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false);
        glManager.setSmoothScrollbarEnabled(true);
        rvLeader.setLayoutManager(glManager);
//        rvLeader.setAdapter(mCourseTypeAdapter);
        rvLeader.setNestedScrollingEnabled(false);
        rvLeader.setFocusable(false);

        llContainer.post(() -> {
            int currentHeight = llContainer.getHeight();
            int minHeight = ScreenUtil.getScreenHeight(DetailActivity.this) - DensityUtil.dip2px(this, 116);
            if (currentHeight < minHeight) {
                ViewGroup.LayoutParams layoutParams = llContainer.getLayoutParams();
                layoutParams.height = minHeight;
            }
        });
    }

    private void initListener() {
        vDrawer.setOnStatusChangeListener(new VerticalDrawerLayout.OnStatusChangeListener() {
            private boolean isNameShownFirstTime = false;

            @Override
            public void onOpen() {
                isNameShownFirstTime = tvBarName.getVisibility() == View.VISIBLE;
                tvBarName.setVisibility(View.VISIBLE);
            }

            @Override
            public void onClose() {
                tvBarName.setVisibility(isNameShownFirstTime ? View.VISIBLE : View.GONE);
            }
        });
        svContent.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                float bottomBlinRootY = flBottomBlinRoot.getY();
                // 抽屉的显示和隐藏  * 132 = 48标题高度 + 84底部拖拽栏高度
//                if (scrollY >= (bottomBlinRootY - mScreenHeight + DensityUtil.dip2px(132))) {
//                    if (vDrawer.isDrawerShow()) {
//                        vDrawer.hideDrawer();
//                    }
//                } else {
//                    if (!vDrawer.isDrawerShow()) {
//                        vDrawer.showDrawer();
//                    }
//                }
                // 标题的显示隐藏
                if (scrollY >= tvName.getY() + tvName.getHeight()) {
                    if (tvBarName.getVisibility() == View.GONE) {
                        tvBarName.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (tvBarName.getVisibility() == View.VISIBLE) {
                        tvBarName.setVisibility(View.GONE);
                    }
                }
//                // 顶部大本营动态栏的显示和隐藏
//                if (scrollY >= bottomBlinRootY + DensityUtil.dip2px(DetailActivity.this, 16)) {
//                    if (rlTitleSecond.getVisibility() == View.GONE) {
//                        rlTitleSecond.setVisibility(View.VISIBLE);
//                    }
//                } else {
//                    if (rlTitleSecond.getVisibility() == View.VISIBLE) {
//                        rlTitleSecond.setVisibility(View.GONE);
//                    }
//                }
            }
        });
    }


    @OnClick(R.id.rl_back)
    public void backOnClick(View view) {
        finish();
    }

    @OnClick(R.id.ll_member)
    public void goMemberList(View view) {
    }


}
