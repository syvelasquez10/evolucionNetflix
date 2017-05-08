// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.model.leafs.PostPlayItem;
import android.view.View$OnClickListener;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.Button;

public final class PostPlayItemSeamlessView extends PostPlayItemView
{
    private static final String TAG = "PostPlayItemSeamlessView";
    private int mCountdownSeconds;
    private Button mNextEpisode;
    private PostPlayCallToAction mPlayAction;
    private PlayerFragment mPlayerFragment;
    private Button mWatchCredits;
    private boolean mWatchCreditsTapped;
    
    public PostPlayItemSeamlessView(final Context context) {
        this(context, null);
    }
    
    public PostPlayItemSeamlessView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public PostPlayItemSeamlessView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mWatchCreditsTapped = false;
    }
    
    private void updateUI() {
        if (this.mCountdownSeconds == 0) {
            this.mNextEpisode.animate().alpha(0.0f);
            this.mWatchCredits.animate().alpha(0.0f);
            if (this.mWatchCreditsTapped) {
                this.mPlayAction.playAction(true);
            }
            return;
        }
        this.mWatchCredits.setAlpha(1.0f);
        this.mNextEpisode.setAlpha(1.0f);
        this.mNextEpisode.setText((CharSequence)this.getResources().getString(2131296753, new Object[] { this.mCountdownSeconds }));
    }
    
    @Override
    protected void findViews() {
        this.mWatchCredits = (Button)this.findViewById(2131755748);
        this.mNextEpisode = (Button)this.findViewById(2131755728);
        this.mWatchCredits.setOnClickListener((View$OnClickListener)new PostPlayItemSeamlessView$1(this));
    }
    
    @Override
    public void onTick(final int mCountdownSeconds) {
        this.mCountdownSeconds = mCountdownSeconds;
        this.updateUI();
    }
    
    @Override
    protected void startTimer(final int mCountdownSeconds) {
        this.mCountdownSeconds = mCountdownSeconds;
        this.mPlayerFragment.getScreen().getTopPanel().show();
        this.updateUI();
    }
    
    @Override
    protected void stopTimer() {
        this.mWatchCredits.setVisibility(4);
    }
    
    @Override
    public void updateViews(final PostPlayItem postPlayItem, final NetflixActivity netflixActivity, final PlayerFragment mPlayerFragment, final PostPlayRequestContext postPlayRequestContext, final View$OnClickListener view$OnClickListener) {
        this.mPlayerFragment = mPlayerFragment;
        this.mPlayAction = new PostPlayCallToAction(netflixActivity, mPlayerFragment, postPlayItem.getPlayAction(), postPlayRequestContext, (View)this.mNextEpisode);
    }
}
