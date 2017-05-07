// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.util.gfx.AnimationUtils;
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
        this.errorGroup = view.findViewById(2131230850);
        this.errorMsg = (TextView)view.findViewById(2131230851);
        (this.retryBtn = (Button)this.errorGroup.findViewById(2131230852)).setOnClickListener(this.retryClickListener);
        this.setRetryVisibility();
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
