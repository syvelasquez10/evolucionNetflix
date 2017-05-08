// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.model.leafs.PostPlayItem;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.NetflixTextButton;

public class PostPlayItemSignup extends PostPlayItemView
{
    protected NetflixTextButton samplingButton;
    protected TextView samplingDetails;
    
    public PostPlayItemSignup(final Context context) {
        super(context, null);
    }
    
    public PostPlayItemSignup(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    @Override
    protected void findViews() {
        this.samplingDetails = (TextView)this.findViewById(2131755749);
        this.samplingButton = (NetflixTextButton)this.findViewById(2131755750);
    }
    
    @Override
    protected void startTimer(final int n) {
    }
    
    @Override
    protected void stopTimer() {
    }
    
    @Override
    public void updateViews(final PostPlayItem postPlayItem, final NetflixActivity netflixActivity, final PlayerFragment playerFragment, final PostPlayRequestContext postPlayRequestContext, final View$OnClickListener onClickListener) {
        this.samplingDetails.setText((CharSequence)this.getContext().getString(2131296778, new Object[] { postPlayItem.getAncestorTitle() }));
        this.samplingButton.setOnClickListener(onClickListener);
    }
}
