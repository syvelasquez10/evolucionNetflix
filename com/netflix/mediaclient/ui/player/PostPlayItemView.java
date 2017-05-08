// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.model.leafs.PostPlayItem;
import android.animation.Animator$AnimatorListener;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.LinearLayout;

public abstract class PostPlayItemView extends LinearLayout
{
    private static final long FADE_DURATION_MS = 300L;
    
    public PostPlayItemView(final Context context) {
        this(context, null);
    }
    
    public PostPlayItemView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public PostPlayItemView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    protected abstract void findViews();
    
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.findViews();
    }
    
    public void onTick(final int n) {
    }
    
    final void startPostPlay(final int n) {
        this.setAlpha(0.0f);
        this.animate().alpha(1.0f).setDuration(300L).start();
        this.startTimer(n);
    }
    
    protected abstract void startTimer(final int p0);
    
    final void stopPostPlay() {
        this.animate().alpha(0.0f).setListener((Animator$AnimatorListener)new PostPlayItemView$1(this)).setDuration(300L).start();
    }
    
    protected abstract void stopTimer();
    
    public abstract void updateViews(final PostPlayItem p0, final NetflixActivity p1, final PlayerFragment p2, final PostPlayRequestContext p3, final View$OnClickListener p4);
}
