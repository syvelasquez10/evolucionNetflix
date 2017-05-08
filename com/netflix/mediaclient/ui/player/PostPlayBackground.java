// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.model.leafs.PostPlayItem;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.FrameLayout;

public abstract class PostPlayBackground extends FrameLayout
{
    protected NetflixActivity netflixActivity;
    
    public PostPlayBackground(final Context context) {
        this(context, null);
    }
    
    public PostPlayBackground(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public PostPlayBackground(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    protected abstract void findViews();
    
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.findViews();
    }
    
    public abstract void startBackgroundAutoPan();
    
    protected abstract void startTimer();
    
    protected abstract void stopTimer();
    
    public abstract void updateViews(final PostPlayItem p0, final NetflixActivity p1, final PlayerFragment p2, final PostPlayRequestContext p3);
}
