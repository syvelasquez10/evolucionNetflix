// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.ViewParent;
import android.support.design.R$layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Build$VERSION;
import android.support.v4.widget.TextViewCompat;
import android.view.View$MeasureSpec;
import android.widget.Toast;
import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;
import android.annotation.TargetApi;
import android.support.v7.app.ActionBar$Tab;
import android.view.accessibility.AccessibilityEvent;
import android.view.ViewGroup$MarginLayoutParams;
import android.text.TextUtils;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.content.res.AppCompatResources;
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
    private int mDefaultMaxLines;
    private ImageView mIconView;
    private TabLayout$Tab mTab;
    private TextView mTextView;
    final /* synthetic */ TabLayout this$0;
    
    public TabLayout$TabView(final TabLayout this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
        this.mDefaultMaxLines = 2;
        if (this$0.mTabBackgroundResId != 0) {
            ViewCompat.setBackground((View)this, AppCompatResources.getDrawable(context, this$0.mTabBackgroundResId));
        }
        ViewCompat.setPaddingRelative((View)this, this$0.mTabPaddingStart, this$0.mTabPaddingTop, this$0.mTabPaddingEnd, this$0.mTabPaddingBottom);
        this.setGravity(17);
        this.setOrientation(1);
        this.setClickable(true);
        ViewCompat.setPointerIcon((View)this, PointerIconCompat.getSystemIcon(this.getContext(), 1002));
    }
    
    private float approximateLineWidth(final Layout layout, final int n, final float n2) {
        return layout.getLineWidth(n) * (n2 / layout.getPaint().getTextSize());
    }
    
    private void updateTextAndIcon(final TextView textView, final ImageView imageView) {
        Drawable icon;
        if (this.mTab != null) {
            icon = this.mTab.getIcon();
        }
        else {
            icon = null;
        }
        CharSequence text;
        if (this.mTab != null) {
            text = this.mTab.getText();
        }
        else {
            text = null;
        }
        CharSequence contentDescription;
        if (this.mTab != null) {
            contentDescription = this.mTab.getContentDescription();
        }
        else {
            contentDescription = null;
        }
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
            imageView.setContentDescription(contentDescription);
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
                textView.setVisibility(0);
                this.setVisibility(0);
            }
            else {
                textView.setVisibility(8);
                textView.setText((CharSequence)null);
            }
            textView.setContentDescription(contentDescription);
        }
        if (imageView != null) {
            final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)imageView.getLayoutParams();
            int dpToPx;
            if (b && imageView.getVisibility() == 0) {
                dpToPx = this.this$0.dpToPx(8);
            }
            else {
                dpToPx = 0;
            }
            if (dpToPx != viewGroup$MarginLayoutParams.bottomMargin) {
                viewGroup$MarginLayoutParams.bottomMargin = dpToPx;
                imageView.requestLayout();
            }
        }
        if (!b && !TextUtils.isEmpty(contentDescription)) {
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
        final Rect rect = new Rect();
        this.getLocationOnScreen(array);
        this.getWindowVisibleDisplayFrame(rect);
        final Context context = this.getContext();
        final int width = this.getWidth();
        final int height = this.getHeight();
        final int n = array[1];
        final int n2 = height / 2;
        int n3 = width / 2 + array[0];
        if (ViewCompat.getLayoutDirection(view) == 0) {
            n3 = context.getResources().getDisplayMetrics().widthPixels - n3;
        }
        final Toast text = Toast.makeText(context, this.mTab.getContentDescription(), 0);
        if (n + n2 < rect.height()) {
            text.setGravity(8388661, n3, array[1] + height - rect.top);
        }
        else {
            text.setGravity(81, 0, height);
        }
        text.show();
        return true;
    }
    
    public void onMeasure(int maxLines, final int n) {
        final boolean b = true;
        final int size = View$MeasureSpec.getSize(maxLines);
        final int mode = View$MeasureSpec.getMode(maxLines);
        final int tabMaxWidth = this.this$0.getTabMaxWidth();
        int measureSpec = maxLines;
        Label_0062: {
            if (tabMaxWidth > 0) {
                if (mode != 0) {
                    measureSpec = maxLines;
                    if (size <= tabMaxWidth) {
                        break Label_0062;
                    }
                }
                measureSpec = View$MeasureSpec.makeMeasureSpec(this.this$0.mTabMaxWidth, Integer.MIN_VALUE);
            }
        }
        super.onMeasure(measureSpec, n);
        if (this.mTextView != null) {
            this.getResources();
            final float mTabTextSize = this.this$0.mTabTextSize;
            final int mDefaultMaxLines = this.mDefaultMaxLines;
            float mTabTextMultiLineSize;
            if (this.mIconView != null && this.mIconView.getVisibility() == 0) {
                maxLines = 1;
                mTabTextMultiLineSize = mTabTextSize;
            }
            else {
                maxLines = mDefaultMaxLines;
                mTabTextMultiLineSize = mTabTextSize;
                if (this.mTextView != null) {
                    maxLines = mDefaultMaxLines;
                    mTabTextMultiLineSize = mTabTextSize;
                    if (this.mTextView.getLineCount() > 1) {
                        mTabTextMultiLineSize = this.this$0.mTabTextMultiLineSize;
                        maxLines = mDefaultMaxLines;
                    }
                }
            }
            final float textSize = this.mTextView.getTextSize();
            final int lineCount = this.mTextView.getLineCount();
            final int maxLines2 = TextViewCompat.getMaxLines(this.mTextView);
            if (mTabTextMultiLineSize != textSize || (maxLines2 >= 0 && maxLines != maxLines2)) {
                boolean b2 = b;
                Label_0247: {
                    if (this.this$0.mMode == 1) {
                        b2 = b;
                        if (mTabTextMultiLineSize > textSize) {
                            b2 = b;
                            if (lineCount == 1) {
                                final Layout layout = this.mTextView.getLayout();
                                if (layout != null) {
                                    b2 = b;
                                    if (this.approximateLineWidth(layout, 0, mTabTextMultiLineSize) <= this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight()) {
                                        break Label_0247;
                                    }
                                }
                                b2 = false;
                            }
                        }
                    }
                }
                if (b2) {
                    this.mTextView.setTextSize(0, mTabTextMultiLineSize);
                    this.mTextView.setMaxLines(maxLines);
                    super.onMeasure(measureSpec, n);
                }
            }
        }
    }
    
    public boolean performClick() {
        boolean performClick = super.performClick();
        if (this.mTab != null) {
            if (!performClick) {
                this.playSoundEffect(0);
            }
            this.mTab.select();
            performClick = true;
        }
        return performClick;
    }
    
    void reset() {
        this.setTab(null);
        this.setSelected(false);
    }
    
    public void setSelected(final boolean b) {
        boolean b2;
        if (this.isSelected() != b) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        super.setSelected(b);
        if (b2 && b && Build$VERSION.SDK_INT < 16) {
            this.sendAccessibilityEvent(4);
        }
        if (this.mTextView != null) {
            this.mTextView.setSelected(b);
        }
        if (this.mIconView != null) {
            this.mIconView.setSelected(b);
        }
        if (this.mCustomView != null) {
            this.mCustomView.setSelected(b);
        }
    }
    
    void setTab(final TabLayout$Tab mTab) {
        if (mTab != this.mTab) {
            this.mTab = mTab;
            this.update();
        }
    }
    
    final void update() {
        final TabLayout$Tab mTab = this.mTab;
        View customView;
        if (mTab != null) {
            customView = mTab.getCustomView();
        }
        else {
            customView = null;
        }
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
            if (this.mCustomTextView != null) {
                this.mDefaultMaxLines = TextViewCompat.getMaxLines(this.mCustomTextView);
            }
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
                this.mDefaultMaxLines = TextViewCompat.getMaxLines(this.mTextView);
            }
            TextViewCompat.setTextAppearance(this.mTextView, this.this$0.mTabTextAppearance);
            if (this.this$0.mTabTextColors != null) {
                this.mTextView.setTextColor(this.this$0.mTabTextColors);
            }
            this.updateTextAndIcon(this.mTextView, this.mIconView);
        }
        else if (this.mCustomTextView != null || this.mCustomIconView != null) {
            this.updateTextAndIcon(this.mCustomTextView, this.mCustomIconView);
        }
        this.setSelected(mTab != null && mTab.isSelected());
    }
}
