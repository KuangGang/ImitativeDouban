package com.kproduce.imitativedouban.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.kproduce.imitativedouban.R;
import com.kproduce.imitativedouban.utils.ScreenUtil;

/**
 * @author KG
 * @date 2019/9/3
 * 垂直的DrawerLayout
 */
public class VerticalDrawerLayout extends RelativeLayout {
    private static final String TAG = "VerticalDrawerLayout";

    private Context mContext;
    private View mDrawerView;
    private ViewDragHelper mDragHelper;
    private boolean mIsOpenDrawer;
    private boolean mCanScrollDrawer = false;
    private boolean mIsHandleDrawer = false;
    private int mVisHeight;
    private OnStatusChangeListener mOnStatusChangeListener;

    public interface OnStatusChangeListener {
        void onOpen();

        void onClose();
    }

    public VerticalDrawerLayout(Context context) {
        this(context, null);
    }

    public VerticalDrawerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public VerticalDrawerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        mDragHelper = ViewDragHelper.create(this, 2.0f, new ViewDragHelperCallBack());
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_BOTTOM);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.VerticalDrawerLayout, defStyleAttr, 0);
            mVisHeight = a.getDimensionPixelSize(R.styleable.VerticalDrawerLayout_vd_vis_height, 0);

            a.recycle();
        }
    }

    private class ViewDragHelperCallBack extends ViewDragHelper.Callback {

        private long startTime;
        private long endTime;
        private boolean isWantOpen = true;

        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            Log.e(TAG, "tryCaptureView：pointerId=" + pointerId);
            startTime = System.currentTimeMillis();
            mIsHandleDrawer = child == mDrawerView;
            return mIsHandleDrawer;
        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            Log.e(TAG, "onEdgeTouched：edgeFlags=" + edgeFlags + " ppointerId=" + pointerId);
            if (edgeFlags == ViewDragHelper.EDGE_BOTTOM) {
                mDragHelper.captureChildView(mDrawerView, pointerId);
            }
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            Log.e(TAG, "clampViewPositionVertical：top=" + top + " dy=" + dy);
            isWantOpen = dy < 0;
            return Math.min(Math.max(top, 0), mDrawerView.getHeight());
        }


        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            Log.e(TAG, "onViewReleased：xvel=" + xvel + " yvel=" + yvel);
            mIsHandleDrawer = false;
            // 快速拖动逻辑
            endTime = System.currentTimeMillis();
            if (endTime - startTime < 200) {
                if (isWantOpen) {
                    openDrawer();
                    return;
                } else {
                    if (mCanScrollDrawer) {
                        closeDrawer();
                        return;
                    }
                }
            }
            // 拖动后定位逻辑
            int childTop = releasedChild.getTop();
            int parentTop = getTop();
            int screenHeight = ScreenUtil.getScreenHeight(mContext);
            if (screenHeight == 0) {
                return;
            }
            float movePrecent = (childTop + parentTop) / (float) screenHeight;
            int finalTop = (movePrecent <= 0.5f) ? 0 : releasedChild.getHeight() - mVisHeight;
            setCanScrollDrawer(true);
            mIsOpenDrawer = finalTop == 0;
            if (mOnStatusChangeListener != null) {
                if (mIsOpenDrawer) {
                    mOnStatusChangeListener.onOpen();
                } else {
                    mOnStatusChangeListener.onClose();
                }
            }
            mDragHelper.settleCapturedViewAt(releasedChild.getLeft(), finalTop);
            invalidate();
        }

        @Override
        public int getViewVerticalDragRange(@NonNull View child) {
            Log.e(TAG, "getViewVerticalDragRange：mCanScrollDrawer=" + mCanScrollDrawer);
            if (child == mDrawerView) {
                if (mIsOpenDrawer) {
                    return mCanScrollDrawer ? 1 : 0;
                } else {
                    return 1;
                }
            } else {
                return 0;
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return mIsHandleDrawer;
    }

    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mDrawerView = getChildAt(0);

        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        Log.e(TAG, "onMeasure:  measureWidth=" + measureWidth + " measureHeight=" + measureHeight);
        setMeasuredDimension(measureWidth, measureHeight);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //每一个子控件测量大小
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e(TAG, "onLayout:  l=" + l + " t=" + t + " r=" + r + " b=" + b);
        int topValue = mDrawerView.getMeasuredHeight() - mVisHeight;
        mDrawerView.layout(l, mIsOpenDrawer ? 0 : topValue, r, mIsOpenDrawer ? topValue + mVisHeight : b - t + topValue);
    }

    public void setCanScrollDrawer(boolean canScrollDrawer) {
        this.mCanScrollDrawer = canScrollDrawer;
    }

    public void openDrawer() {
        if (mOnStatusChangeListener != null) {
            mOnStatusChangeListener.onOpen();
        }
        setCanScrollDrawer(true);
        mIsOpenDrawer = true;
        mDragHelper.smoothSlideViewTo(mDrawerView, mDrawerView.getLeft(), 0);
        invalidate();
    }

    public void closeDrawer() {
        if (mOnStatusChangeListener != null) {
            mOnStatusChangeListener.onClose();
        }
        setCanScrollDrawer(true);
        mIsOpenDrawer = false;
        mDragHelper.smoothSlideViewTo(mDrawerView, mDrawerView.getLeft(), mDrawerView.getHeight() - mVisHeight);
        invalidate();
    }

    public void showDrawer() {
        if (mDrawerView == null) {
            return;
        }
        mDrawerView.setVisibility(VISIBLE);
    }

    public void dismissDrawer() {
        if (mDrawerView == null) {
            return;
        }
        mDrawerView.setVisibility(GONE);
    }

    public boolean isDrawerShow() {
        if (mDrawerView == null) {
            return false;
        }
        return mDrawerView.getVisibility() == VISIBLE;
    }

    public void setOnStatusChangeListener(OnStatusChangeListener onStatusChangeListener) {
        mOnStatusChangeListener = onStatusChangeListener;
    }
}
