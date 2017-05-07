// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.view.ViewGroup$LayoutParams;
import android.content.res.Resources;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import android.view.View$OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class ErrorWrapper
{
    private static final String TAG = "ErrorWrapper";
    private final Callback callback;
    private final View errorGroup;
    private final TextView errorMsg;
    private final Button retryBtn;
    private final View$OnClickListener retryClickListener;
    private boolean showRetry;
    
    public ErrorWrapper(final View view, final Callback callback) {
        this.showRetry = true;
        this.retryClickListener = (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                Log.v("ErrorWrapper", "Retry requested");
                if (ErrorWrapper.this.callback != null) {
                    ErrorWrapper.this.callback.onRetryRequested();
                }
            }
        };
        this.callback = callback;
        this.errorGroup = view.findViewById(2131165354);
        this.errorMsg = (TextView)view.findViewById(2131165355);
        (this.retryBtn = (Button)this.errorGroup.findViewById(2131165356)).setOnClickListener(this.retryClickListener);
        if (view.getContext() instanceof NetflixActivity) {
            final NetflixActivity netflixActivity = (NetflixActivity)view.getContext();
            if (netflixActivity.isForKids()) {
                this.configureViewsForKidsExperience(netflixActivity);
            }
        }
        this.setRetryVisibility();
    }
    
    private void configureViewsForKidsExperience(final NetflixActivity netflixActivity) {
        final Resources resources = netflixActivity.getResources();
        this.errorMsg.setTextColor(resources.getColor(2131296306));
        ViewUtils.setTextViewSizeByRes(this.errorMsg, 2131361838);
        ViewUtils.setTextViewToBold(this.errorMsg);
        final ViewGroup$LayoutParams layoutParams = this.retryBtn.getLayoutParams();
        layoutParams.height = resources.getDimensionPixelSize(2131361928);
        layoutParams.width = resources.getDimensionPixelSize(2131361927);
        this.retryBtn.setBackgroundResource(2130837753);
        this.retryBtn.setTextColor(resources.getColor(2131296309));
        ViewUtils.setTextViewSizeByRes((TextView)this.retryBtn, 2131361843);
        ViewUtils.setTextViewToBold((TextView)this.retryBtn);
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
    
    public void showErrorView(final boolean b) {
        AnimationUtils.showView(this.errorGroup, b);
        this.setRetryVisibility();
    }
    
    public interface Callback
    {
        void onRetryRequested();
    }
}
