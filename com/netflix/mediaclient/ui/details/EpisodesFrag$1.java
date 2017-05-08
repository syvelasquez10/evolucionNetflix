// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.content.Context;
import com.netflix.mediaclient.service.configuration.persistent.Config_Ab7994;
import android.view.View;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;

class EpisodesFrag$1 implements RecyclerViewHeaderAdapter$IViewCreator
{
    final /* synthetic */ EpisodesFrag this$0;
    
    EpisodesFrag$1(final EpisodesFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public View createItemView() {
        if (this.this$0.showDetailsOnLaunch && Config_Ab7994.shouldRenderDPWithBifs((Context)this.this$0.getActivity())) {
            return (View)new EpisodesFrag$EpisodeView_Ab7994(this.this$0, (Context)this.this$0.getActivity(), 2130903146);
        }
        return (View)new EpisodesFrag$EpisodeView(this.this$0, (Context)this.this$0.getActivity(), 2130903145);
    }
}
