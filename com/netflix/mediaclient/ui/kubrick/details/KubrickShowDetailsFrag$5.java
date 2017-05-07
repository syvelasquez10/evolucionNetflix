// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;

class KubrickShowDetailsFrag$5 implements RecyclerViewHeaderAdapter$IViewCreator
{
    final /* synthetic */ KubrickShowDetailsFrag this$0;
    
    KubrickShowDetailsFrag$5(final KubrickShowDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public View createItemView() {
        return (View)new KubrickShowDetailsFrag$KubrickEpisodeView(this.this$0, (Context)this.this$0.getActivity(), 2130903103);
    }
}
