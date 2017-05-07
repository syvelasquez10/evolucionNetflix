// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;

class KubrickKidsShowDetailsFrag$1 implements RecyclerViewHeaderAdapter$IViewCreator
{
    final /* synthetic */ KubrickKidsShowDetailsFrag this$0;
    
    KubrickKidsShowDetailsFrag$1(final KubrickKidsShowDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public View createItemView() {
        return (View)new KubrickKidsShowDetailsFrag$KubrickKidsEpisodeView(this.this$0, (Context)this.this$0.getActivity(), 2130903107);
    }
}
