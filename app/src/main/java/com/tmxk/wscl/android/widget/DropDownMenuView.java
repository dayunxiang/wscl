package com.tmxk.wscl.android.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.tmxk.wscl.android.R;

public class DropDownMenuView extends RelativeLayout {
    //默认插值器
    private Interpolator mInterpolator = new DecelerateInterpolator();
    //筛选条件的view
    private View viewMenu;
    //遮盖层的view
    private View viewMask;
    //遮盖层默认颜色
    private int mMaskViewColor = 0x60434444;
    //动画时间
    private int mDurationTime = 300;
    //判断当前状态
    private boolean mIsOpen = false;
    private Context mContext;

    public DropDownMenuView(Context context) {
        this(context, null);
    }

    public DropDownMenuView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropDownMenuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initAttr(attrs);
        initView();
    }

    private void initView() {
        addMaskView(); //添加一个遮盖层
        checkLayout();//检查是否满足要求
    }

    private void initAttr(@Nullable AttributeSet attrs) {
        TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.DropDownMenuView);
        mMaskViewColor = array.getColor(R.styleable.DropDownMenuView_mask_view_color, mMaskViewColor);
        mDurationTime = array.getInteger(R.styleable.DropDownMenuView_duration, mDurationTime);
        array.recycle();
    }

    private void addMaskView() {
        if (viewMask != null) {
            return;
        }
        viewMask = new View(mContext);
        RelativeLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        viewMask.setLayoutParams(params);
        viewMask.setBackgroundColor(mMaskViewColor);   //灰黑色透明背景
        addView(viewMask);  //添加view
        viewMask.setVisibility(GONE); //默认是隐藏的
        viewMask.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                close();  //收缩遮盖层
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        checkChildCount();  //检查子view的数量
        View viewTop = getChildAt(1);   //顶部的view
        viewMenu = getChildAt(2);
        viewTop.setClickable(true); //设置可点击，避免点击事件传递到底层的viewMask
        viewMenu.setClickable(true);
        //设置背景色
        if (viewTop.getBackground() == null) {
            viewTop.setBackgroundColor(Color.WHITE);
        }
        if (viewMenu.getBackground() == null) {
            viewMenu.setBackgroundColor(Color.WHITE);
        }
        //默认是隐藏的
        viewMenu.setVisibility(GONE);
    }

    private void checkLayout() {
        post(new Runnable() {
            @Override
            public void run() {
                checkParentLayout();   //检查父布局是否满足要求
            }
        });
    }

    private void checkParentLayout() {
        ViewGroup viewGroup = (ViewGroup) this.getParent();
        if (!(viewGroup instanceof FrameLayout)) {
            throw new RuntimeException("ParentView must is FrameLayout ");
        }
    }

    private void checkChildCount() {
        if (this.getChildCount() != 3) {
            throw new RuntimeException("Only two child view support!");
        }
    }

    private void openAnimation() {
        //设置展开的基准位置,从顶部开始展开(默认是中心位置展开收缩)
        viewMenu.setPivotY(0);
        viewMenu.setVisibility(View.VISIBLE);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(viewMenu, "scaleY", 0f, 1f);
        scaleY.setDuration(mDurationTime);
        scaleY.setInterpolator(mInterpolator);
        scaleY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mIsOpen = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        scaleY.start();
        //渐变显示maskView
        viewMask.setVisibility(View.VISIBLE);
        changeAlpha(viewMask, 0f, 1f);
    }

    private void closeAnimation() {
        viewMenu.setPivotY(0);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(viewMenu, "scaleY", 1f, 0f);
        scaleY.setDuration(mDurationTime);
        scaleY.setInterpolator(mInterpolator);
        scaleY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                viewMenu.setVisibility(View.GONE);
                if (viewMask != null) {
                    viewMask.setVisibility(View.GONE);
                }
                mIsOpen = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        scaleY.start();
        //渐变隐藏maskView
        changeAlpha(viewMask, 1f, 0f);
    }

    private void changeAlpha(@NonNull View view, float startStatus, float endStatus) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", startStatus, endStatus);
        alpha.setDuration(mDurationTime);
        alpha.start();
    }

    public void open() {
        if (isOpen()) {
            return;
        }
        if (viewMenu == null) {
            return;
        }
        openAnimation();
    }

    public void close() {
        if (!isOpen()) {
            return;
        }
        if (viewMenu == null) {
            return;
        }
        closeAnimation();
    }

    public boolean isOpen() {
        return mIsOpen;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }
}