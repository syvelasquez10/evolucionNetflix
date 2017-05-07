// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.view.ViewGroup;
import com.facebook.android.R$layout;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.FrameLayout;
import android.view.View$OnClickListener;
import android.view.View$MeasureSpec;
import android.app.Activity;
import com.facebook.android.R$drawable;
import com.facebook.android.R$id;
import android.widget.TextView;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.widget.PopupWindow;
import android.content.Context;
import android.view.View;
import java.lang.ref.WeakReference;

public class ToolTipPopup
{
    private final WeakReference<View> mAnchorViewRef;
    private final Context mContext;
    private long mNuxDisplayTime;
    private ToolTipPopup$PopupContentView mPopupContent;
    private PopupWindow mPopupWindow;
    private final ViewTreeObserver$OnScrollChangedListener mScrollListener;
    private ToolTipPopup$Style mStyle;
    private final String mText;
    
    public ToolTipPopup(final String mText, final View view) {
        this.mStyle = ToolTipPopup$Style.BLUE;
        this.mNuxDisplayTime = 6000L;
        this.mScrollListener = (ViewTreeObserver$OnScrollChangedListener)new ToolTipPopup$1(this);
        this.mText = mText;
        this.mAnchorViewRef = new WeakReference<View>(view);
        this.mContext = view.getContext();
    }
    
    private void registerObserver() {
        this.unregisterObserver();
        if (this.mAnchorViewRef.get() != null) {
            this.mAnchorViewRef.get().getViewTreeObserver().addOnScrollChangedListener(this.mScrollListener);
        }
    }
    
    private void unregisterObserver() {
        if (this.mAnchorViewRef.get() != null) {
            this.mAnchorViewRef.get().getViewTreeObserver().removeOnScrollChangedListener(this.mScrollListener);
        }
    }
    
    private void updateArrows() {
        if (this.mPopupWindow != null && this.mPopupWindow.isShowing()) {
            if (!this.mPopupWindow.isAboveAnchor()) {
                this.mPopupContent.showTopArrow();
                return;
            }
            this.mPopupContent.showBottomArrow();
        }
    }
    
    public void dismiss() {
        this.unregisterObserver();
        if (this.mPopupWindow != null) {
            this.mPopupWindow.dismiss();
        }
    }
    
    public void setNuxDisplayTime(final long mNuxDisplayTime) {
        this.mNuxDisplayTime = mNuxDisplayTime;
    }
    
    public void setStyle(final ToolTipPopup$Style mStyle) {
        this.mStyle = mStyle;
    }
    
    public void show() {
        if (this.mAnchorViewRef.get() != null) {
            this.mPopupContent = new ToolTipPopup$PopupContentView(this, this.mContext);
            ((TextView)this.mPopupContent.findViewById(R$id.com_facebook_tooltip_bubble_view_text_body)).setText((CharSequence)this.mText);
            if (this.mStyle == ToolTipPopup$Style.BLUE) {
                this.mPopupContent.bodyFrame.setBackgroundResource(R$drawable.com_facebook_tooltip_blue_background);
                this.mPopupContent.bottomArrow.setImageResource(R$drawable.com_facebook_tooltip_blue_bottomnub);
                this.mPopupContent.topArrow.setImageResource(R$drawable.com_facebook_tooltip_blue_topnub);
                this.mPopupContent.xOut.setImageResource(R$drawable.com_facebook_tooltip_blue_xout);
            }
            else {
                this.mPopupContent.bodyFrame.setBackgroundResource(R$drawable.com_facebook_tooltip_black_background);
                this.mPopupContent.bottomArrow.setImageResource(R$drawable.com_facebook_tooltip_black_bottomnub);
                this.mPopupContent.topArrow.setImageResource(R$drawable.com_facebook_tooltip_black_topnub);
                this.mPopupContent.xOut.setImageResource(R$drawable.com_facebook_tooltip_black_xout);
            }
            final View decorView = ((Activity)this.mContext).getWindow().getDecorView();
            final int width = decorView.getWidth();
            final int height = decorView.getHeight();
            this.registerObserver();
            this.mPopupContent.onMeasure(View$MeasureSpec.makeMeasureSpec(width, Integer.MIN_VALUE), View$MeasureSpec.makeMeasureSpec(height, Integer.MIN_VALUE));
            (this.mPopupWindow = new PopupWindow((View)this.mPopupContent, this.mPopupContent.getMeasuredWidth(), this.mPopupContent.getMeasuredHeight())).showAsDropDown((View)this.mAnchorViewRef.get());
            this.updateArrows();
            if (this.mNuxDisplayTime > 0L) {
                this.mPopupContent.postDelayed((Runnable)new ToolTipPopup$2(this), this.mNuxDisplayTime);
            }
            this.mPopupWindow.setTouchable(true);
            this.mPopupContent.setOnClickListener((View$OnClickListener)new ToolTipPopup$3(this));
        }
    }
}
