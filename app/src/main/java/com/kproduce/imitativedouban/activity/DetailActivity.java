package com.kproduce.imitativedouban.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.kproduce.imitativedouban.R;
import com.kproduce.imitativedouban.bean.Movie;
import com.kproduce.imitativedouban.utils.DensityUtil;
import com.kproduce.imitativedouban.utils.ScreenUtil;
import com.kproduce.imitativedouban.view.VerticalDrawerLayout;
import com.kproduce.roundcorners.RoundImageView;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author KG
 * @date 2019/9/2
 */
public class DetailActivity extends BaseActivity {
    /**
     * 内容
     */
    @BindView(R.id.view_content_status)
    View viewContentStatus;
    @BindView(R.id.view_background)
    View viewBackground;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    @BindView(R.id.iv_cover)
    RoundImageView ivCover;
    /**
     * 标题栏
     */
    @BindView(R.id.view_title)
    ViewGroup viewTitle;
    @BindView(R.id.view_title_status)
    View viewTitleStatus;
    @BindView(R.id.view_title_back)
    View viewTitleBackground;
    @BindView(R.id.rl_title_background)
    RelativeLayout rlTitleBackground;
    @BindView(R.id.iv_title_cover)
    RoundImageView ivTitleCover;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_title_cover)
    LinearLayout llTitleCover;

    @BindView(R.id.sv_content)
    NestedScrollView svContent;
    @BindView(R.id.vd)
    VerticalDrawerLayout vDrawer;

    private Movie mData;
    private int mTransValue;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusBar();

        getInfo();
        init();
        initListener();
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int vis = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            window.getDecorView().setSystemUiVisibility(vis);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

            int statusBarHeight = ScreenUtil.getStatusBarHeight(this);
            ViewGroup.LayoutParams titleParmas = viewTitleStatus.getLayoutParams();
            titleParmas.height = statusBarHeight;

            ViewGroup.LayoutParams contentParmas = viewContentStatus.getLayoutParams();
            contentParmas.height = statusBarHeight;

            ViewGroup.LayoutParams layoutParams = viewTitle.getLayoutParams();
            layoutParams.height = statusBarHeight + DensityUtil.dip2px(this, 49);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void getInfo() {
        mData = (Movie) getIntent().getSerializableExtra("data");
        if (mData == null) {
            finish();
        }
    }

    private void init() {
        mTransValue = DensityUtil.dip2px(this, 90);
        setBackgroundColor();
        rlTitleBackground.setAlpha(0f);
        tvTitleName.setText(mData.getTitle());
        ivTitleCover.setImageResource(mData.getCoverId());

    }

    private void initListener() {
        vDrawer.setOnStatusChangeListener(new VerticalDrawerLayout.OnStatusChangeListener() {
            @Override
            public void onOpen() {
            }

            @Override
            public void onClose() {
            }
        });
        svContent.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // 标题的透明度控制
                if (scrollY >= mTransValue) {
                    if (rlTitleBackground.getAlpha() != 1) {
                        rlTitleBackground.setAlpha(1f);
                        showTitleCover();
                    }
                } else {
                    float alpha = BigDecimal.valueOf(scrollY).divide(BigDecimal.valueOf(mTransValue), 5, BigDecimal.ROUND_HALF_UP).floatValue();
                    rlTitleBackground.setAlpha(alpha);
                    dismissTitileCover();
                }
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

    private void setBackgroundColor() {
        Glide.with(this)
                .applyDefaultRequestOptions(new RequestOptions()
                        .dontAnimate()
                        .dontTransform()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .placeholder(ivCover.getDrawable() == null ? new ColorDrawable(Color.WHITE) : ivCover.getDrawable()))
                .asBitmap()
                .addListener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                                @Override
                                public void onGenerated(Palette palette) {
                                    int darkMutedColor = palette.getDarkMutedColor(palette.getDarkMutedColor(Color.WHITE));

                                    viewTitleBackground.setBackgroundColor(darkMutedColor);
                                    viewBackground.setBackgroundColor(darkMutedColor);
                                }
                            });
                        }
                        return false;
                    }
                }).load(mData.getCoverId()).into(ivCover);
    }

    private void showTitleCover() {
        if (llTitleCover.getVisibility() == View.VISIBLE) {
            return;
        }
        // 标题电影封面动画
        llTitleCover.setVisibility(View.VISIBLE);
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 1, 0, 1, 0.7f, 1, 0f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(250);
        animationSet.setInterpolator(new DecelerateInterpolator());

        llTitleCover.startAnimation(animationSet);

        // 标题文字动画
        TranslateAnimation titleTranslateAnimation = new TranslateAnimation(0, 0, 1, 0, 1, 0, 1, -0.5f);
        AlphaAnimation titleAlphaAnimation = new AlphaAnimation(1f, 0f);

        AnimationSet titleAnimationSet = new AnimationSet(true);
        titleAnimationSet.addAnimation(titleTranslateAnimation);
        titleAnimationSet.addAnimation(titleAlphaAnimation);
        titleAnimationSet.setDuration(250);
        titleAnimationSet.setInterpolator(new DecelerateInterpolator());
        titleAnimationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tvTitle.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        tvTitle.startAnimation(titleAnimationSet);
    }

    private void dismissTitileCover() {
        if (tvTitle.getVisibility() == View.VISIBLE) {
            return;
        }
        // 标题电影封面动画
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 1, 0, 1, 0f, 1, 0.7f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(250);
        animationSet.setInterpolator(new DecelerateInterpolator());
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llTitleCover.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        llTitleCover.startAnimation(animationSet);

        // 标题文字动画
        tvTitle.setVisibility(View.VISIBLE);
        TranslateAnimation titleTranslateAnimation = new TranslateAnimation(0, 0, 1, 0, 1, -0.5f, 1, 0);
        AlphaAnimation titleAlphaAnimation = new AlphaAnimation(0f, 1f);

        AnimationSet titleAnimationSet = new AnimationSet(true);
        titleAnimationSet.addAnimation(titleTranslateAnimation);
        titleAnimationSet.addAnimation(titleAlphaAnimation);
        titleAnimationSet.setDuration(250);
        titleAnimationSet.setInterpolator(new DecelerateInterpolator());
        tvTitle.startAnimation(titleAnimationSet);
    }
}
