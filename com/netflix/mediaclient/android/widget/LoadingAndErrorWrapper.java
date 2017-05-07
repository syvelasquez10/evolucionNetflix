// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.os.Looper;
import android.view.View;
import android.os.Handler;

public class LoadingAndErrorWrapper extends ErrorWrapper
{
    private static final long SHOW_LOADING_VIEW_RUNNABLE_DELAY_MS = 150L;
    private static final String TAG = "LoadingAndErrorWrapper";
    private final Handler handler;
    private final View loading;
    private final Runnable showLoadingViewAnimatedRunnable;
    private final Runnable showLoadingViewRunnable;
    
    public LoadingAndErrorWrapper(final View view, final ErrorWrapper$Callback errorWrapper$Callback) {
        super(view, errorWrapper$Callback);
        this.showLoadingViewRunnable = new LoadingAndErrorWrapper$1(this);
        this.showLoadingViewAnimatedRunnable = new LoadingAndErrorWrapper$2(this);
        this.handler = new Handler(Looper.getMainLooper());
        (this.loading = view.findViewById(2131624195)).setVisibility(8);
        this.showLoadingView(false);
    }
    
    private void cancelLoadingRunnables() {
        this.handler.removeCallbacks(this.showLoadingViewRunnable);
        this.handler.removeCallbacks(this.showLoadingViewAnimatedRunnable);
    }
    
    @Override
    public void hide(final boolean b) {
        this.cancelLoadingRunnables();
        super.hide(b);
        AnimationUtils.hideView(this.loading, b);
    }
    
    @Override
    public void showErrorView(final int n, final boolean b, final boolean b2) {
        this.cancelLoadingRunnables();
        super.showErrorView(n, b, b2);
        AnimationUtils.hideView(this.loading, b2);
    }
    
    @Override
    public void showErrorView(final boolean b) {
        this.cancelLoadingRunnables();
        super.showErrorView(b);
        AnimationUtils.hideView(this.loading, b);
    }
    
    public void showLoadingView(final boolean b) {
        this.cancelLoadingRunnables();
        super.hide(b);
        if (this.loading.getVisibility() == 0) {
            Log.v("LoadingAndErrorWrapper", "Loading view is already visible - skipping");
            return;
        }
        Log.v("LoadingAndErrorWrapper", "Showing loading view after delay");
        final Handler handler = this.handler;
        Runnable runnable;
        if (b) {
            runnable = this.showLoadingViewAnimatedRunnable;
        }
        else {
            runnable = this.showLoadingViewRunnable;
        }
        handler.postDelayed(runnable, 150L);
    }
}
