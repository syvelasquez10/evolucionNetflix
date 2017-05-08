// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.content.Context;
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
        return (View)new EpisodesFrag$EpisodeView(this.this$0, (Context)this.this$0.getActivity(), 2130903107);
    }
}
