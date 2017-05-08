// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker_kids.details;

import com.netflix.mediaclient.android.widget.PressedStateHandler;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.ui.barker.details.BarkerShowDetailsFrag;
import android.content.Context;
import com.netflix.mediaclient.android.widget.PressAnimationFrameLayout;
import com.netflix.mediaclient.ui.barker.details.BarkerShowDetailsFrag$BarkerEpisodeView;

class BarkerKidsShowDetailsFrag$BarkerKidsEpisodeView extends BarkerShowDetailsFrag$BarkerEpisodeView
{
    private final PressAnimationFrameLayout pressableViewGroup;
    final /* synthetic */ BarkerKidsShowDetailsFrag this$0;
    
    public BarkerKidsShowDetailsFrag$BarkerKidsEpisodeView(final BarkerKidsShowDetailsFrag this$0, final Context context, final int n) {
        this.this$0 = this$0;
        super(this$0, context, n);
        this.pressableViewGroup = (PressAnimationFrameLayout)this.findViewById(2131821036);
        this.image.setPressedStateHandlerEnabled(false);
    }
    
    @Override
    protected void adjustEpisodeImageHeight() {
        this.image.getLayoutParams().height = (int)((KidsUtils.getDetailsPageContentWidth((Context)this.this$0.getActivity()) - this.this$0.getActivity().getResources().getDimensionPixelOffset(2131427763) * (this.this$0.getNumColumns() + 1.0f)) / this.this$0.getNumColumns() * 0.5625f);
    }
    
    @Override
    protected void adjustEpisodeImageWidth() {
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
            this.progressBar.getLayoutParams().width = -1;
            return;
        }
        this.progressBar.setVisibility(8);
    }
    
    @Override
    protected void updateTitle(final EpisodeDetails episodeDetails) {
        if (this.title != null) {
            super.updateTitle(episodeDetails);
            this.title.setTextColor(this.getResources().getColor(2131755150));
            final View view = (View)this.getParent();
            if (view != null) {
                view.setTag(2131820565, (Object)episodeDetails.getSeasonNumber());
            }
        }
    }
}
