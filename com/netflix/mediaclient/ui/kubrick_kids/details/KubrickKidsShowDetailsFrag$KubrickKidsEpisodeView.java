// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import android.widget.SpinnerAdapter;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter$IViewCreator;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationEdgePadding;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.kubrick.details.KubrickVideoDetailsViewGroup;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.Log;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.PorterDuff$Mode;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter;
import android.view.ViewGroup;
import android.os.Bundle;
import android.app.Fragment;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag$HeroSlideshow;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.android.widget.PressedStateHandler;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag;
import android.content.Context;
import com.netflix.mediaclient.android.widget.PressAnimationFrameLayout;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag$KubrickEpisodeView;

class KubrickKidsShowDetailsFrag$KubrickKidsEpisodeView extends KubrickShowDetailsFrag$KubrickEpisodeView
{
    private final PressAnimationFrameLayout pressableViewGroup;
    final /* synthetic */ KubrickKidsShowDetailsFrag this$0;
    
    public KubrickKidsShowDetailsFrag$KubrickKidsEpisodeView(final KubrickKidsShowDetailsFrag this$0, final Context context, final int n) {
        this.this$0 = this$0;
        super(this$0, context, n);
        this.pressableViewGroup = (PressAnimationFrameLayout)this.findViewById(2131624251);
        this.image.setPressedStateHandlerEnabled(false);
    }
    
    @Override
    protected void adjustHeight() {
        this.image.getLayoutParams().height = (int)((KidsUtils.getDetailsPageContentWidth((Context)this.this$0.getActivity()) - this.this$0.getActivity().getResources().getDimensionPixelOffset(2131296577) * (this.this$0.numColumns + 1.0f)) / this.this$0.numColumns * 0.5625f);
    }
    
    @Override
    protected CharSequence createTitleText(final EpisodeDetails episodeDetails) {
        return episodeDetails.getTitle();
    }
    
    @Override
    protected View getPressableView() {
        return (View)this.pressableViewGroup;
    }
    
    @Override
    protected PressedStateHandler getPressedStateHandler() {
        return this.pressableViewGroup.getPressedStateHandler();
    }
    
    @Override
    protected void updateProgressBar() {
        if (this.progressBar == null) {
            return;
        }
        if (this.progressVal > 0 && this.isCurrentEpisode) {
            this.progressBar.setVisibility(0);
            this.progressBar.setProgress(this.progressVal);
            this.progressBar.setSecondaryProgress(0);
            return;
        }
        this.progressBar.setVisibility(8);
    }
    
    @Override
    protected void updateTitle(final EpisodeDetails episodeDetails) {
        if (this.title != null) {
            super.updateTitle(episodeDetails);
            this.title.setTextColor(this.getResources().getColor(2131558503));
            final View view = (View)this.getParent();
            if (view != null) {
                view.setTag(2131623951, (Object)episodeDetails.getSeasonNumber());
            }
        }
    }
}
