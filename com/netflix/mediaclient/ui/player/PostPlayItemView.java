// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.model.leafs.PostPlayItem;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.LinearLayout;

public abstract class PostPlayItemView extends LinearLayout
{
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
    
    protected abstract void startTimer();
    
    protected abstract void stopTimer();
    
    public abstract void updateViews(final PostPlayItem p0, final NetflixActivity p1, final PlayerFragment p2, final PostPlayRequestContext p3, final View$OnClickListener p4);
}
