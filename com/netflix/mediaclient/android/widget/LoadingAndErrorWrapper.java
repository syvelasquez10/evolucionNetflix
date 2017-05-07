// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.view.View;

public class LoadingAndErrorWrapper extends ErrorWrapper
{
    private final View loading;
    
    public LoadingAndErrorWrapper(final View view, final Callback callback) {
        super(view, callback);
        this.loading = view.findViewById(2131165416);
        this.showLoadingView(false);
    }
    
    @Override
    public void hide(final boolean b) {
        super.hide(b);
        AnimationUtils.hideView(this.loading, b);
    }
    
    @Override
    public void showErrorView(final int n, final boolean b, final boolean b2) {
        super.showErrorView(n, b, b2);
        AnimationUtils.hideView(this.loading, b2);
    }
    
    @Override
    public void showErrorView(final boolean b) {
        super.showErrorView(b);
        AnimationUtils.hideView(this.loading, b);
    }
    
    public void showLoadingView(final boolean b) {
        super.hide(b);
        AnimationUtils.showView(this.loading, b);
    }
}
