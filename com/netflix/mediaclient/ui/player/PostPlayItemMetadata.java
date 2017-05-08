// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.model.leafs.PostPlayItem;
import android.util.AttributeSet;
import android.content.Context;

public class PostPlayItemMetadata extends PostPlayItemView
{
    PostPlayItemBasic itemBasic;
    PostPlayMetadata metadata;
    
    public PostPlayItemMetadata(final Context context) {
        super(context, null);
    }
    
    public PostPlayItemMetadata(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    @Override
    protected void findViews() {
        this.itemBasic = (PostPlayItemBasic)this.findViewById(2131690177);
        this.metadata = (PostPlayMetadata)this.findViewById(2131690185);
    }
    
    @Override
    protected void startTimer(final int n) {
        if (this.metadata != null) {
            this.metadata.startTimer();
        }
    }
    
    @Override
    protected void stopTimer() {
        if (this.metadata != null) {
            this.metadata.stopTimer();
        }
    }
    
    @Override
    public void updateViews(final PostPlayItem postPlayItem, final NetflixActivity netflixActivity, final PlayerFragment playerFragment, final PostPlayRequestContext postPlayRequestContext, final View$OnClickListener view$OnClickListener) {
        this.itemBasic.updateViews(postPlayItem, netflixActivity, playerFragment, postPlayRequestContext, view$OnClickListener);
        this.metadata.updateViews(postPlayItem, netflixActivity, playerFragment, postPlayRequestContext);
    }
}
