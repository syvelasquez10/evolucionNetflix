// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.view.ViewGroup$LayoutParams;
import android.content.res.Resources;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class ErrorWrapper
{
    private static final String TAG = "ErrorWrapper";
    private ErrorWrapper$Callback callback;
    private final View errorGroup;
    private final TextView errorMsg;
    private final Button retryBtn;
    private final View$OnClickListener retryClickListener;
    private boolean showRetry;
    
    public ErrorWrapper(final View view, final ErrorWrapper$Callback callback) {
        this.showRetry = true;
        this.retryClickListener = (View$OnClickListener)new ErrorWrapper$1(this);
        this.callback = callback;
        (this.errorGroup = view.findViewById(2131624168)).setVisibility(0);
        this.errorMsg = (TextView)view.findViewById(2131624169);
        (this.retryBtn = (Button)this.errorGroup.findViewById(2131624170)).setOnClickListener(this.retryClickListener);
        if (view.getContext() instanceof NetflixActivity) {
            final NetflixActivity netflixActivity = (NetflixActivity)view.getContext();
            if (BrowseExperience.isKubrickKids()) {
                this.configureViewsForKidsExperience(netflixActivity);
            }
        }
        this.setRetryVisibility();
    }
    
    private void configureViewsForKidsExperience(final NetflixActivity netflixActivity) {
        final Resources resources = netflixActivity.getResources();
        this.errorMsg.setTextColor(resources.getColor(2131558453));
        ViewUtils.setTextViewSizeByRes(this.errorMsg, 2131296335);
        ViewUtils.setTextViewToBold(this.errorMsg);
        final ViewGroup$LayoutParams layoutParams = this.retryBtn.getLayoutParams();
        layoutParams.height = resources.getDimensionPixelSize(2131296495);
        layoutParams.width = resources.getDimensionPixelSize(2131296496);
        this.retryBtn.setAllCaps(false);
        this.retryBtn.setBackgroundResource(2130837766);
        this.retryBtn.setTextColor(resources.getColor(2131558596));
        ViewUtils.setTextViewSizeByRes((TextView)this.retryBtn, 2131296328);
    }
    
    private void setRetryVisibility() {
        final Button retryBtn = this.retryBtn;
        int visibility;
        if (this.callback == null || !this.showRetry) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        retryBtn.setVisibility(visibility);
    }
    
    public TextView getErrorMessageTextView() {
        return this.errorMsg;
    }
    
    public void hide(final boolean b) {
        AnimationUtils.hideView(this.errorGroup, b);
    }
    
    public void showErrorView(final int text, final boolean showRetry, final boolean b) {
        this.errorMsg.setText(text);
        this.showRetry = showRetry;
        AnimationUtils.showView(this.errorGroup, b);
        this.setRetryVisibility();
    }
    
    public void showErrorView(final CharSequence text, final int text2, final ErrorWrapper$Callback callback) {
        this.showRetry = true;
        this.errorMsg.setText(text);
        this.retryBtn.setText(text2);
        AnimationUtils.showView(this.errorGroup, false);
        this.setRetryVisibility();
        this.callback = callback;
    }
    
    public void showErrorView(final boolean b) {
        AnimationUtils.showView(this.errorGroup, b);
        this.setRetryVisibility();
    }
}
