// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.view.ViewPager$OnPageChangeListener;
import android.support.v4.view.ViewPager;
import android.support.v4.view.PagerAdapter;
import java.util.Iterator;
import android.widget.LinearLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.support.design.R$style;
import android.support.design.R$styleable;
import android.util.AttributeSet;
import java.util.ArrayList;
import android.content.res.ColorStateList;
import android.view.View$OnClickListener;
import android.widget.HorizontalScrollView;
import android.view.ViewParent;
import android.support.design.R$layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View$MeasureSpec;
import android.widget.Toast;
import android.view.accessibility.AccessibilityNodeInfo;
import android.annotation.TargetApi;
import android.support.v7.app.ActionBar$Tab;
import android.view.accessibility.AccessibilityEvent;
import android.text.TextUtils;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.internal.widget.TintManager;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View$OnLongClickListener;
import android.widget.LinearLayout;

class TabLayout$TabView extends LinearLayout implements View$OnLongClickListener
{
    private ImageView mCustomIconView;
    private TextView mCustomTextView;
    private View mCustomView;
    private ImageView mIconView;
    private final TabLayout$Tab mTab;
    private TextView mTextView;
    final /* synthetic */ TabLayout this$0;
    
    public TabLayout$TabView(final TabLayout this$0, final Context context, final TabLayout$Tab mTab) {
        this.this$0 = this$0;
        super(context);
        this.mTab = mTab;
        if (this$0.mTabBackgroundResId != 0) {
            this.setBackgroundDrawable(TintManager.getDrawable(context, this$0.mTabBackgroundResId));
        }
        ViewCompat.setPaddingRelative((View)this, this$0.mTabPaddingStart, this$0.mTabPaddingTop, this$0.mTabPaddingEnd, this$0.mTabPaddingBottom);
        this.setGravity(17);
        this.update();
    }
    
    private void updateTextAndIcon(final TabLayout$Tab tabLayout$Tab, final TextView textView, final ImageView imageView) {
        final Drawable icon = tabLayout$Tab.getIcon();
        final CharSequence text = tabLayout$Tab.getText();
        if (imageView != null) {
            if (icon != null) {
                imageView.setImageDrawable(icon);
                imageView.setVisibility(0);
                this.setVisibility(0);
            }
            else {
                imageView.setVisibility(8);
                imageView.setImageDrawable((Drawable)null);
            }
            imageView.setContentDescription(tabLayout$Tab.getContentDescription());
        }
        boolean b;
        if (!TextUtils.isEmpty(text)) {
            b = true;
        }
        else {
            b = false;
        }
        if (textView != null) {
            if (b) {
                textView.setText(text);
                textView.setContentDescription(tabLayout$Tab.getContentDescription());
                textView.setVisibility(0);
                this.setVisibility(0);
            }
            else {
                textView.setVisibility(8);
                textView.setText((CharSequence)null);
            }
        }
        if (!b && !TextUtils.isEmpty(tabLayout$Tab.getContentDescription())) {
            this.setOnLongClickListener((View$OnLongClickListener)this);
            return;
        }
        this.setOnLongClickListener((View$OnLongClickListener)null);
        this.setLongClickable(false);
    }
    
    public TabLayout$Tab getTab() {
        return this.mTab;
    }
    
    @TargetApi(14)
    public void onInitializeAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName((CharSequence)ActionBar$Tab.class.getName());
    }
    
    @TargetApi(14)
    public void onInitializeAccessibilityNodeInfo(final AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName((CharSequence)ActionBar$Tab.class.getName());
    }
    
    public boolean onLongClick(final View view) {
        final int[] array = new int[2];
        this.getLocationOnScreen(array);
        final Context context = this.getContext();
        final int width = this.getWidth();
        final int height = this.getHeight();
        final int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        final Toast text = Toast.makeText(context, this.mTab.getContentDescription(), 0);
        text.setGravity(49, array[0] + width / 2 - widthPixels / 2, height);
        text.show();
        return true;
    }
    
    public void onMeasure(int measuredWidth, final int n) {
        super.onMeasure(measuredWidth, n);
        measuredWidth = this.getMeasuredWidth();
        if (measuredWidth < this.this$0.mTabMinWidth || measuredWidth > this.this$0.mTabMaxWidth) {
            super.onMeasure(View$MeasureSpec.makeMeasureSpec(MathUtils.constrain(measuredWidth, this.this$0.mTabMinWidth, this.this$0.mTabMaxWidth), 1073741824), n);
        }
    }
    
    public void setSelected(final boolean selected) {
        boolean b;
        if (this.isSelected() != selected) {
            b = true;
        }
        else {
            b = false;
        }
        super.setSelected(selected);
        if (b && selected) {
            this.sendAccessibilityEvent(4);
            if (this.mTextView != null) {
                this.mTextView.setSelected(selected);
            }
            if (this.mIconView != null) {
                this.mIconView.setSelected(selected);
            }
        }
    }
    
    final void update() {
        final TabLayout$Tab mTab = this.mTab;
        final View customView = mTab.getCustomView();
        if (customView != null) {
            final ViewParent parent = customView.getParent();
            if (parent != this) {
                if (parent != null) {
                    ((ViewGroup)parent).removeView(customView);
                }
                this.addView(customView);
            }
            this.mCustomView = customView;
            if (this.mTextView != null) {
                this.mTextView.setVisibility(8);
            }
            if (this.mIconView != null) {
                this.mIconView.setVisibility(8);
                this.mIconView.setImageDrawable((Drawable)null);
            }
            this.mCustomTextView = (TextView)customView.findViewById(16908308);
            this.mCustomIconView = (ImageView)customView.findViewById(16908294);
        }
        else {
            if (this.mCustomView != null) {
                this.removeView(this.mCustomView);
                this.mCustomView = null;
            }
            this.mCustomTextView = null;
            this.mCustomIconView = null;
        }
        if (this.mCustomView == null) {
            if (this.mIconView == null) {
                final ImageView mIconView = (ImageView)LayoutInflater.from(this.getContext()).inflate(R$layout.design_layout_tab_icon, (ViewGroup)this, false);
                this.addView((View)mIconView, 0);
                this.mIconView = mIconView;
            }
            if (this.mTextView == null) {
                final TextView mTextView = (TextView)LayoutInflater.from(this.getContext()).inflate(R$layout.design_layout_tab_text, (ViewGroup)this, false);
                this.addView((View)mTextView);
                this.mTextView = mTextView;
            }
            this.mTextView.setTextAppearance(this.getContext(), this.this$0.mTabTextAppearance);
            if (this.this$0.mTabTextColors != null) {
                this.mTextView.setTextColor(this.this$0.mTabTextColors);
            }
            this.updateTextAndIcon(mTab, this.mTextView, this.mIconView);
        }
        else if (this.mCustomTextView != null || this.mCustomIconView != null) {
            this.updateTextAndIcon(mTab, this.mCustomTextView, this.mCustomIconView);
        }
    }
}
