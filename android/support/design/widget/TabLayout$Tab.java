// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.view.ViewPager$OnPageChangeListener;
import android.support.v4.view.ViewPager;
import android.support.v4.view.PagerAdapter;
import java.util.Iterator;
import android.view.View$MeasureSpec;
import android.widget.LinearLayout$LayoutParams;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.support.design.R$style;
import android.support.design.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import java.util.ArrayList;
import android.content.res.ColorStateList;
import android.view.View$OnClickListener;
import android.widget.HorizontalScrollView;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.graphics.drawable.Drawable;
import android.view.View;

public final class TabLayout$Tab
{
    private CharSequence mContentDesc;
    private View mCustomView;
    private Drawable mIcon;
    private final TabLayout mParent;
    private int mPosition;
    private CharSequence mText;
    
    TabLayout$Tab(final TabLayout mParent) {
        this.mPosition = -1;
        this.mParent = mParent;
    }
    
    public CharSequence getContentDescription() {
        return this.mContentDesc;
    }
    
    public View getCustomView() {
        return this.mCustomView;
    }
    
    public Drawable getIcon() {
        return this.mIcon;
    }
    
    public int getPosition() {
        return this.mPosition;
    }
    
    public CharSequence getText() {
        return this.mText;
    }
    
    public void select() {
        this.mParent.selectTab(this);
    }
    
    public TabLayout$Tab setCustomView(final int n) {
        return this.setCustomView(LayoutInflater.from(this.mParent.getContext()).inflate(n, (ViewGroup)null));
    }
    
    public TabLayout$Tab setCustomView(final View mCustomView) {
        this.mCustomView = mCustomView;
        if (this.mPosition >= 0) {
            this.mParent.updateTab(this.mPosition);
        }
        return this;
    }
    
    void setPosition(final int mPosition) {
        this.mPosition = mPosition;
    }
    
    public TabLayout$Tab setText(final CharSequence mText) {
        this.mText = mText;
        if (this.mPosition >= 0) {
            this.mParent.updateTab(this.mPosition);
        }
        return this;
    }
}
