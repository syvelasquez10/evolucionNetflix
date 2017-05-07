// 
// Decompiled by Procyon v0.5.30
// 

package com.viewpagerindicator;

import android.widget.ImageView;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.util.AttributeSet;
import android.content.Context;
import com.viewpagerindicator.android.osp.ViewPager;
import com.viewpagerindicator.android.osp.ViewPager$OnPageChangeListener;
import android.widget.HorizontalScrollView;

public class IconPageIndicator extends HorizontalScrollView implements PageIndicator
{
    private Runnable mIconSelector;
    private final IcsLinearLayout mIconsLayout;
    private ViewPager$OnPageChangeListener mListener;
    private int mSelectedIndex;
    private ViewPager mViewPager;
    
    public IconPageIndicator(final Context context) {
        this(context, null);
    }
    
    public IconPageIndicator(final Context context, final AttributeSet set) {
        super(context, set);
        this.setHorizontalScrollBarEnabled(false);
        this.addView((View)(this.mIconsLayout = new IcsLinearLayout(context, R$attr.vpiIconPageIndicatorStyle)), (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -1, 17));
    }
    
    private void animateToIcon(final int n) {
        final View child = this.mIconsLayout.getChildAt(n);
        if (this.mIconSelector != null) {
            this.removeCallbacks(this.mIconSelector);
        }
        this.post(this.mIconSelector = new IconPageIndicator$1(this, child));
    }
    
    public void notifyDataSetChanged() {
        this.mIconsLayout.removeAllViews();
        final IconPagerAdapter iconPagerAdapter = (IconPagerAdapter)this.mViewPager.getAdapter();
        final int count = iconPagerAdapter.getCount();
        for (int i = 0; i < count; ++i) {
            final ImageView imageView = new ImageView(this.getContext(), (AttributeSet)null, R$attr.vpiIconPageIndicatorStyle);
            imageView.setImageResource(iconPagerAdapter.getIconResId(i));
            this.mIconsLayout.addView((View)imageView);
        }
        if (this.mSelectedIndex > count) {
            this.mSelectedIndex = count - 1;
        }
        this.setCurrentItem(this.mSelectedIndex);
        this.requestLayout();
    }
    
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mIconSelector != null) {
            this.post(this.mIconSelector);
        }
    }
    
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mIconSelector != null) {
            this.removeCallbacks(this.mIconSelector);
        }
    }
    
    public void onPageScrollStateChanged(final int n) {
        if (this.mListener != null) {
            this.mListener.onPageScrollStateChanged(n);
        }
    }
    
    public void onPageScrolled(final int n, final float n2, final int n3) {
        if (this.mListener != null) {
            this.mListener.onPageScrolled(n, n2, n3);
        }
    }
    
    public void onPageSelected(final int currentItem) {
        this.setCurrentItem(currentItem);
        if (this.mListener != null) {
            this.mListener.onPageSelected(currentItem);
        }
    }
    
    public void setCurrentItem(final int n) {
        if (this.mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        this.mSelectedIndex = n;
        this.mViewPager.setCurrentItem(n);
        for (int childCount = this.mIconsLayout.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.mIconsLayout.getChildAt(i);
            final boolean selected = i == n;
            child.setSelected(selected);
            if (selected) {
                this.animateToIcon(n);
            }
        }
    }
    
    public void setOnPageChangeListener(final ViewPager$OnPageChangeListener mListener) {
        this.mListener = mListener;
    }
    
    public void setViewPager(final ViewPager mViewPager) {
        if (this.mViewPager == mViewPager) {
            return;
        }
        if (this.mViewPager != null) {
            this.mViewPager.setOnPageChangeListener(null);
        }
        if (mViewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        (this.mViewPager = mViewPager).setOnPageChangeListener(this);
        this.notifyDataSetChanged();
    }
    
    public void setViewPager(final ViewPager viewPager, final int currentItem) {
        this.setViewPager(viewPager);
        this.setCurrentItem(currentItem);
    }
}
