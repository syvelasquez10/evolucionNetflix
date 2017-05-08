// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import android.content.Context;
import com.netflix.mediaclient.android.widget.PressAnimationFrameLayout;
import com.netflix.mediaclient.ui.kubrick.details.KubrickVideoDetailsViewGroup;

class KubrickKidsMovieDetailsFrag$KubrickKidsMovieDetailsViewGroup extends KubrickVideoDetailsViewGroup
{
    private PressAnimationFrameLayout pressableCWImgGroup;
    final /* synthetic */ KubrickKidsMovieDetailsFrag this$0;
    
    public KubrickKidsMovieDetailsFrag$KubrickKidsMovieDetailsViewGroup(final KubrickKidsMovieDetailsFrag this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
        this.setupViews();
    }
    
    private void setupViews() {
        this.getHeroImage().setPressedStateHandlerEnabled(false);
        this.getHeroImage2().setPressedStateHandlerEnabled(false);
        this.pressableCWImgGroup.setOnClickListener((View$OnClickListener)new PressedStateHandler$DelayedOnClickListener(this.pressableCWImgGroup.getPressedStateHandler(), this.onCWClickListener));
    }
    
    @Override
    protected int calculateImageHeight() {
        return (int)(KidsUtils.getDetailsPageContentWidth(this.getContext()) * 0.5625f);
    }
    
    @Override
    protected void findViews() {
        super.findViews();
        this.pressableCWImgGroup = (PressAnimationFrameLayout)this.findViewById(2131624251);
    }
    
    @Override
    protected int getlayoutId() {
        return 2130903138;
    }
    
    @Override
    protected void setupImageClicks(final VideoDetails videoDetails, final NetflixActivity netflixActivity) {
    }
}
